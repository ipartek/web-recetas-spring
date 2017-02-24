package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario u = new Usuario();

		u.setId(rs.getLong("id"));
		u.setNombre(rs.getString("nombre"));
		u.setEmail(rs.getString("email"));
		u.setImagen(rs.getString("imagen"));
		u.setPassword(rs.getString("password"));

		return u;
	}

}
