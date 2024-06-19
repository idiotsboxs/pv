package com.puntoventa.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.VwVentas;
import com.puntoventa.hibernate.VwVentasId;

@Repository("vwVentasDaoImpl")
public class VwVentasDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<VwVentasId> getListaVetasPorDia(final Calendar diaUno, final Calendar diaDos) {

		List<VwVentasId> lista;
		
	    lista  = (List<VwVentasId>)this.jdbcTemplate.query("select total, fecha_liq from vw_ventas where fecha_liq between  ? and ? order by fecha_liq",new Object[]{diaUno.getTime(),diaDos.getTime()}, new BeanPropertyRowMapper(VwVentasId.class));
		
		return lista;
	}
	
}
