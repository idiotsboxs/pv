package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;

public interface ReporteFaltantes {
	
	List<CatProductos> getFaltantes(final CatProductos producto);
	List<CatProveedor> getProveedores();

}
