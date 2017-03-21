package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

/**
 *
 * @author Curso
 *
 */
public class UsuarioRecetaResultSetExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	// @Override()
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

	@Override()
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {

		HashMap<Long, Usuario> map = new HashMap<Long, Usuario>();
		Usuario usr = null;
		Receta receta = null;
		Long ID_RECETA_NULL = (long) 0;

		// reecorrer el rs
		while (rs.next()) {
			// recuperar key de rs de la fila actual RS

			Long usrId = rs.getLong("usuario_id");
			// buscar esa key(idUsuario) en coleccion y recuperar "usuario"
			if (map.containsKey(usrId)) {

			} else {
				String usrNombre = rs.getString("usuario_nombre");
				String usrEmail = rs.getString("usuario_email");
				String usrImagen = rs.getString("usuario_imagen");
				usr = new Usuario();
				usr.setId(usrId);
				usr.setEmail(usrEmail);
				usr.setNombre(usrNombre);
				usr.setImagen(usrImagen);
				map.put(usrId, usr);

			}
			// Recuperar y crear receta

			Long recetaId = rs.getLong("receta_id");
			if (recetaId != ID_RECETA_NULL) {
				receta = new Receta();
				String recetaNombre = (String) rs.getObject("receta_nombre");
				String recetaDescripcion = (String) rs.getObject("receta_descripcion");
				String recetaImagen = (String) rs.getObject("receta_imagen");
				receta.setDescripcion(recetaDescripcion);
				receta.setId(recetaId);
				receta.setImagen(recetaImagen);
				receta.setNombre(recetaNombre);
				// Asociar receta al usuario
				usr = map.get(usrId);
				usr.addReceta(receta);

				// Guardar usuario en coleccion
				map.put(usrId, usr);

			}

		}
		return map;
	}
}
