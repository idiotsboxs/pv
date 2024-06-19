package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatVentas;

public interface Impresion {

	void impresionTicket(List<CatVentas> venta);
	
}
