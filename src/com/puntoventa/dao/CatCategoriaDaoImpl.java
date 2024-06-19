package com.puntoventa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatCategoria;

@Repository("catCategoriaDaoImpl")
public class CatCategoriaDaoImpl {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<CatCategoria> getCategorias() {
		List<CatCategoria> categorias = new ArrayList<CatCategoria>();
		String query = new String("SELECT id_categoria, descripcion from cat_categoria");
		categorias = this.jdbcTemplate.query(query, new RowMapper<CatCategoria>() {

			@Override
			public CatCategoria mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				CatCategoria cat = new CatCategoria();
				cat.setIdCategoria(rs.getInt("id_categoria"));
				cat.setDescripcion(rs.getString("descripcion"));

				return cat;
			}

		});
		return categorias;
	}

}
