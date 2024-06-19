package com.puntoventa.hibernate;
// Generated 27/11/2015 03:27:13 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * CatVentasHistoricasId generated by hbm2java
 */
public class CatVentasHistoricasId implements java.io.Serializable {

	private int idProducto;
	private int folio;
	private Integer cantidad;
	private BigDecimal PUnitario;
	private BigDecimal subTotal;
	private Integer status;
	private Date fechaLiq;

	public CatVentasHistoricasId() {
	}

	public CatVentasHistoricasId(int idProducto, int folio) {
		this.idProducto = idProducto;
		this.folio = folio;
	}

	public CatVentasHistoricasId(int idProducto, int folio, Integer cantidad, BigDecimal PUnitario, BigDecimal subTotal,
			Integer status, Date fechaLiq) {
		this.idProducto = idProducto;
		this.folio = folio;
		this.cantidad = cantidad;
		this.PUnitario = PUnitario;
		this.subTotal = subTotal;
		this.status = status;
		this.fechaLiq = fechaLiq;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPUnitario() {
		return this.PUnitario;
	}

	public void setPUnitario(BigDecimal PUnitario) {
		this.PUnitario = PUnitario;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatVentasHistoricasId))
			return false;
		CatVentasHistoricasId castOther = (CatVentasHistoricasId) other;

		return (this.getIdProducto() == castOther.getIdProducto()) && (this.getFolio() == castOther.getFolio())
				&& ((this.getCantidad() == castOther.getCantidad()) || (this.getCantidad() != null
						&& castOther.getCantidad() != null && this.getCantidad().equals(castOther.getCantidad())))
				&& ((this.getPUnitario() == castOther.getPUnitario()) || (this.getPUnitario() != null
						&& castOther.getPUnitario() != null && this.getPUnitario().equals(castOther.getPUnitario())))
				&& ((this.getSubTotal() == castOther.getSubTotal()) || (this.getSubTotal() != null
						&& castOther.getSubTotal() != null && this.getSubTotal().equals(castOther.getSubTotal())))
				&& ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus().equals(castOther.getStatus())))
				&& ((this.getFechaLiq() == castOther.getFechaLiq()) || (this.getFechaLiq() != null
						&& castOther.getFechaLiq() != null && this.getFechaLiq().equals(castOther.getFechaLiq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdProducto();
		result = 37 * result + this.getFolio();
		result = 37 * result + (getCantidad() == null ? 0 : this.getCantidad().hashCode());
		result = 37 * result + (getPUnitario() == null ? 0 : this.getPUnitario().hashCode());
		result = 37 * result + (getSubTotal() == null ? 0 : this.getSubTotal().hashCode());
		result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result + (getFechaLiq() == null ? 0 : this.getFechaLiq().hashCode());
		return result;
	}

}