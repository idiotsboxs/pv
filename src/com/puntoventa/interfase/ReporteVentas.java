package com.puntoventa.interfase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.VwVentasId;
import com.puntoventa.vo.ConcentradoProveedor;

public interface ReporteVentas  {

	List<VwVentasId> getVentasPorDia(final Calendar diaUno, final Calendar diaDos);

	ConcentradoProveedor getConcentradoProveedor(Date fecha_liqIni, Date fecha_liqFin, CatProveedor proveedor);


	
}
