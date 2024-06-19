package com.puntoventa.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatMovCaja;
import com.puntoventa.hibernate.MovimientoCaja;
import com.puntoventa.hibernate.Usuario;

@Repository("movimientoCajaDaoImpl")
public class MovimientoCajaDaoImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(MovimientoCaja movimientoCaja) {

		int res = this.jdbcTemplate.update(
				"Insert into movimiento_caja (id_cat_mov,monto,comentario,fecha,id_usuario) values (?,?,?,?,?) ",
				new Object[] { movimientoCaja.getCatMovCaja().getIdMovimiento(), movimientoCaja.getMonto(),
						movimientoCaja.getComentario(), movimientoCaja.getFecha(),
						movimientoCaja.getUsuario().getIdUsuario() });
		movimientoCaja.setIdMovCaja(
				this.jdbcTemplate.queryForObject("select max(id_mov_caja) from movimiento_caja", Integer.class));

	}

	public List<MovimientoCaja> getMovCajaByDate(Date fechaliq) {

		List<MovimientoCaja> list = this.jdbcTemplate.query(
				"select c.id_mov_caja,c.id_cat_mov,c.monto,c.comentario,c.fecha,c.id_usuario,m.descripcion,m.afectacion,m.status from movimiento_caja c ,cat_mov_caja m  where c.id_cat_mov = m.id_movimiento and fecha = ?",
				new Object[] { fechaliq }, new MovCajRowMapper());

		return list;

	}

	class MovCajRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			MovimientoCaja mov = new MovimientoCaja();
			CatMovCaja catMov = new CatMovCaja();
			mov.setIdMovCaja(rs.getInt("id_mov_caja"));
			catMov.setIdMovimiento(rs.getInt("id_cat_mov"));
			catMov.setDescripcion(rs.getString("descripcion"));
			catMov.setAfectacion(rs.getInt("afectacion"));
			catMov.setStatus(rs.getInt("status"));
			mov.setCatMovCaja(catMov);
			mov.setMonto(rs.getBigDecimal("monto"));
			mov.setComentario(rs.getString("comentario"));
			mov.setFecha(rs.getDate("fecha"));
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("id_usuario"));
			mov.setUsuario(usuario);

			return mov;
		}

	}

}
