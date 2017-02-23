package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Ingrediente;

public class IngredienteMapper implements RowMapper<Ingrediente> {

	@Override
	public Ingrediente mapRow(ResultSet rs, int numRow) throws SQLException {
		Ingrediente i = new Ingrediente();

		i.setId(rs.getLong("id"));
		i.setNombre(rs.getString("nombre"));
		i.setGluten(rs.getBoolean("gluten"));

		return i;
	}

}
