package com.puntoventa.manage.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.ImpresionImpl;
import com.puntoventa.Impl.VentaMostradorImpl;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatUnidadMedida;
import com.puntoventa.hibernate.CatVentas;

import com.puntoventa.hibernate.Documento;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.hibernate.Ventas;
import com.puntoventa.interfase.Impresion;
import com.puntoventa.interfase.VentaMostrador;
import com.puntoventa.utils.SpringUtils;

@ManagedBean(name = "devolucionManage")
@ViewScoped
public class DevolucionManage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ventas ventas;
	private CatVentas catVentas;
	private List<CatVentas> catListVentas = new ArrayList<CatVentas>();
	private CatProductos productos;
	private Integer pedido = 0;
	private Integer existencia = 0;
	private Usuario usuario;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private VentaMostrador mostrador;
	

	public DevolucionManage() {
		this.ventas = new Ventas();
		this.catVentas = new CatVentas();
		// this.catVentas.setId(new CatVentasId());
		this.productos = new CatProductos();
		this.productos.setCatUnidadMedida(new CatUnidadMedida());
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		this.usuario = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
		this.mostrador = (VentaMostradorImpl) SpringUtils.geWebApplicationContex(faceContext)
				.getBean("ventaMostradorImpl");
		this.ventas.setUsuario(usuario);

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
	 * @param catListVentas
	 *            the catListVentas to set
	 */
	public void setCatListVentas(List<CatVentas> catListVentas) {
		this.catListVentas = catListVentas;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	/**
	 * @return the ventas
	 */
	public Ventas getVentas() {
		return ventas;
	}

	/**
	 * @param ventas
	 *            the ventas to set
	 */
	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}

	public String guardar() {
		if (catListVentas.size() >= 1) {
			Impresion impresion = new ImpresionImpl();

			boolean print = mostrador.guardaDevolucion(catListVentas);
			if (print) {
				impresion.impresionTicket(catListVentas);
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
		}

		
		return "success";
	}

	private void recalcular(CatVentas ventas) {

		ventas.setFechaLiq(new Date());
		if(ventas.getCantidad()>0){
		ventas.setCantidad( (ventas.getCantidad())* -1);
		}
		Double sub = ventas.getPUnitario().doubleValue() * ventas.getCantidad();
		ventas.setSubTotal(BigDecimal.valueOf(sub));
		ventas.setStatus(1);
		// this.pedido = ventas.getCantidad();
		// this.existencia = getProductos().getCantidad() - this.pedido;
		calculoVenta();
		getVentas().setDocumento(new Documento(3));
		ventas.setVentas(getVentas());

	}

	public void recalcularVenta() {

		for (CatVentas ventas : getCatListVentas()) {

			if (ventas.getCatProductos().getCatUnidadMedida().getIdUnidadMedida().equals(5)) {
				recalcularPeso(ventas);
			} else {
				recalcular(ventas);
			}

		}
	}

	private void recalcularPeso(CatVentas ventas) {

		ventas.setFechaLiq(new Date());
		if(ventas.getCantidad()>0){
		ventas.setCantidad( (ventas.getCantidad())* -1);
		}
		Double sub = ventas.getPUnitario().doubleValue() * (ventas.getCantidad().doubleValue() / 1000);
		ventas.setSubTotal(BigDecimal.valueOf(sub));
		ventas.setStatus(1);
		// this.pedido = ventas.getCantidad();
		// this.existencia = getProductos().getCantidad() - this.pedido;
		calculoVenta();
		getVentas().setDocumento(new Documento(3));
		ventas.setVentas(getVentas());

	}

	public void addVenta() {
		boolean newItem = true;
		for (CatVentas ventas : getCatListVentas()) {

			if (getProductos().getIdProducto().equals(ventas.getCatProductos().getIdProducto())) {

				ventas.setFechaLiq(new Date());
				ventas.setCantidad(ventas.getCantidad() + getCatVentas().getCantidad());
				Double sub = 0.0;
				if(ventas.getCatProductos().getCatUnidadMedida().getIdUnidadMedida().equals(5)){
					sub = ventas.getPUnitario().doubleValue() * (ventas.getCantidad().doubleValue() / 1000);
				}else{
					sub =  ventas.getPUnitario().doubleValue() * ventas.getCantidad().doubleValue();
				}
				ventas.setSubTotal(BigDecimal.valueOf(sub));
				ventas.setStatus(1);
				this.pedido = ventas.getCantidad();
				this.existencia = getProductos().getCantidad() - this.pedido;
				calculoVenta();
				getVentas().setDocumento(new Documento(3));
				ventas.setVentas(getVentas());
				newItem = false;
			}

		}

		if (newItem) {
			CatVentas cat = new CatVentas();
			CatProductos prod = new CatProductos();
			// cat.setId(new CatVentasId());
			cat.setCatProductos(new CatProductos());
			prod.setIdProducto(getProductos().getIdProducto());
			prod.setDescProducto(getProductos().getDescProducto());
			prod.setIva(getProductos().getIva());
			prod.setPUCompra(getProductos().getPUCompra());
			prod.setCantidad(getProductos().getCantidad());
			prod.setCatUnidadMedida(getCatVentas().getCatProductos().getCatUnidadMedida());
			cat.setStatus(1);
			cat.getCatProductos().setIdProducto((getProductos().getIdProducto()));

			cat.setCatProductos(prod);
			cat.setCantidad(getCatVentas().getCantidad());
			cat.setFechaLiq(new Date());
			cat.setPUnitario(getProductos().getPUVenta());
			if(prod.getCatUnidadMedida().getIdUnidadMedida().equals(5)){
			cat.setSubTotal(
					BigDecimal.valueOf(getCatVentas().getCantidad() * (getProductos().getPUVenta().doubleValue()/1000)));
			}else{
				cat.setSubTotal(
						BigDecimal.valueOf(getCatVentas().getCantidad() * getProductos().getPUVenta().doubleValue()));
			}
			this.pedido = getCatVentas().getCantidad();
			this.existencia = getProductos().getCantidad() - this.pedido;
			getVentas().setDocumento(new Documento(3));
			cat.setVentas(getVentas());
			catListVentas.add(cat);
			calculoVenta();

			// if (catListVentas.size() == 1) {
			// catListVentas.get(0).setVentas(getVentas());
			// }

		}

	}

	public List<CatVentas> getCatListVentas() {
		return catListVentas;
	}

	/**
	 * @param catVentas
	 *            the catVentas to set
	 */
	public void setCatVentas(CatVentas catVentas) {
		this.catVentas = catVentas;
	}

	/**
	 * @return the catVentas
	 */
	public CatVentas getCatVentas() {
		return catVentas;
	}

	/**
	 * @return the productos
	 */
	public CatProductos getProductos() {

		return productos;
	}

	/**
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(CatProductos productos) {
		this.productos = productos;
	}

	public void obtenerProducto() {

		CatProductos prod = mostrador.getProducto(getProductos());
		if (prod == null) {
			prod = new CatProductos();
			prod.setCatUnidadMedida(new CatUnidadMedida());
		}
		this.pedido = 0;
		this.existencia = 0;
		setProductos(prod);
		if (prod.getIdProducto() != null) {
			getCatVentas().setCatProductos(prod);
			if (prod.getCatUnidadMedida().getIdUnidadMedida().equals(5)) {
				getCatVentas().setCantidad(-1000);
			} else {
				getCatVentas().setCantidad(-1);
			}
			addVenta();

		}
		
		getProductos().setCodigoBarras(new Long("0"));

	}

	public void calculoVenta() {
		double subtotal = 0;
		double total = 0;
		double iva = 0;
		double impuesto = 0;
		for (CatVentas venta : catListVentas) {
			subtotal = subtotal + venta.getSubTotal().doubleValue();
			impuesto = venta.getCatProductos().getIva();
		}

		subtotal = subtotal / (1.16);
		iva = (subtotal * (impuesto / 100));
		total = subtotal + iva;

		getVentas().setFecha(new Date());
		getVentas().setFechaLiq(new Date());
		getVentas().setIva(BigDecimal.valueOf(iva));
		getVentas().setStatus(1);
		getVentas().setSubTotal(BigDecimal.valueOf(subtotal));
		getVentas().setTotal(BigDecimal.valueOf(total));
		// getVentas().setTotal(BigDecimal.valueOf(subtotal));

	}

	public String eliminar() {
		Integer codigo;

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		codigo = Integer.valueOf(params.get("producto"));
		CatVentas removeVenta = new CatVentas();
		for (CatVentas venta : getCatListVentas()) {
			if (venta.getCatProductos().getIdProducto().equals(codigo)) {
				removeVenta = venta;
			}
		}
		getCatListVentas().remove(removeVenta);
		calculoVenta();
		return "Ok";
	}

}
