package com.puntoventa.manage.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.CompraImpl;
import com.puntoventa.Impl.VentaMostradorImpl;
import com.puntoventa.hibernate.CatCompras;
import com.puntoventa.hibernate.CatComprasId;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatUnidadMedida;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.Compras;
import com.puntoventa.hibernate.Documento;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.Compra;
import com.puntoventa.interfase.VentaMostrador;
import com.puntoventa.utils.SpringUtils;

@ManagedBean(name = "compraManage")
@ViewScoped
public class CompraManage {

	private CatCompras catCompras;
	private Compras compras;
	private List<CatCompras> listCompras;
	private CatProductos productos;
	private Usuario usuario;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private Compra mostrador;


	@PostConstruct
	public void init() {
		// do your initializations here
	}

	public CompraManage() {
		this.listCompras = new ArrayList<CatCompras>();
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext
				.getExternalContext().getRequest();
		this.usuario = (Usuario) httpServletRequest.getSession().getAttribute(
				"sessionUsuario");
		this.compras = new Compras();
		this.productos = new CatProductos();
		this.catCompras = new CatCompras();
		this.compras.setIdUsuario(this.usuario.getIdUsuario());
		this.mostrador = (CompraImpl) SpringUtils
				.geWebApplicationContex(faceContext).getBean(
						"compraImpl");
	}

	/**
	 * @return the catCompras
	 */
	public CatCompras getCatCompras() {
		return catCompras;
	}

	/**
	 * @param catCompras
	 *            the catCompras to set
	 */
	public void setCatCompras(CatCompras catCompras) {
		this.catCompras = catCompras;
	}

	/**
	 * @return the compras
	 */
	public Compras getCompras() {
		return compras;
	}

	/**
	 * @param compras
	 *            the compras to set
	 */
	public void setCompras(Compras compras) {
		this.compras = compras;
	}

	/**
	 * @return the listCompras
	 */
	public List<CatCompras> getListCompras() {
		return listCompras;
	}

	/**
	 * @param listCompras
	 *            the listCompras to set
	 */
	public void setListCompras(List<CatCompras> listCompras) {
		this.listCompras = listCompras;
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

	public void obtenerProducto() {

		CatProductos prod = mostrador.getProducto(getProductos());
		if (prod == null) {
			prod = new CatProductos();
			prod.setCatUnidadMedida(new CatUnidadMedida());
		}

		setProductos(prod);
		if (prod.getIdProducto() != null) {
			getCatCompras().setCatProductos(prod);
			if (prod.getCatUnidadMedida().getIdUnidadMedida().equals(5)) {
				getCatCompras().setCantidad(1000);
			} else {
				getCatCompras().setCantidad(1);
			}

		}

	}

	public void addCompra() {
		boolean newItem = true;
		this.obtenerProducto();
		if (getProductos().getCodigoBarras() != null) {
			for (CatCompras compras : getListCompras()) {

				if (getProductos().getIdProducto().equals(
						compras.getCatProductos().getIdProducto())) {
					getCompras().setDocumento(new Documento(2));
					CatComprasId id = new CatComprasId();
					compras.setPCompra(getProductos().getPUCompra());
					compras.setPVenta(getProductos().getPUVenta());
					compras.getCatProductos().setPUCompra(
							getProductos().getPUCompra());
					compras.getCatProductos().setPUVenta(
							getProductos().getPUVenta());
					compras.setCantidad(compras.getCantidad()
							+ getCatCompras().getCantidad());
					Double sub = compras.getPCompra().doubleValue()
							* compras.getCantidad();
					compras.setSubTotal(BigDecimal.valueOf(sub));
					calculoCompra();
					id.setIdProducto(compras.getCatProductos().getIdProducto());
					compras.setId(id);
					compras.setStatus(1);
					compras.setCompras(getCompras());
					newItem = false;
				}

			}

			if (newItem) {
				CatComprasId id = new CatComprasId();
				CatCompras cat = new CatCompras();
				CatProductos prod = new CatProductos();
				prod.setIdProducto(getProductos().getIdProducto());
				prod.setDescProducto(getProductos().getDescProducto());
				prod.setIva(getProductos().getIva());
				prod.setPUCompra(getProductos().getPUCompra());
				prod.setPUVenta(getProductos().getPUVenta());
				prod.setCantidad(getProductos().getCantidad());
				prod.setCatUnidadMedida(getProductos().getCatUnidadMedida());
				cat.setStatus(1);
				cat.setCatProductos(prod);
				cat.setCantidad(getCatCompras().getCantidad());
				cat.setPCompra(getProductos().getPUCompra());
				cat.setPVenta(getProductos().getPUVenta());
				cat.setSubTotal(BigDecimal.valueOf(getCatCompras()
						.getCantidad()
						* getProductos().getPUCompra().doubleValue()));
				id.setIdProducto(getProductos().getIdProducto());
				cat.setId(id);
				getCompras().setDocumento(new Documento(2));
				listCompras.add(cat);
				calculoCompra();

				if (listCompras.size() == 1) {
					listCompras.get(0).setCompras(getCompras());
				}

			}

		}

	}

	private void calculoCompra() {
		double subtotal = 0;
		double total = 0;
		double iva = 0;
		double impuesto = 0;
		for (CatCompras compra : listCompras) {
			subtotal = subtotal + compra.getSubTotal().doubleValue();
			impuesto = compra.getCatProductos().getIva();
		}

		subtotal = subtotal / (1.16);
		iva = (subtotal * (impuesto / 100));
		total = subtotal + iva;

		getCompras().setFecha(new Date());
		getCompras().setIva(BigDecimal.valueOf(iva));
		getCompras().setStatus(1);
		getCompras().setSubTotal(BigDecimal.valueOf(subtotal));
		getCompras().setTotal(BigDecimal.valueOf(total));

	}

	public String eliminar() {
		Integer codigo;

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		codigo = Integer.valueOf(params.get("producto"));
		CatCompras removeCompra = new CatCompras();
		for (CatCompras compra : listCompras) {
			if (compra.getCatProductos().getIdProducto().equals(codigo)) {
				removeCompra = compra;
			}
		}

		listCompras.remove(removeCompra);
		calculoCompra();
		return "Ok";
	}

	public String guardar() {
		if (listCompras.size() >= 1) {
			
			mostrador.guardaCompra(listCompras);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.clear();
		}

		return "success";
	}

	private void recalcular(CatCompras compras) {

		// ventas.setCantidad( getCatVentas().getCantidad());
		Double sub = compras.getPCompra().doubleValue() * compras.getCantidad();
		compras.setSubTotal(BigDecimal.valueOf(sub));
		compras.setStatus(1);
		// this.pedido = ventas.getCantidad();
		// this.existencia = getProductos().getCantidad() - this.pedido;
		calculoCompra();

		compras.setCompras(getCompras());

	}

	public void recalcularCompra() {

		for (CatCompras compras : getListCompras()) {

			recalcular(compras);

		}
	}

}
