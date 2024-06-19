package com.puntoventa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatMovCaja;

@Repository("catMovCajaDaoImpl")
public class CatMovCajaDaoImpl {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	public List<CatMovCaja> getListaMovimientosCaja() {
	
		List<CatMovCaja> list = new ArrayList<CatMovCaja>();
		
		list  = this.jdbcTemplate.query("select id_movimiento, descripcion, status from cat_mov_caja", new BeanPropertyRowMapper(CatMovCaja.class));
		
		return list;
	}
	
}
