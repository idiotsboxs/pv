package com.puntoventa.Impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puntoventa.dao.CatCategoriaDaoImpl;
import com.puntoventa.dao.MovimientoCajaDaoImpl;
import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.hibernate.Ventas;
import com.puntoventa.interfase.Arqueo;
import com.puntoventa.vo.ConcentradoCategoria;

@Service("corteImpl")
public class CorteImpl extends com.puntoventa.dao.VentaDaoImpl implements Arqueo,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MovimientoCajaDaoImpl movimientoCajaDaoImpl;
	@Autowired
	private CatCategoriaDaoImpl catCategoriaDaoImpl;

	@Override
	public List<Ventas> getVentasDelDia(Date fecha_liq) {
		// TODO Auto-generated method stub
		return this.getVentasByDate(fecha_liq);
	}

	@Override
	public void cierreDia() {
		// TODO Auto-generated method stub
		this.sp_cierre_dia();
	}

	@Override
	public ConcentradoCategoria getConcentradoCategoria(Date fecha_liq, CatCategoria categoria) {

		ConcentradoCategoria concentradoCategoria = new ConcentradoCategoria();
		concentradoCategoria.setVentas(this.getVentasByDateCategoria(fecha_liq, categoria));
		BigDecimal total = new BigDecimal("0");
		
		for (CatVentas venta : concentradoCategoria.getVentas()) {
			if(venta.getCatProductos().getCodigoBarras() != 0){
			total = total.add(venta.getSubTotal());
			}
		}

		concentradoCategoria.setTotal(total);

		return concentradoCategoria;

	}

	@Override
	public List<CatCategoria> getCategorias() {
		return this.catCategoriaDaoImpl.getCategorias();
	}

	@Override
	public List<MovimientoCaja> getMovCaja(Date fecha_liq) {
		// TODO Auto-generated method stub

		List<MovimientoCaja> lista = movimientoCajaDaoImpl.getMovCajaByDate(fecha_liq);

		for (MovimientoCaja mov : lista) {

			if (mov.getCatMovCaja().getAfectacion().equals(0)) {

				mov.setMonto(mov.getMonto().multiply(BigDecimal.valueOf(-1)));

			}

		}

		return lista;
	}

}
