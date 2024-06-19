package com.puntoventa.hibernate;
// Generated 27/11/2015 03:27:13 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * VentasHistoricas generated by hbm2java
 */
public class VentasHistoricas implements java.io.Serializable {

	private int folio;
	private MovimientoCajaHistoricas movimientoCajaHistoricas;
	private Usuario usuario;
	private Date fecha;
	private BigDecimal total;
	private BigDecimal subTotal;
	private BigDecimal iva;
	private Integer status;
	private Date fechaLiq;
	private Integer idDoc;

	public VentasHistoricas() {
	}

	public VentasHistoricas(int folio) {
		this.folio = folio;
	}

	public VentasHistoricas(int folio, MovimientoCajaHistoricas movimientoCajaHistoricas, Usuario usuario, Date fecha,
			BigDecimal total, BigDecimal subTotal, BigDecimal iva, Integer status, Date fechaLiq, Integer idDoc) {
		this.folio = folio;
		this.movimientoCajaHistoricas = movimientoCajaHistoricas;
		this.usuario = usuario;
		this.fecha = fecha;
		this.total = total;
		this.subTotal = subTotal;
		this.iva = iva;
		this.status = status;
		this.fechaLiq = fechaLiq;
		this.idDoc = idDoc;
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public MovimientoCajaHistoricas getMovimientoCajaHistoricas() {
		return this.movimientoCajaHistoricas;
	}

	public void setMovimientoCajaHistoricas(MovimientoCajaHistoricas movimientoCajaHistoricas) {
		this.movimientoCajaHistoricas = movimientoCajaHistoricas;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFechaLiq() {
		return this.fechaLiq;
	}

	public void setFechaLiq(Date fechaLiq) {
		this.fechaLiq = fechaLiq;
	}

	public Integer getIdDoc() {
		return this.idDoc;
	}

	public void setIdDoc(Integer idDoc) {
		this.idDoc = idDoc;
	}

}
