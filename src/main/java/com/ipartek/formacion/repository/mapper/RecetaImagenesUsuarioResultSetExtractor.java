package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class RecetaImagenesUsuarioResultSetExtractor implements ResultSetExtractor<HashMap<Long, Receta>> {

	private static final int ID_RECETA_NULL = 0;

	@Override
	public HashMap<Long, Receta> extractData(ResultSet rs) throws SQLException, DataAccessException {

		HashMap<Long, Receta> resul = new HashMap<Long, Receta>();
		Usuario usuario = null;
		Receta receta = null;
		Imagen imagen = null;
		long idImagen = ID_RECETA_NULL;

		// recorrer RS
		while (rs.next()) {

			// recuperar key(idReceta) de fila actual RS
			long key = rs.getLong("receta_id");

			// buscar esa key en coleccion y recuperar "receta"
			receta = resul.get(key);
			// "receta" == null
			if (receta == null) {
				// crear receta
				receta = new Receta();
				receta.setId(key);
				receta.setNombre(rs.getString("receta_nombre"));
				receta.setImagen(rs.getString("receta_imagen"));
				receta.setDescripcion(rs.getString("receta_descripcion"));
				receta.setLikes(rs.getInt("receta_likes"));

				// crear usuario
				usuario = new Usuario();
				usuario.setId(rs.getLong("usuario_id"));
				usuario.setNombre(rs.getString("usuario_nombre"));
				usuario.setEmail(rs.getString("usuario_email"));
				usuario.setImagen(rs.getString("usuario_imagen"));

				receta.setUsuario(usuario);

			}

			// imagenes
			idImagen = rs.getLong("imagen_id");
			if (idImagen != ID_RECETA_NULL) {
				imagen = new Imagen();
				imagen.setId(idImagen);
				imagen.setNombre(rs.getString("imagen_nombre"));

				// asociar imagen a la receta
				receta.addImagen(imagen);
			}

			// guardar usuario en coleccion
			resul.put(key, receta);

		}
		// end while

		return resul;
	}

}
