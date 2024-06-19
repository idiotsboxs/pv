package com.puntoventa.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.puntoventa.dao.CatProductosDaoImpl;

import com.puntoventa.dao.CatVentasDaoImpl;

import com.puntoventa.dao.CentroVentasDaoImpl;

import com.puntoventa.dao.MovimientoCajaDaoImpl;

import com.puntoventa.dao.VentaDaoImpl;
import com.puntoventa.hibernate.CatMovCaja;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.CentroVenta;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.interfase.VentaMostrador;
@Service("ventaMostradorImpl")
public class VentaMostradorImpl implements VentaMostrador, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CatProductosDaoImpl catProductosDaoImpl;
	@Autowired
	private VentaDaoImpl ventaDaoImpl;
	@Autowired
	private CatVentasDaoImpl catVentasDaoImpl;	
	@Autowired
	private CentroVentasDaoImpl centroVentasDaoImpl;
	@Autowired
	private MovimientoCajaDaoImpl movimientoCajaDaoImpl;
	
	@Override
	public CatProductos getProducto(CatProductos producto) {
		// TODO Auto-generated method stub

		
		
		return catProductosDaoImpl.getProductoByCodeBar(producto);//dao.findById(producto, producto.getIdProducto());

	}
	
	public CatProductos getProductoById(CatProductos producto) {
		// TODO Auto-generated method stub
		
		
		
		return this.catProductosDaoImpl.findById(producto, producto.getIdProducto());

	}
	
	
	@Override
	public boolean guardaVenta(List<CatVentas> ventas) {
		// TODO Auto-generated method stub
		
		
		int cont = 0;
		Integer id = 0;

		for (CatVentas venta : ventas) {

			if (cont == 0) {
				MovimientoCaja mov = new MovimientoCaja();
				CentroVenta cve = this.centroVentasDaoImpl.findById(new CentroVenta(1),1);
				mov.setFecha(cve.getFechaLiq());
				mov.setMonto(venta.getVentas().getTotal());
				CatMovCaja movCaja = new CatMovCaja();
				movCaja.setIdMovimiento(1);
				mov.setCatMovCaja(movCaja);
				mov.setComentario("Venta de mostrador");
				mov.setUsuario(venta.getVentas().getUsuario());
				this.movimientoCajaDaoImpl.save(mov);
				venta.getVentas().setMovimientoCaja(mov);
				this.ventaDaoImpl.save(venta.getVentas());
				cont++;
				id = this.ventaDaoImpl.getFolio();
			}
			venta.getVentas().setFolio(id);
			venta.getCatProductos().setIdProducto(venta.getCatProductos().getIdProducto());
			this.catVentasDaoImpl.save(venta);

		}

		return true;
	}

	
	@Override
	public boolean guardaDevolucion(List<CatVentas> ventas) {
		// TODO Auto-generated method stub
		
		
		int cont = 0;
		Integer id = 0;

		for (CatVentas venta : ventas) {

			if (cont == 0) {
				MovimientoCaja mov = new MovimientoCaja();
				CentroVenta cve = this.centroVentasDaoImpl.findById(new CentroVenta(1),1);
				mov.setFecha(cve.getFechaLiq());
				mov.setMonto(venta.getVentas().getTotal());
				CatMovCaja movCaja = new CatMovCaja();
				movCaja.setIdMovimiento(3);
				mov.setCatMovCaja(movCaja);
				mov.setComentario("Devolucion");
				mov.setUsuario(venta.getVentas().getUsuario());
				this.movimientoCajaDaoImpl.save(mov);
				venta.getVentas().setMovimientoCaja(mov);
				this.ventaDaoImpl.save(venta.getVentas());
				cont++;
				id = this.ventaDaoImpl.getFolio();
			}
			venta.getVentas().setFolio(id);
			venta.getCatProductos().setIdProducto(venta.getCatProductos().getIdProducto());
			this.catVentasDaoImpl.save(venta);

		}

		return true;
	}
}
