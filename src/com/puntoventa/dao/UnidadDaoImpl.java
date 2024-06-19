package com.puntoventa.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatUnidadMedida;
@Repository("unidadDaoImpl")
public class UnidadDaoImpl {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	
	public List<CatUnidadMedida> getListaUnidad() {

		List<CatUnidadMedida> lista;
		
		lista =  (List<CatUnidadMedida>)this.jdbcTemplate.query("Select id_unidad_medida, des_unidad_medida from cat_unidad_medida", new BeanPropertyRowMapper(CatUnidadMedida.class));
		

		return lista;

	}
	
}
