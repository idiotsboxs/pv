package com.puntoventa.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.puntoventa.hibernate.CatCategoria;
import com.puntoventa.hibernate.CatProductos;
import com.puntoventa.hibernate.CatProveedor;
import com.puntoventa.hibernate.CatUnidadMedida;

@Repository("catProductosDaoImpl")
public class CatProductosDaoImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public CatProductos getProductoByCodeBar(CatProductos producto) {

		CatProductos catProductos = null;

		try {

			catProductos = (CatProductos) this.jdbcTemplate.queryForObject(
					"select p.id_producto,p.desc_producto,p.id_unidad_medida,p.cantidad,p.p_u_compra,p.p_u_venta,p.minimo,p.iva,p.p_u_mayoreo,p.p_s_mayoreo,p.factor,p.codigo_barras,p.id_tipo_abastecimiento,m.des_unidad_medida,pv.id_proveedor,pv.proveedor, c.id_categoria, c.descripcion from cat_productos p left join cat_unidad_medida m on m.id_unidad_medida = p.id_unidad_medida left join  cat_proveedor pv on pv.id_proveedor = p.id_proveedor left join  cat_categoria c on c.id_categoria = p.id_categoria where  codigo_barras = ? ",
					new Object[] { producto.getCodigoBarras() }, new CatProductoRowMapper());
		} catch (EmptyResultDataAccessException emptyEx) {

			catProductos = new CatProductos();
			catProductos.setCatUnidadMedida(new CatUnidadMedida());

		}

