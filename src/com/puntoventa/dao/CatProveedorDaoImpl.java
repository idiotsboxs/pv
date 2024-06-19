package com.puntoventa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatProveedor;

@Repository("catProveedorDaoImpl")
public class CatProveedorDaoImpl {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	public List<CatProveedor> getProveedores(){
		
		List<CatProveedor> proveedores = new ArrayList<>();
		String sql = new String("select id_proveedor, proveedor, descripcion from cat_proveedor");
		proveedores = this.jdbcTemplate.query(sql, new RowMapper<CatProveedor>(){

			@Override
			public CatProveedor mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				CatProveedor proveedor = new CatProveedor();
				proveedor.setIdProveedor(rs.getInt("id_proveedor"));
				proveedor.setDescripcion(rs.getString("descripcion"));
				proveedor.setProveedor(rs.getString("proveedor"));
				
				return proveedor;
			}
			
		});
		
		
		return proveedores;
	}

	
}
