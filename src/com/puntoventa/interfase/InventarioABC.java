package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatUnidadMedida;

public interface InventarioABC {

	List<CatUnidadMedida> getUnidadMedida();
	void guardar(CatProductos catProducto);
	List<CatProductos> getProductLike(CatProductos prod);
	void editar(CatProductos catProducto);
	List<CatProveedor> getProveedores();
	List<CatCategoria> getCategorias();
}
