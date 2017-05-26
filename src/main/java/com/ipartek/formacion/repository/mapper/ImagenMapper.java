package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Ingrediente;

public class ImagenMapper implements RowMapper<Imagen> {

	@Override
	public Imagen mapRow(ResultSet rs, int numRow) throws SQLException {
		Imagen img = new Imagen();

		img.setId(rs.getLong("id"));
		img.setId_receta(rs.getLong("id_receta"));
		img.setUrl(rs.getString("url"));
		
		return img;
	}

}
