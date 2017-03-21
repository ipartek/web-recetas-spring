package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class RecetaUsuarioMapper implements RowMapper<Receta> {

	@Override()
	public Receta mapRow(ResultSet rs, int row) throws SQLException {

		Receta r = new Receta();

		r.setId(rs.getLong("receta_id"));
		r.setNombre(rs.getString("receta_nombre"));
		r.setImagen(rs.getString("receta_imagen"));
		r.setDescripcion(rs.getString("receta_descripcion"));

		Usuario u = new Usuario();
		u.setId(rs.getLong("usuario_id"));
		u.setNombre(rs.getString("usuario_nombre"));
		u.setEmail(rs.getString("usuario_email"));
		u.setImagen(rs.getString("usuario_imagen"));

		r.setUsuario(u);

		return r;
	}

}
