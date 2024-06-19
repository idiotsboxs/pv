package com.puntoventa.dao;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.puntoventa.hibernate.Usuario;

@Repository("usuarioDaoImpl")
public class UsuarioDaoImpl {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;


	public List<Usuario> getListaUsuario() {

		
		List<Usuario> lista;
	
		lista = (List<Usuario>)this.jdbcTemplate.query("select id_usuario, nombre, paterno, materno, pass, status from usuario",new BeanPropertyRowMapper(Usuario.class));
		
		return lista;

	}
	
	public Usuario getUserByName(Usuario usuario) {

		
		Usuario user = (Usuario) jdbcTemplate.queryForObject(
				"select id_usuario, nombre, paterno, materno, pass, status from usuario WHERE nombre = ?",
				new Object[] { usuario.getNombre() }, new BeanPropertyRowMapper(Usuario.class));

		return user;

	}

	
	
}