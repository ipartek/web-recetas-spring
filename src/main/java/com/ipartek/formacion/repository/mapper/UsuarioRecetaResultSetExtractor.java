package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaResultSetExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		HashMap<Long, Usuario> hmUsuarios = new HashMap<Long, Usuario>();
		Usuario usuario;
		Receta receta;
		ArrayList<Receta> recetas;

		long idUsuario;

		// recorrer RS
		while (rs.next()) {

			usuario = new Usuario();
			receta = new Receta();
			recetas = new ArrayList<Receta>();

			// recuperar key (idUsuario) de la fila actual (RS)
			idUsuario = rs.getLong("id_usuario");

			// Buscar esa key en la coleccion y recuperar usuario
			if (!hmUsuarios.containsKey(idUsuario)) {

				// "usuario == null --> crear usuario

				usuario.setId(idUsuario);
				usuario.setNombre(rs.getString("nombre_usuario"));
				usuario.setEmail(rs.getString("email_usuario"));
				usuario.setImagen(rs.getString("imagen_usuario"));
				// guardar ArrayList<Receta> inicializada para que no sea null
				usuario.setRecetas(recetas);

				hmUsuarios.put(idUsuario, usuario);

			} else {

				// "usuario" != null --> No hacer nada

			}

			// recuperar y crear receta

			if (rs.getLong("id_receta") != 0) {
				receta.setId(rs.getLong("id_receta"));
				receta.setNombre(rs.getString("nombre_receta"));
				receta.setImagen(rs.getString("imagen_receta"));
				receta.setDescripcion(rs.getString("descripcion_receta"));

				// asociar receta al usuario
				recetas = hmUsuarios.get(idUsuario).getRecetas();
				recetas.add(receta);

				// usuario.setRecetas(recetas);

				// guardar usuario en coleccion

				hmUsuarios.get(idUsuario).setRecetas(recetas);
			}
		}

		return hmUsuarios;
	}

}
