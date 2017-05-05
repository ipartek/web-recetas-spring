package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaResultSetExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	private static final int ID_RECETA_NULL = 0;

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		HashMap<Long, Usuario> resul = new HashMap<Long, Usuario>();
		Usuario usuario = null;
		Receta receta = null;
		long idReceta = ID_RECETA_NULL;
		// recorrer RS
		while (rs.next()) {
			// recuperar key(idUsuario) de fila actual RS
			long key = rs.getLong("usuario_id");

			// buscar esa key en coleccion y recuperar "usuario"
			usuario = resul.get(key);
			// "usuario" == null
			if (usuario == null) {
				// crear usuario
				usuario = new Usuario();
				usuario.setId(key);
				usuario.setNombre(rs.getString("usuario_nombre"));
				usuario.setEmail(rs.getString("usuario_email"));
				usuario.setImagen(rs.getString("usuario_imagen"));
			}
			// receta
			idReceta = rs.getLong("receta_id");
			if (idReceta != ID_RECETA_NULL) {
				receta = new Receta();
				receta.setId(idReceta);
				receta.setNombre(rs.getString("receta_nombre"));
				receta.setImagen(rs.getString("receta_imagen"));
				receta.setDescripcion(rs.getString("receta_descripcion"));
				receta.setLikes(rs.getInt("receta_likes"));

				// asociar receta al usuario
				usuario.addReceta(receta);
			}

			// guardar usuario en coleccion
			resul.put(key, usuario);

		}
		// end while

		return resul;
	}

}
