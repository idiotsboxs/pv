package com.puntoventa.manage.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.puntoventa.Impl.InventarioABCImpl;
import com.puntoventa.Impl.ReporteVentasImpl;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.InventarioABC;
import com.puntoventa.utils.SpringUtils;
import com.puntoventa.vo.ConcentradoProveedor;

@ManagedBean(name = "vwVentasManage")
@ViewScoped
public class RepVentasManage {
	private BarChartModel barModel;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private Usuario usuario;
	private float maxTotal = 0;
	private ReporteVentasImpl rep;
	private Date firstDay;
	private Date lastDay;
	private CatProveedor catProveedor;
	private List<CatProveedor> proveedores;
	private InventarioABC inv;
	private ConcentradoProveedor concentradoProveedor;

	public RepVentasManage() {
		// TODO Auto-generated constructor stub
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		this.usuario = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
		this.rep = (ReporteVentasImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("reporteVentasImpl");
		this.inv = (InventarioABCImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("inventarioABCImpl");
		firstDay = Calendar.getInstance().getTime();
		lastDay = Calendar.getInstance().getTime();
		catProveedor = new CatProveedor();
	}

	@PostConstruct
	public void init() {
		Calendar dia = Calendar.getInstance();
		Calendar diaDos = Calendar.getInstance();
		diaDos.set(Calendar.DAY_OF_WEEK, dia.get(Calendar.DAY_OF_WEEK) + 6);
		createBarModel(dia, diaDos);
		
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the barModel
	 */
	public BarChartModel getBarModel() {
		return barModel;
	}

	/**
	 * @param barModel
	 *            the barModel to set
	 */
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	private BarChartModel initBarModel(final Calendar diaUno, final Calendar diaDos) {
		BarChartModel model = new BarChartModel();
		ChartSeries dias = new ChartSeries("Total de venta por d�a");

		List<com.puntoventa.hibernate.VwVentasId> lstVw = new ArrayList<com.puntoventa.hibernate.VwVentasId>();
		lstVw = rep.getVentasPorDia(diaUno, diaDos);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (com.puntoventa.hibernate.VwVentasId vw : lstVw) {
			
			dias.set(format.format(vw.getFechaLiq()), vw.getTotal());

			if (maxTotal == 0 || maxTotal < Float.valueOf(vw.getTotal().toString())) {
				maxTotal = Float.valueOf(vw.getTotal().toString());
			}
		}

		model.addSeries(dias);

		return model;
	}

	private void createBarModel(final Calendar diaUno, final Calendar diaDos) {
		barModel = initBarModel(diaUno, diaDos);
		barModel.setTitle("Ventas por dia");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Dia");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Monto Vendido");
		yAxis.setMin(0);
		yAxis.setMax(maxTotal + 100);
	}

	public void renderBarModel() {
		Calendar dayOne = Calendar.getInstance();
		dayOne.setTime(getFirstDay());
		Calendar dayTwo = Calendar.getInstance();
		dayTwo.setTime(getLastDay());
		createBarModel(dayOne, dayTwo);
	}
	
	
	
	public void generaConcentrado(){
	  this.setConcentradoProveedor(this.rep.getConcentradoProveedor(firstDay, lastDay, getCatProveedor()));	
	}
	
	public List<CatProveedor> getCatProveedores() {
		return inv.getProveedores();
	}

	public Date getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	public CatProveedor getCatProveedor() {
		return catProveedor;
	}

	public void setCatProveedor(CatProveedor catProveedor) {
		this.catProveedor = catProveedor;
	}

	public List<CatProveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<CatProveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public ConcentradoProveedor getConcentradoProveedor() {
		return concentradoProveedor;
	}

	public void setConcentradoProveedor(ConcentradoProveedor concentradoProveedor) {
		this.concentradoProveedor = concentradoProveedor;
	}

	
	
}
