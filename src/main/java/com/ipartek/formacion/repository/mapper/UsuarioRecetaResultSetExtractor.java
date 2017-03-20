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

		HashMap<Long, Usuario> map = new HashMap<Long, Usuario>();
		Usuario usr = null;
		Receta receta = null;
		ArrayList<Receta> recetas = null;
		// reecorrer el rs
		while (rs.next()) {
			// recuperar key de rs de la fila actual RS
			Integer usrIdInt = (Integer) rs.getObject("usuario_id");
			Long usrId = usrIdInt.longValue();
			// buscar esa key(idUsuario) en coleccion y recuperar "usuario"
			if (map.containsKey(usrId)) {

			} else {
				String usrNombre = (String) rs.getObject("usuario_nombre");
				String usrEmail = (String) rs.getObject("usuario_email");
				String usrImagen = (String) rs.getObject("usuario_imagen");
				recetas = new ArrayList<Receta>();
				usr = new Usuario();
				usr.setId(usrId);
				usr.setEmail(usrEmail);
				usr.setNombre(usrNombre);
				usr.setImagen(usrImagen);
				usr.setRecetas(recetas);
				usr.setRecetas(recetas);
				map.put(usrId, usr);

			}
			// Recuperar y crear receta
			Integer recetaIdInteger =  (Integer) rs.getObject("receta_id");
			Long recetaId=recetaIdInteger.longValue();
			if (recetaId != 0) {
				String recetaNombre = (String) rs.getObject("receta_nombre");
				String recetaDescripcion = (String) rs.getObject("receta_descrpcion");
				String recetaImagen = (String) rs.getObject("receta_imagen");
				receta.setDescripcion(recetaDescripcion);
				receta.setId(recetaId);
				receta.setImagen(recetaImagen);
				receta.setNombre(recetaNombre);
				// Asociar receta al usuario
				usr = map.get(usrId);
				usr.setRecetas(recetas);
				// Guardar usuario en coleccion
				map.put(usrId, usr);

			}

		}
		return map;
	}
}
