package com.puntoventa.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CentroVenta;

@Repository("centroVentasDaoImpl")
public class CentroVentasDaoImpl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	public CentroVenta findById(CentroVenta centroVenta,Integer id){
		
		return (CentroVenta)this.jdbcTemplate.queryForObject("select id_cve,nombre,rfc,domicilio,fecha_liq from centro_venta where id_cve = ? ", new Object[]{id}, new BeanPropertyRowMapper(centroVenta.getClass()));
		
	}
	
	
}
