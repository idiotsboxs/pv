package com.puntoventa.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.Compras;

@Repository("compraDaoImpl")
public class CompraDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void save(Compras compras){
		
		int res = this.jdbcTemplate.update("insert into compras (fecha,id_usuario,total,sub_total,iva,status,nota, id_doc) values (?,?,?,?,?,?,?,?)",
				new Object[]{compras.getFecha(),compras.getIdUsuario(),compras.getTotal(),compras.getSubTotal(),compras.getIva(),compras.getStatus(),compras.getNota(),compras.getDocumento().getIdDoc()});
		
	}
	
public Integer getFolio(){
						
		Integer folio;
		folio =  this.jdbcTemplate.queryForObject("select max(id_compras) from compras", Integer.class); 
		
		return folio;
	}

@SuppressWarnings("unchecked")
public List<Compras> getComprasByDate(Date fechaliq){
	List<Compras> compras = new ArrayList<Compras>();
	
	compras = (List<Compras>) jdbcTemplate.query("select id_compras,fecha,id_usuario,total,sub_total,iva,status,nota, id_doc from compras where fecha = ? ", new Object[]{fechaliq},new BeanPropertyRowMapper(Compras.class) );
	
	return compras;
}
	
}
