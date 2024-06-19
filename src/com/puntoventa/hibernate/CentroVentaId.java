package com.puntoventa.hibernate;
// Generated 21/09/2015 12:10:14 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * CentroVentaId generated by hbm2java
 */
public class CentroVentaId implements java.io.Serializable {

	private int idCve;
	private String nombre;
	private String rfc;
	private String domicilio;
	private Date fechaLiq;

	public CentroVentaId() {
	}

	public CentroVentaId(int idCve) {
		this.idCve = idCve;
	}

	public CentroVentaId(int idCve, String nombre, String rfc, String domicilio, Date fechaLiq) {
		this.idCve = idCve;
		this.nombre = nombre;
		this.rfc = rfc;
		this.domicilio = domicilio;
		this.fechaLiq = fechaLiq;
	}

	public int getIdCve() {
		return this.idCve;
	}

	public void setIdCve(int idCve) {
		this.idCve = idCve;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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
		if (!(other instanceof CentroVentaId))
			return false;
		CentroVentaId castOther = (CentroVentaId) other;

		return (this.getIdCve() == castOther.getIdCve())
				&& ((this.getNombre() == castOther.getNombre()) || (this.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre().equals(castOther.getNombre())))
				&& ((this.getRfc() == castOther.getRfc()) || (this.getRfc() != null && castOther.getRfc() != null
						&& this.getRfc().equals(castOther.getRfc())))
				&& ((this.getDomicilio() == castOther.getDomicilio()) || (this.getDomicilio() != null
						&& castOther.getDomicilio() != null && this.getDomicilio().equals(castOther.getDomicilio())))
				&& ((this.getFechaLiq() == castOther.getFechaLiq()) || (this.getFechaLiq() != null
						&& castOther.getFechaLiq() != null && this.getFechaLiq().equals(castOther.getFechaLiq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdCve();
		result = 37 * result + (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37 * result + (getRfc() == null ? 0 : this.getRfc().hashCode());
		result = 37 * result + (getDomicilio() == null ? 0 : this.getDomicilio().hashCode());
		result = 37 * result + (getFechaLiq() == null ? 0 : this.getFechaLiq().hashCode());
		return result;
	}

}