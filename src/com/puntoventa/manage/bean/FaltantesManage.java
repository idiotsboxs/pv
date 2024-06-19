package com.puntoventa.manage.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.ReporteFaltantesImpl;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.interfase.ReporteFaltantes;
import com.puntoventa.utils.SpringUtils;

@ManagedBean(name="faltantesManage")
@SessionScoped
public class FaltantesManage {

	private Integer abastecimiento;
	private List<CatProveedor> proveedores;
	private List<CatProductos> productos;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private ReporteFaltantes reporteFaltantes;
	private CatProductos catProductos;
	private boolean surte;
	
	public FaltantesManage(){
		
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext
				.getExternalContext().getRequest();
		this.reporteFaltantes = (ReporteFaltantesImpl)SpringUtils
				.geWebApplicationContex(faceContext).getBean(
						"reporteFaltantesImpl");
		
		this.setProveedores(reporteFaltantes.getProveedores());
		catProductos = new CatProductos();
		catProductos.setCatProveedor(new CatProveedor());
	}
	
	public void obtineReporte(){
		if(getCatProductos().getCatProveedor().getIdProveedor().equals(666)){
			getCatProductos().getCatProveedor().setIdProveedor(null);
		}
		this.catProductos.setIdTipoAbastecimiento(surte == false ? 0 : 1);
		this .productos = this.reporteFaltantes.getFaltantes(getCatProductos());
		
	}
	
	public Integer getAbastecimiento() {
		return abastecimiento;
	}
	public void setAbastecimiento(Integer abastecimiento) {
		this.abastecimiento = abastecimiento;
	}
	public List<CatProveedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(List<CatProveedor> proveedores) {
		this.proveedores = proveedores;
	}
	public List<CatProductos> getProductos() {
		return productos;
	}
	public void setProductos(List<CatProductos> productos) {
		this.productos = productos;
	}



	public CatProductos getCatProductos() {
		return catProductos;
	}



	public void setCatProductos(CatProductos catProductos) {
		this.catProductos = catProductos;
	}



	public boolean isSurte() {
		return surte;
	}



	public void setSurte(boolean surte) {
		this.surte = surte;
	}
	
	
	
	
}
