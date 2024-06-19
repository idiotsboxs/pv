package com.puntoventa.interfase;

import java.util.Date;
import java.util.List;

import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.hibernate.Ventas;
import com.puntoventa.vo.ConcentradoCategoria;

public interface Arqueo {

	List<Ventas> getVentasDelDia(Date fecha_liq);
	void cierreDia();
	List<MovimientoCaja> getMovCaja(Date fecha_liq);
	ConcentradoCategoria getConcentradoCategoria(Date fecha_liq, CatCategoria categoria);
	List<CatCategoria> getCategorias();
}
