package com.puntoventa.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatVentas;

@Repository("catVentasDaoImpl")
public class CatVentasDaoImpl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(CatVentas ventas){
		
		int res = this.jdbcTemplate.update("insert into cat_ventas (id_producto,folio,cantidad,p_unitario,sub_total,status,fecha_liq) values (?,?,?,?,?,?,? )", 
				new Object[]{ventas.getCatProductos().getIdProducto(),ventas.getVentas().getFolio(),ventas.getCantidad(),ventas.getPUnitario(),ventas.getSubTotal(),ventas.getStatus(),ventas.getFechaLiq()});
		
	}
	
	
}
