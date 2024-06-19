package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatCompras;
import com.puntoventa.hibernate.CatProductos;

public interface Compra {
	CatProductos getProducto(CatProductos producto);

	boolean guardaCompra(List<CatCompras> compras);

}
