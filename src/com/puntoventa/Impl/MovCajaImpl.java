package com.puntoventa.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.puntoventa.dao.CatMovCajaDaoImpl;
import com.puntoventa.dao.CentroVentasDaoImpl;
import com.puntoventa.dao.MovimientoCajaDaoImpl;
import com.puntoventa.hibernate.CatMovCaja;
import com.puntoventa.hibernate.CentroVenta;
import com.puntoventa.interfase.MovimientoCaja;
@Service("movCajaImpl")
public class MovCajaImpl extends MovimientoCajaDaoImpl implements MovimientoCaja, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("centroVentasDaoImpl")
	public CentroVentasDaoImpl centroVentasDaoImpl;
	@Autowired
	@Qualifier("catMovCajaDaoImpl")
	public CatMovCajaDaoImpl catMovCajaDaoImpl;
	
	@Override
	public List<CatMovCaja> getCatCaja() {		
		// TODO Auto-generated method stub
		return catMovCajaDaoImpl.getListaMovimientosCaja();
	}

	@Override
	public void Guardar(com.puntoventa.hibernate.MovimientoCaja mov) {
		// TODO Auto-generated method stub
		
		
		CentroVenta cve = centroVentasDaoImpl.findById(new CentroVenta(1),1);
		mov.setFecha(cve.getFechaLiq());
		save(mov);
		
	}

   

}