		return catProductos;

	}

	public List<CatProductos> getFaltantes(final CatProductos productos) {

		List<CatProductos> lista;
		StringBuffer query = new StringBuffer(
				"select desc_producto,cantidad,minimo,p_u_compra,p_u_venta,codigo_barras,factor, 'true' as faltante from cat_productos where minimo >= cantidad and id_tipo_abastecimiento = ? ");
		query.append("union select desc_producto,cantidad,minimo,p_u_compra,p_u_venta,codigo_barras,factor, 'false' as faltante from cat_productos where minimo < cantidad and id_tipo_abastecimiento = ? ");
		Object[] params = new Object[] { productos.getIdTipoAbastecimiento(), productos.getIdTipoAbastecimiento() };
		if (productos.getCatProveedor().getIdProveedor() != null) {
			query.append(" and id_proveedor = ?");
			params = new Object[] { productos.getIdTipoAbastecimiento(), productos.getCatProveedor().getIdProveedor(),productos.getIdTipoAbastecimiento(), productos.getCatProveedor().getIdProveedor() };
		}
		lista = (List<CatProductos>) this.jdbcTemplate.query(query.toString(), params, new RowMapper<CatProductos>() {

			@Override
			public CatProductos mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub

				CatProductos producto = new CatProductos();

				producto.setDescProducto(rs.getString("desc_producto"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setFactor(rs.getInt("factor"));
				producto.setPUCompra(rs.getBigDecimal("p_u_compra"));
				producto.setPUVenta(rs.getBigDecimal("p_u_venta"));
				producto.setCodigoBarras(rs.getLong("codigo_barras"));
				producto.setMinimo(rs.getInt("minimo"));
				producto.setFaltante(rs.getBoolean("faltante"));
				return producto;
			}

		});

		return lista;

	}

	public List<CatProductos> getListaCatProductos() {

		List<CatProductos> lista;
		lista = (List<CatProductos>) this.jdbcTemplate.query(
				"select id_producto,desc_producto,id_unidad_medida,cantidad,p_u_compra,p_u_venta,minimo,iva,p_u_mayoreo,p_s_mayoreo,factor,codigo_barras from cat_productos ",
				new BeanPropertyRowMapper(CatProductos.class));

		return lista;

	}

	public List<CatProductos> getProductoByLike(CatProductos prod) {

		List<CatProductos> lista;

		lista = (List<CatProductos>) this.jdbcTemplate.query(
				"select p.id_producto,p.desc_producto,p.id_unidad_medida,p.cantidad,p.p_u_compra,p.p_u_venta,p.minimo,p.iva,p.p_u_mayoreo,p.p_s_mayoreo,p.factor,p.codigo_barras,p.id_tipo_abastecimiento,m.des_unidad_medida,pv.id_proveedor,pv.proveedor, c.id_categoria, c.descripcion from cat_productos p left join cat_unidad_medida m on m.id_unidad_medida = p.id_unidad_medida left join  cat_proveedor pv on pv.id_proveedor = p.id_proveedor left join  cat_categoria c on c.id_categoria = p.id_categoria where p.desc_producto like ? ",
				new Object[] { "%" + prod.getDescProducto() + "%" }, new CatProductoRowMapper());

		return lista;
	}

	public void save(CatProductos catProducto) {
		// TODO Auto-generated method stub
		int res = this.jdbcTemplate.update(
				"insert into cat_productos (desc_producto,id_unidad_medida,cantidad,p_u_compra,p_u_venta,minimo,iva,p_u_mayoreo,p_s_mayoreo,factor,codigo_barras,id_proveedor,id_categoria, id_tipo_abastecimiento) values (?,?,?,?,?,?,?,?,?,?,?,?,?,? )",
				new Object[] { catProducto.getDescProducto(), catProducto.getCatUnidadMedida().getIdUnidadMedida(),
						catProducto.getCantidad(), catProducto.getPUCompra(), catProducto.getPUVenta(),
						catProducto.getMinimo(), catProducto.getIva(), catProducto.getPUMayoreo(),
						catProducto.getPSMayoreo(), catProducto.getFactor(), catProducto.getCodigoBarras(),
						catProducto.getCatProveedor().getIdProveedor(), catProducto.getCatCategoria().getIdCategoria(),
						catProducto.getIdTipoAbastecimiento() });
	}

	public void update(CatProductos catProducto) {
		// TODO Auto-generated method stub

		int res = this.jdbcTemplate.update(
				"update cat_productos set  desc_producto=?,id_unidad_medida=?,cantidad=?,p_u_compra =?,p_u_venta=?,minimo=?,iva =?,p_u_mayoreo=?,p_s_mayoreo=?,factor=?,codigo_barras=?,id_proveedor = ?,id_categoria = ?, id_tipo_abastecimiento = ? where id_producto = ?",
				new Object[] { catProducto.getDescProducto(), catProducto.getCatUnidadMedida().getIdUnidadMedida(),
						catProducto.getCantidad(), catProducto.getPUCompra(), catProducto.getPUVenta(),
						catProducto.getMinimo(), catProducto.getIva(), catProducto.getPUMayoreo(),
						catProducto.getPSMayoreo(), catProducto.getFactor(), catProducto.getCodigoBarras(),
						catProducto.getCatProveedor().getIdProveedor(), catProducto.getCatCategoria().getIdCategoria(),
						catProducto.getIdTipoAbastecimiento(), catProducto.getIdProducto() });

	}

	public CatProductos findById(CatProductos catProductos, Integer id) {

		CatProductos prod = (CatProductos) this.jdbcTemplate.queryForObject(
				"select p.id_producto,p.desc_producto,p.id_unidad_medida,p.cantidad,p.p_u_compra,p.p_u_venta,p.minimo,p.iva,p.p_u_mayoreo,p.p_s_mayoreo,p.factor,p.codigo_barras, m.des_unidad_medida from cat_productos p, cat_unidad_medida m where m.id_unidad_medida = p.id_unidad_medida and id_producto = ?",
				new Object[] { id }, new CatProductoRowMapper());

		
		return prod; 
	}

	public class CatProductoRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			CatProductos prod = new CatProductos();
			CatProveedor prov = new CatProveedor();
			CatCategoria cate = new CatCategoria();
			cate.setIdCategoria(rs.getInt("id_categoria"));
			cate.setDescripcion(rs.getString("descripcion"));
			prod.setIdProducto(rs.getInt("id_producto"));
			prod.setDescProducto(rs.getString("desc_producto"));
			CatUnidadMedida uni = new CatUnidadMedida();
			uni.setIdUnidadMedida(rs.getInt("id_unidad_medida"));
			uni.setDesUnidadMedida(rs.getString("des_unidad_medida"));
			prov.setProveedor(rs.getString("proveedor"));
			prov.setIdProveedor(rs.getInt("id_proveedor"));
			prod.setCatUnidadMedida(uni);
			prod.setCantidad(rs.getInt("cantidad"));
			prod.setPUCompra(rs.getBigDecimal("p_u_compra"));
			prod.setPUVenta(rs.getBigDecimal("p_u_venta"));
			prod.setMinimo(rs.getInt("minimo"));
			prod.setIva(rs.getInt("iva"));
			prod.setPUMayoreo(rs.getBigDecimal("p_u_mayoreo"));
			prod.setPSMayoreo(rs.getBigDecimal("p_s_mayoreo"));
			prod.setFactor(rs.getInt("factor"));
			prod.setCodigoBarras(rs.getLong("codigo_barras"));
			prod.setIdTipoAbastecimiento(rs.getInt("id_tipo_abastecimiento"));
			prod.setCatCategoria(cate);
			prod.setCatProveedor(prov);

			return prod;
		}

	}

}
