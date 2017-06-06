package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Imagen;

public class ImagenMapper implements RowMapper<Imagen> {
	@Override
	public Imagen mapRow(ResultSet rs, int numRow) throws SQLException {
		Imagen imagen = new Imagen();

		imagen.setId(rs.getLong("id"));
		imagen.setId_receta(rs.getLong("id_receta"));
		imagen.setUrl(rs.getString("url"));

		return imagen;
	}
}
