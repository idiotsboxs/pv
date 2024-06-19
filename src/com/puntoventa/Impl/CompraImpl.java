package com.puntoventa.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.puntoventa.dao.CatComprasDaoImpl;
import com.puntoventa.dao.CatProductosDaoImpl;
import com.puntoventa.dao.CompraDaoImpl;
import com.puntoventa.hibernate.CatCompras;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.Compras;
import com.puntoventa.interfase.Compra;
@Service("compraImpl")
public class CompraImpl implements Compra, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	CatProductosDaoImpl catProductosDaoImpl;
	@Autowired
	CompraDaoImpl compraDaoImpl;
	@Autowired
	CatComprasDaoImpl catComprasDaoImpl;
	
	@Override
	public CatProductos getProducto(CatProductos producto) {
		// TODO Auto-generated method stub
		

		return catProductosDaoImpl.getProductoByCodeBar(producto);
	}

	@Override
	public boolean guardaCompra(List<CatCompras> compras) {
		// TODO Auto-generated method stub
		
		
		int cont = 0;
		Integer id = 0;

		for (CatCompras compra : compras) {

			if (cont == 0) {
				compraDaoImpl.save(compra.getCompras());
				cont++;
				id = compraDaoImpl.getFolio();
			}
			Compras idCompra = new Compras();
			idCompra.setIdCompras(id);
			compra.setCompras(idCompra);
			compra.getId().setIdCompras(id);				
			catComprasDaoImpl.save(compra);

		}

		return true;
	}

}
