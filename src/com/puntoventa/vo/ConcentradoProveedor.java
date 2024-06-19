package com.puntoventa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.puntoventa.hibernate.CatVentas;

public class ConcentradoProveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal totalCompra;
	private BigDecimal total;
	private List<CatVentas> ventas;
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<CatVentas> getVentas() {
		return ventas;
	}
	public void setVentas(List<CatVentas> ventas) {
		this.ventas = ventas;
	}
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
	
	
}
