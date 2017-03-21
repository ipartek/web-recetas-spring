package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException {
		HashMap<Long, Usuario> hmUsuarios = new HashMap<Long, Usuario>();
		while (rs.next()) {
			Long idUsuario = rs.getLong("id_usuario");
			if (hmUsuarios.containsKey(idUsuario)) {
				if (rs.getString("nombre_receta") != null) {
					añadirRecetaUsuario(hmUsuarios, rs, idUsuario);
				}
			} else {
				Usuario u = new Usuario();
				u.setId(idUsuario);
				u.setNombre(rs.getString("nombre_usuario"));
				u.setImagen(rs.getString("imagen_usuario"));
				u.setEmail(rs.getString("email_usuario"));
				hmUsuarios.put(u.getId(), u);
				if (rs.getString("nombre_receta") != null) {
					añadirRecetaUsuario(hmUsuarios, rs, u);
				}
			}

		}
		return hmUsuarios;
	}

	private void añadirRecetaUsuario(HashMap<Long, Usuario> hmUsuarios, ResultSet rs, Usuario u) throws SQLException {
		// TODO Auto-generated method stub
		añadirRecetaUsuario(hmUsuarios, rs, u.getId());
	}

	private void añadirRecetaUsuario(HashMap<Long, Usuario> hmUsuario, ResultSet rs, Long idUsuario)
			throws SQLException {
		Receta r = new Receta();
		r.setId(rs.getLong("id_receta"));
		r.setNombre(rs.getString("nombre_receta"));
		r.setImagen(rs.getString("imagen_receta"));
		r.setDescripcion(rs.getString("descripcion_receta"));
		hmUsuario.get(idUsuario).getRecetas().add(r);

	}
}
