package com.puntoventa.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import com.puntoventa.dao.CatCategoriaDaoImpl;
import com.puntoventa.dao.CatProductosDaoImpl;
import com.puntoventa.dao.CatProveedorDaoImpl;
import com.puntoventa.dao.UnidadDaoImpl;
import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatUnidadMedida;
import com.puntoventa.interfase.InventarioABC;

@Service("inventarioABCImpl")
public class InventarioABCImpl extends CatProductosDaoImpl implements InventarioABC,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("unidadDaoImpl")
	public UnidadDaoImpl unidadDaoImpl;
	@Autowired
	@Qualifier("catCategoriaDaoImpl")
	public CatCategoriaDaoImpl catCategoriaDaoImpl;
	@Autowired
	@Qualifier("catProveedorDaoImpl")
	public CatProveedorDaoImpl catProveedorDaoImpl;
	
	
	@Override
	public List<CatUnidadMedida> getUnidadMedida() {
		// TODO Auto-generated method stub
				
		return unidadDaoImpl.getListaUnidad();
	}

	@Override
	public void guardar(CatProductos catProducto) {
		// TODO Auto-generated method stub		
		this.save(catProducto);
		
	}
	
	
	@Override
	public List<CatProductos> getProductLike(CatProductos prod){				
		
		return getProductoByLike(prod);
		
	}

	@Override
	public void editar(CatProductos catProducto) {
		// TODO Auto-generated method stub
		
		this.update(catProducto);
		
	}
	
	@Override
	public List<CatProveedor> getProveedores(){
		
		return this.catProveedorDaoImpl.getProveedores();
		
	}
	
	@Override
	public List<CatCategoria> getCategorias(){
		
		return this.catCategoriaDaoImpl.getCategorias();
	}

}
