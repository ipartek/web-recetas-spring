package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaResultSetExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	// @Override
	// public Usuario mapRow(ResultSet rs, int numRow) throws SQLException {
	//
	// Usuario u = new Usuario();
	//
	// u.setId(rs.getLong("id"));
	// u.setNombre(rs.getString("nombre"));
	// u.setEmail(rs.getString("email"));
	// u.setPassword(rs.getString("password"));
	// u.setImagen(rs.getString("imagen"));
	//
	// return u;
	// }

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		// recorrer el rs

			// recuperar key de rs de la fila actual RS

			// buscar esa key(idUsuario) en coleccion y recuperar "usuario"
		
				// usuario ==Null

					// Crear usuario

				// usuario!=null

					// no hacer nada

			// Recuperar y crear receta
		
			// Asociar receta al usuario

			// Guardar usuario en coleccion
		return null;
	}

}
