package com.puntoventa.manage.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.puntoventa.Impl.MovCajaImpl;
import com.puntoventa.hibernate.CatMovCaja;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.hibernate.Usuario;

@ManagedBean(name = "cajaManage")
@ViewScoped
public class CajaManage {

	private Usuario usuario;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;
	private MovimientoCaja caja;
	private List<CatMovCaja> catCaja;

	public CajaManage() {
		caja = new MovimientoCaja();
		caja.setCatMovCaja(new CatMovCaja());
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		this.usuario = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
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
	 * @return the caja
	 */
	public MovimientoCaja getCaja() {
		return caja;
	}

	/**
	 * @param caja
	 *            the caja to set
	 */
	public void setCaja(MovimientoCaja caja) {
		this.caja = caja;
	}

	
	/**
	 * @return the catCaja
	 */
	public List<CatMovCaja> getCatCaja() {
		com.puntoventa.interfase.MovimientoCaja movDao = new MovCajaImpl();
		this.catCaja = movDao.getCatCaja();
		return catCaja;
	}

	/**
	 * @param catCaja the catCaja to set
	 */
	public void setCatCaja(List<CatMovCaja> catCaja) {
		this.catCaja = catCaja;
	}

	public String guardar() {

		com.puntoventa.interfase.MovimientoCaja movDao = new MovCajaImpl();

		this.caja.setUsuario(getUsuario());
		movDao.Guardar(caja);

		return "success";
	}

}
