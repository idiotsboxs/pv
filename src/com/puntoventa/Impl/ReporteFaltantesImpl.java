package com.puntoventa.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puntoventa.dao.CatProductosDaoImpl;
import com.puntoventa.dao.CatProveedorDaoImpl;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.interfase.ReporteFaltantes;
@Service("reporteFaltantesImpl")
public class ReporteFaltantesImpl implements ReporteFaltantes, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CatProductosDaoImpl catProductosDaoImpl;
	@Autowired
	private CatProveedorDaoImpl catProveedorDaoImpl;
	
	
	@Override
	public List<CatProductos> getFaltantes(final CatProductos producto) {
		// TODO Auto-generated method stub
		return catProductosDaoImpl.getFaltantes(producto);
	}

	public List<CatProveedor> getProveedores(){
		return this.catProveedorDaoImpl.getProveedores();
	}
	
}
