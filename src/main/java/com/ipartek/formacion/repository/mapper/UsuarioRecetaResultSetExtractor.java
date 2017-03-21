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

	private static final int ID_A_NULL = 0;

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		HashMap<Long, Usuario> hmUsuarios = new HashMap<Long, Usuario>();
		Usuario usuario = null;
		Receta receta = null;
		ArrayList<Receta> recetas = null;

		long idUsuario = ID_A_NULL;

		// recorrer RS
		while (rs.next()) {

			// recuperar key (idUsuario) de la fila actual (RS)
			idUsuario = rs.getLong("id_usuario");

			// Buscar esa key en la coleccion y recuperar usuario
			if (!hmUsuarios.containsKey(idUsuario)) {

				// "usuario == null --> crear usuario

				usuario = new Usuario();

				usuario.setId(idUsuario);
				usuario.setNombre(rs.getString("nombre_usuario"));
				usuario.setEmail(rs.getString("email_usuario"));
				usuario.setImagen(rs.getString("imagen_usuario"));

				hmUsuarios.put(idUsuario, usuario);

				// } else {

				// "usuario" != null --> No hacer nada

			} // end if

			// recuperar y crear receta

			if (rs.getLong("id_receta") != ID_A_NULL) {
				recetas = new ArrayList<Receta>();
				receta = new Receta();

				receta.setId(rs.getLong("id_receta"));
				receta.setNombre(rs.getString("nombre_receta"));
				receta.setImagen(rs.getString("imagen_receta"));
				receta.setDescripcion(rs.getString("descripcion_receta"));

				// asociar receta al usuario
				recetas = hmUsuarios.get(idUsuario).getRecetas();
				recetas.add(receta);
				// usuario.addReceta(receta);

				// guardar usuario en coleccion

				hmUsuarios.get(idUsuario).setRecetas(recetas);

			} // end if

			// hmUsuarios.put(idUsuario, usuario);

		} // end while

		return hmUsuarios;
	}

}
