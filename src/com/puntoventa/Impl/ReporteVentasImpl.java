package com.puntoventa.Impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puntoventa.dao.VentaDaoImpl;
import com.puntoventa.dao.VwVentasDaoImpl;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.VwVentasId;
import com.puntoventa.interfase.ReporteVentas;
import com.puntoventa.vo.ConcentradoProveedor;
@Repository("reporteVentasImpl")
public class ReporteVentasImpl extends VwVentasDaoImpl implements ReporteVentas, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VentaDaoImpl ventaDaoImpl;
	
	@Override
	public List<VwVentasId> getVentasPorDia(final Calendar diaUno, final Calendar diaDos) {
		// TODO Auto-generated method stub		
		return this.getListaVetasPorDia( diaUno, diaDos);
	}
	
	@Override
	public ConcentradoProveedor getConcentradoProveedor(Date fecha_liqIni,Date fecha_liqFin , CatProveedor proveedor) {

		ConcentradoProveedor concentradoProveedor = new ConcentradoProveedor();
		concentradoProveedor.setVentas(ventaDaoImpl.getVentasByDateProveedor(fecha_liqIni,fecha_liqFin, proveedor));
		BigDecimal total = new BigDecimal("0");
		BigDecimal totalPCompra = new BigDecimal("0");
		
		for (CatVentas venta : concentradoProveedor.getVentas()) {
			if(venta.getCatProductos().getCodigoBarras() != 0){
			total = total.add(venta.getSubTotal());
			totalPCompra = totalPCompra.add(venta.getCatProductos().getPUCompra());
			}
		}

		concentradoProveedor.setTotal(total);
		concentradoProveedor.setTotalCompra(totalPCompra);

		return concentradoProveedor;

	}


}
