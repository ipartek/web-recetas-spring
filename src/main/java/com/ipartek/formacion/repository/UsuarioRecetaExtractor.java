package com.ipartek.formacion.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaExtractor implements ResultSetExtractor<Map<Integer, Usuario>> {

	@Override
	public Map<Integer, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

		while (rs.next()) {

			int idUsuario = rs.getInt("usuario_id");

			// buscamos receta para saber si se ha creado
			Usuario usuario = usuarios.get(idUsuario);
			if (usuario != null) {
				usuario.setId(idUsuario);
			}

			//rellenar recetas del usuario actual
			
		}

		return usuarios;
	}

}
