package com.puntoventa.interfase;

import java.util.List;

import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatVentas;

public interface VentaMostrador  {

 CatProductos getProducto(CatProductos producto);
 
 boolean guardaVenta(List<CatVentas> ventas);

CatProductos getProductoById(CatProductos producto);

boolean guardaDevolucion(List<CatVentas> ventas);
 
}
