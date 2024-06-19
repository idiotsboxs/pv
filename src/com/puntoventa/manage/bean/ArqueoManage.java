package com.puntoventa.manage.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.CorteImpl;
import com.puntoventa.Impl.VentaMostradorImpl;
import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.hibernate.Ventas;
import com.puntoventa.interfase.Arqueo;
import com.puntoventa.utils.SpringUtils;
import com.puntoventa.vo.ConcentradoCategoria;

@ManagedBean(name="arqueoManage")
@ViewScoped
public class ArqueoManage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double ventaTotal = new Double(0.0);
	private String ventaDia;
	private List<Ventas> listVentas;
	private Date fechaLiq = new Date();
	private List<MovimientoCaja> listaCaja;
	private Double totalCaja = new Double(0.0);
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private CorteImpl corteImpl;
	private ConcentradoCategoria concentrado;
	private List<CatCategoria> listaCategoria;
	private CatCategoria catCategoria;
	
	public ArqueoManage(){
		
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		this.corteImpl = (CorteImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("corteImpl");
		listaCategoria = corteImpl.getCategorias();
		catCategoria = new CatCategoria();
	}
	
	/**
	 * @return the totalCaja
	 */
	public Double getTotalCaja() {
		return totalCaja;
	}
	/**
	 * @param totalCaja the totalCaja to set
	 */
	public void setTotalCaja(Double totalCaja) {
		this.totalCaja = totalCaja;
	}
	/**
	 * @return the fechaLiq
	 */
	public Date getFechaLiq() {
		return fechaLiq;
	}
	/**
	 * @param fechaLiq the fechaLiq to set
	 */
	public void setFechaLiq(Date fechaLiq) {
		this.fechaLiq = fechaLiq;
	}
	/**
	 * @return the ventaTotal
	 */
	public Double getVentaTotal() {
		return ventaTotal;
	}
	/**
	 * @param ventaTotal the ventaTotal to set
	 */
	public void setVentaTotal(Double ventaTotal) {
		this.ventaTotal = ventaTotal;
	}
	/**
	 * @return the listVentas
	 */
	public List<Ventas> getListVentas() {
		return listVentas;
	}
	/**
	 * @param listVentas the listVentas to set
	 */
	public void setListVentas(List<Ventas> listVentas) {
		this.listVentas = listVentas;
	}
	

	
	
	/**
	 * @return the listaCaja
	 */
	public List<MovimientoCaja> getListaCaja() {
		return listaCaja;
	}
	/**
	 * @param listaCaja the listaCaja to set
	 */
	public void setListaCaja(List<MovimientoCaja> listaCaja) {
		this.listaCaja = listaCaja;
	}
	/**
	 * @return the ventaDia
	 */
	public String getVentaDia() {
		return ventaDia;
	}
	/**
	 * @param ventaDia the ventaDia to set
	 */
	public void setVentaDia(String ventaDia) {
		this.ventaDia = ventaDia;
	}
	public void generaCorte(){
		
		this.listVentas = corteImpl.getVentasDelDia(getFechaLiq());
		this.listaCaja = corteImpl.getMovCaja(getFechaLiq());
		
		
		
		this.ventaTotal = 0.0;
		for (Ventas ventas : listVentas) {
			
			this.ventaTotal = this.ventaTotal + ventas.getTotal().doubleValue();
		}
		
		this.totalCaja = 0.0;
		for(MovimientoCaja caja : listaCaja){
			
			this.totalCaja = this.totalCaja + caja.getMonto().doubleValue();
		}
		
		this.setVentaDia(this.ventaTotal.toString());
	}
	
	public void generaConcentrado(){
		
		this.setConcentrado(corteImpl.getConcentradoCategoria(getFechaLiq(), getCatCategoria()));
	}
	public ConcentradoCategoria getConcentrado() {
		return concentrado;
	}

	public void setConcentrado(ConcentradoCategoria concentrado) {
		this.concentrado = concentrado;
	}

	
	public List<CatCategoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<CatCategoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public void cierreDia(){
		
		corteImpl.cierreDia();
	}

	public CatCategoria getCatCategoria() {
		return catCategoria;
	}

	public void setCatCategoria(CatCategoria catCategoria) {
		this.catCategoria = catCategoria;
	}
	
	
}
