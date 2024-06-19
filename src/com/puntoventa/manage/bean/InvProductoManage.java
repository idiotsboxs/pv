package com.puntoventa.manage.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.InventarioABCImpl;
import com.puntoventa.Impl.VentaMostradorImpl;
import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatUnidadMedida;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.InventarioABC;
import com.puntoventa.interfase.VentaMostrador;
import com.puntoventa.utils.SpringUtils;

/**
 * @author cbuendia
 *
 */
@ManagedBean(name = "invProductoManage")
@ViewScoped
public class InvProductoManage {

	private boolean abastecimiento;
	private CatProductos catProductos;
	private Usuario usuario;
	private List<CatProductos> productos;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private InventarioABC inv;
	private VentaMostradorImpl ventaMostrador;

	
	public InvProductoManage() {
		
		productos = new ArrayList<CatProductos>();
		
		
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		this.usuario = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
		inv = (InventarioABCImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("inventarioABCImpl");
		this.ventaMostrador = (VentaMostradorImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("ventaMostradorImpl");
		
		
		
	}

	@PostConstruct
	public void init() {
		catProductos = new CatProductos();
		catProductos.setCatUnidadMedida(new CatUnidadMedida());
		catProductos.setCatProveedor(new CatProveedor());
		catProductos.setCatCategoria(new CatCategoria());
	}
	
	
	
	
	public boolean isAbastecimiento() {
		if(this.catProductos.getIdTipoAbastecimiento() == null || this.catProductos.getIdTipoAbastecimiento().equals(0)){
			abastecimiento = false;
		}else{
			abastecimiento = true;
		}
		return abastecimiento;
	}

	public void setAbastecimiento(boolean abastecimiento) {

		if(abastecimiento){
			catProductos.setIdTipoAbastecimiento(1) ;
		}else{
			catProductos.setIdTipoAbastecimiento(0) ;
		}

		
		this.abastecimiento = abastecimiento;
	}

	/**
	 * @return the productos
	 */
	public List<CatProductos> getProductos() {
		return productos;
	}

	/**
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<CatProductos> productos) {
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

	/**
	 * @return the catProductos
	 */
	public CatProductos getCatProductos() {
		return catProductos;
	}

	/**
	 * @param catProductos
	 *            the catProductos to set
	 */
	public void setCatProductos(CatProductos catProductos) {
		this.catProductos = catProductos;
	}

	public List<CatUnidadMedida> getUnidadMedida() {


		return inv.getUnidadMedida();

	}

	public String guardar() {

		try {
			
			inv.guardar(getCatProductos());
			return "success";
		} catch (Exception ex) {
			return "fail";
		}

	}

	public String editar() {

		try {

			inv.editar(getCatProductos());
			return "success";
		} catch (Exception ex) {
			return "fail";
		}

	}
	
	public String articuloLike() {

		try {

			productos = inv.getProductLike(catProductos);
			return "success";
		} catch (Exception ex) {
			return "fail";
		}

	}

	
	
	public List<CatProveedor> getCatProveedores() {
		return inv.getProveedores();
	}



	public List<CatCategoria> getCatCategorias() {
		return inv.getCategorias();
	}

	

	public void obtenerProducto() {
		
		CatProductos prod = ventaMostrador.getProducto(this.getCatProductos());
		if (prod == null || prod.getCodigoBarras() == null) {
			prod = new CatProductos();
			prod.setCatUnidadMedida(new CatUnidadMedida());
			prod.setCatProveedor(new CatProveedor());
			prod.setCatCategoria(new CatCategoria());
		}

		setCatProductos(prod);

	}
}
