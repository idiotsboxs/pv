package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatMovCaja;

public interface MovimientoCaja {

	List<CatMovCaja> getCatCaja();
	
	void Guardar(com.puntoventa.hibernate.MovimientoCaja mov);
	
}
