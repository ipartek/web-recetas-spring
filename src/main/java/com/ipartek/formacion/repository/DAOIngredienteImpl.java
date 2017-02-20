package com.ipartek.formacion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.repository.mapper.IngredienteMapper;

@Repository("daoIngrediente")
public class DAOIngredienteImpl implements DAOIngrediente {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;

	@Autowired
	@Override
	public void setDatasource(DataSource ds) {
		this.dataSource = ds;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
	}

	// Sentencias SQL
	private static final String SQL_GET_ALL = "SELECT id, nombre, gluten FROM ingrediente ORDER BY id DESC LIMIT 1000;";

	@Override
	public List<Ingrediente> getAll() {
		ArrayList<Ingrediente> lista = new ArrayList<Ingrediente>();

		try {
			lista = (ArrayList<Ingrediente>) this.jdbctemplate.query(SQL_GET_ALL, new IngredienteMapper());
		} catch (EmptyResultDataAccessException e) {
			this.logger.warn("No existen ingredientes todavia");
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}

		return lista;
	}

	@Override
	public Ingrediente getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Ingrediente i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ingrediente i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
