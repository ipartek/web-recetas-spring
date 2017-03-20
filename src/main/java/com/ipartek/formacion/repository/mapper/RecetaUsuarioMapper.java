package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class RecetaUsuarioMapper implements RowMapper<Receta> {

	@Override
	public Receta mapRow(ResultSet rs, int numRow) throws SQLException {

		Receta r = new Receta();

		r.setId(rs.getLong("id"));
		r.setNombre(rs.getString("nombre"));
		r.setImagen(rs.getString("imagen"));
		r.setDescripcion(rs.getString("descripcion"));

		Usuario u = new Usuario();
		u.setId(rs.getLong("user_id"));
		u.setNombre(rs.getString("usuario_nombre"));
		u.setEmail(rs.getString("user_email"));
		u.setImagen(rs.getString("usuario_imagen"));

		return r;
	}

}
