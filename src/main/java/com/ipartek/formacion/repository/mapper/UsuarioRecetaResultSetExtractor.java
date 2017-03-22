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

	private static final int ID_RECETA_NULL = 0;
	
	
	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		HashMap<Long,Usuario> resultado = new HashMap<Long, Usuario>();
		Usuario usuario = null;
		Receta r = null;
		long idReceta = ID_RECETA_NULL;
		
		while (rs.next()) { 
			
			Long key = rs.getLong("usuario_id");
		    usuario = resultado.get(key);
			if( usuario == null){
				
				usuario = new Usuario();
				usuario.setId(key);
				usuario.setNombre(rs.getString("usuario_nombre"));
				usuario.setEmail(rs.getString("usuario_email"));
				usuario.setImagen(rs.getString("usuario_imagen"));
				
		    }
			idReceta = rs.getLong("receta_id");
			if(idReceta != ID_RECETA_NULL){
				r = new Receta();
				r.setId(rs.getLong("receta_id"));
				r.setNombre(rs.getString("receta_nombre"));
				r.setImagen(rs.getString("receta_imagen"));
				r.setDescripcion(rs.getString("receta_descripcion"));
				
				usuario.addReceta(r);
			}
			
			resultado.put(key, usuario);
			
		}
		
		return resultado;
	}

}
