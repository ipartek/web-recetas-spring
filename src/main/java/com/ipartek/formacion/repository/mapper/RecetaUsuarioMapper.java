package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Receta;

public class RecetaUsuarioMapper implements RowMapper<Receta> {

	@Override
	public Receta mapRow(ResultSet rs, int numRow) throws SQLException {
		Receta r = new Receta();
		r.setId(rs.getLong("id_receta"));
		r.setNombre(rs.getString("nombre_receta"));
		r.setImagen(rs.getString("imagen_receta"));
		r.setDescripcion(rs.getString("descripcion_receta"));
		r.getUsuario().setId(rs.getLong("id_usuario"));
		r.getUsuario().setNombre(rs.getString("nombre_usuario"));
		r.getUsuario().setImagen(rs.getString("imagen_usuario"));
		r.getUsuario().setEmail(rs.getString("email_usuario"));
		return r;
	}

}
