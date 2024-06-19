package com.puntoventa.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.Ventas;

@Repository("ventaDaoImpl")
public class VentaDaoImpl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	
public Integer getFolio(){
		
		
		
		Integer folio = (Integer)this.jdbcTemplate.queryForObject("select max(folio) from ventas", Integer.class);
				
		
		return folio;
	}
	
	
	public List<Ventas> getVentasByDate(Date fechaliq){
		
		List<Ventas> ventas = new ArrayList<Ventas>();		
	
		ventas = this.jdbcTemplate.query("Select folio,fecha,id_user,total,sub_total,iva,status,fecha_liq,id_doc,id_mov_caja from ventas where fecha_liq = ?", 
				new Object[]{fechaliq},new BeanPropertyRowMapper(Ventas.class));
		
		
		return  ventas;
		
	}
	

	public List<CatVentas> getVentasByDateCategoria(Date fechaliq,CatCategoria categoria){
		
		List<CatVentas> ventas = new ArrayList<CatVentas>();		
		StringBuffer query = new StringBuffer("select p.codigo_barras,p.desc_producto,sum(v.cantidad) cantidad,sum(v.sub_total) sub_total from cat_ventas v inner join cat_productos p on p.id_producto = v.id_producto where fecha_liq = ? and p.id_categoria =  ? group by codigo_barras,desc_producto ");
		query.append(" union ");
		query.append(" select p.codigo_barras,p.desc_producto,sum(v.cantidad) cantidad,sum(v.sub_total) sub_total from cat_ventas_historicas v inner join cat_productos p on p.id_producto = v.id_producto where fecha_liq = ? and p.id_categoria =  ? group by codigo_barras,desc_producto ");
		ventas = this.jdbcTemplate.query(query.toString(), 
				new Object[]{fechaliq,categoria.getIdCategoria(),fechaliq,categoria.getIdCategoria()},new RowMapper<CatVentas>(){

					@Override
					public CatVentas mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						CatVentas cat = new CatVentas();
						CatProductos prod = new CatProductos();
						prod.setCodigoBarras(rs.getLong("codigo_barras"));
						prod.setDescProducto(rs.getString("desc_producto"));
						cat.setCantidad(rs.getInt("cantidad"));
						cat.setSubTotal(rs.getBigDecimal("sub_total"));
						cat.setCatProductos(prod);
						
						return cat;
					}
			
		});
		
		
		return  ventas;
		
	}



	public List<CatVentas> getVentasByDateProveedor(Date fechaliqIni,Date fechaliqFin,CatProveedor catProveedor){
		
		List<CatVentas> ventas = new ArrayList<CatVentas>();		
		StringBuffer query = new StringBuffer("select p.codigo_barras,p.desc_producto,sum(v.cantidad) cantidad,p.p_u_compra * sum(v.cantidad) p_u_compra,p.p_u_venta* sum(v.cantidad) p_u_venta, sum(v.sub_total) sub_total from cat_ventas v inner join cat_productos p on p.id_producto = v.id_producto where (fecha_liq between  ? and ? ) and p.id_proveedor =  ? group by codigo_barras,desc_producto ");
		query.append(" union ");
		query.append(" select p.codigo_barras,p.desc_producto,sum(v.cantidad) cantidad ,p.p_u_compra * sum(v.cantidad) p_u_compra,p.p_u_venta* sum(v.cantidad) p_u_venta,sum(v.sub_total) sub_total from cat_ventas_historicas v inner join cat_productos p on p.id_producto = v.id_producto where (fecha_liq between  ? and ?) and p.id_proveedor =  ? group by codigo_barras,desc_producto ");
		ventas = this.jdbcTemplate.query(query.toString(), 
				new Object[]{fechaliqIni,fechaliqFin,catProveedor.getIdProveedor(),fechaliqIni,fechaliqFin,catProveedor.getIdProveedor()},new RowMapper<CatVentas>(){

					@Override
					public CatVentas mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						CatVentas cat = new CatVentas();
						CatProductos prod = new CatProductos();
						prod.setPUCompra(rs.getBigDecimal("p_u_compra"));
						prod.setPUVenta(rs.getBigDecimal("p_u_venta"));
						prod.setCodigoBarras(rs.getLong("codigo_barras"));
						prod.setDescProducto(rs.getString("desc_producto"));
						cat.setCantidad(rs.getInt("cantidad"));
						cat.setSubTotal(rs.getBigDecimal("sub_total"));
						cat.setCatProductos(prod);
						
						return cat;
					}
			
		});
		
		
		return  ventas;
		
	}

	

	public void sp_cierre_dia(){

		this.jdbcTemplate.update("call sp_cierre_dia()");
	
	}
	
	
	public void save(Ventas ventas){
		
		int res  = this.jdbcTemplate.update("insert into ventas (fecha,id_user,total,sub_total,iva,status,fecha_liq,id_doc,id_mov_caja) values (?,?,?,?,?,?,?,?,?)",
				new Object[]{ventas.getFecha(),ventas.getUsuario().getIdUsuario(),ventas.getTotal(),ventas.getSubTotal(),ventas.getIva(),ventas.getStatus(),
						ventas.getFechaLiq(),ventas.getDocumento().getIdDoc(),ventas.getMovimientoCaja().getIdMovCaja()});
		
	}
	
}
