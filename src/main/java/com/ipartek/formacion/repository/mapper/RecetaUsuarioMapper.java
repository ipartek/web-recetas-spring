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

		r.setId(rs.getLong("id_receta"));
		r.setNombre(rs.getString("nombre_receta"));
		r.setImagen(rs.getString("imagen_receta"));
		r.setDescripcion(rs.getString("descripcion_receta"));

		Usuario u = new Usuario();

		u.setId(rs.getLong("id_usuario"));
		u.setNombre(rs.getString("nombre_usuario"));
		u.setEmail(rs.getString("email_usuario"));
		u.setImagen(rs.getString("imagen_usuario"));

		r.setUsuario(u);

		return r;
	}

}
