package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Receta;

public class ImagenMapper implements RowMapper<Imagen> {

	@Override
	public Imagen mapRow(ResultSet rs, int numRow) throws SQLException {

		Imagen i = new Imagen();

		i.setId(rs.getLong("id"));
		i.setNombre(rs.getString("nombre"));

		Receta r = new Receta();
		r.setId(rs.getLong("receta_id"));

		i.setReceta(r);

		return i;
	}

}
