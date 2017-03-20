package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.domain.Usuario;

public class UsuarioRecetaResultSetExtractor implements ResultSetExtractor<HashMap<Long, Usuario>> {

	@Override
	public HashMap<Long, Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		
		//recorrer RS
		
			//recuperar key(idUsuario) de fila actual RS
		
		
			// buscar esa key en coleccion y recuperar "usuario"
		
						
				// "usuario" == null
		
						//crear usuario
		
				// "usuario" != null  'no hacernada'
		
		
		
			// recuperar y crear receta
		
			// asociar receta al usuario
		
		
			//guardar usuario en coleccion
		
		
		
		return null;
	}

}
