package com.puntoventa.hibernate;

public class CatProveedor implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899004832456820200L;
	private Integer idProveedor;
	private String proveedor;
	private String descripcion;
	public Integer getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
