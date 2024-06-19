package com.puntoventa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatCompras;


@Repository("catComprasDaoImpl")
public class CatComprasDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void save(CatCompras catCompras){
		
		int res =  this.jdbcTemplate.update("insert into cat_compras (id_compras,id_producto,cantidad,p_compra,sub_total,status,p_venta) values (?,?,?,?,?,?,?)", 
				new Object[]{catCompras.getId().getIdCompras(),catCompras.getId().getIdProducto(),catCompras.getCantidad(),catCompras.getPCompra(),catCompras.getSubTotal(),catCompras.getStatus(),catCompras.getPVenta()});
		
		
	}
	
	
}
