package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, gluten FROM `ingrediente` WHERE `id` = ?;";
	private static final String SQL_GET_BY_RECETA_ID = "SELECT i.id, i.nombre, i.gluten, ri.cantidad FROM receta_ingrediente as ri, ingrediente as i WHERE ri.receta_id = ? AND ri.ingrediente_id = i.id;";
	private static final String SQL_DELETE = "DELETE FROM `ingrediente` WHERE `id` = ?;";
	private static final String SQL_INSERT = "INSERT INTO `ingrediente` (`nombre`, `gluten`) VALUES (?,?);";
	private static final String SQL_UPDATE = "UPDATE `ingrediente` SET `nombre`= ? , `gluten`= ? WHERE `id`= ? ;";

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
		Ingrediente i = null;
		try {
			i = this.jdbctemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new IngredienteMapper());
		} catch (EmptyResultDataAccessException e) {
			this.logger.warn("No existen ingredientes todavia");
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}

		return i;
	}

	@Override
	public boolean insert(final Ingrediente i) {
		logger.trace("insert " + i);
		boolean resul = false;
		try {
			int affectedRows = -1;
			KeyHolder keyHolder = new GeneratedKeyHolder();

			affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, i.getNombre());
					ps.setBoolean(2, i.isGluten());
					return ps;
				}
			}, keyHolder);

			if (affectedRows == 1) {
				resul = true;
				i.setId(keyHolder.getKey().longValue());
			}

		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return resul;
	}

	@Override
	public boolean update(Ingrediente i) {
		logger.trace("update " + i);
		boolean resul = false;
		int affectedRows = -1;
		try {

			Object[] argumentos = { i.getNombre(), i.isGluten(), i.getId() };
			affectedRows = this.jdbctemplate.update(SQL_UPDATE, argumentos);

			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return resul;
	}

	@Override
	public boolean delete(long id) {
		logger.trace("eliminar " + id);
		boolean resul = false;
		try {
			int affectedRows = this.jdbctemplate.update(SQL_DELETE, id);
			if (affectedRows == 1) {
				resul = true;
			}
		} catch (DataIntegrityViolationException e) {
			this.logger.warn(e.getMessage());
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return resul;
	}

	@Override
	public List<Ingrediente> getAllByReceta(long idReceta) {

		ArrayList<Ingrediente> lista = new ArrayList<Ingrediente>();

		try {

			lista = (ArrayList<Ingrediente>) this.jdbctemplate.query(SQL_GET_BY_RECETA_ID, new Object[] { idReceta },
					new IngredienteMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen ingredientes todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return lista;
	}

}
