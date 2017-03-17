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

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.repository.mapper.RecetaMapper;

@Repository("daoReceta")
public class DAORecetaImpl implements DAOReceta {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Override
	public void setDatasource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	// Sentencias SQL

	private static final String SQL_GET_ALL = "SELECT `id`, `nombre`, `imagen`, `descripcion` FROM `receta` ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_ALL_AND_USER = "SELECT r.`id` as receta_id , r.`nombre` as receta_nombre, r.`imagen` as receta_imagen, `descripcion`, u.id as usuario_id, u.nombre as usuario_nombre, u.email, u.imagen as usuario_imagen FROM `receta` as r,`usuario` as  u WHERE r.usuario_id = u.id ORDER BY r.`id` DESC LIMIT 1000;";
	private static final String SQL_GET_ALL_BY_USER = "SELECT `id`, `nombre`, `imagen`, `descripcion` FROM `receta` WHERE `usuario_id`=?  ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, `imagen`, `descripcion` FROM `receta` WHERE `id` = ?";
	private static final String SQL_DELETE = "DELETE FROM `receta` WHERE `id` = ?;";
	private static final String SQL_UPDATE = "UPDATE `receta` SET `nombre`= ? , `imagen`= ?, `descripcion`= ?, `usuario_id` = ? WHERE `id`= ? ;";
	private static final String SQL_INSERT = "INSERT INTO `receta` (`nombre`, `imagen`, `descripcion`, `usuario_id`) VALUES (?, ?, ?, ?);";

	@Override
	public List<Receta> getAll() {

		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL, new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return lista;
	}
	
	
	public List<Receta> getAllwithUsers() {

		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			//lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL_AND_USER, new RecetaUsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return lista;
	}
	

	@Override
	public List<Receta> getAllByUser(long idUsuario) {
		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL_BY_USER, new Object[] { idUsuario },
					new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public Receta getById(long id) {

		Receta r = new Receta();

		try {

			r = this.jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return r;
	}

	@Override
	public boolean insert(final Receta r) {

		logger.trace("insert " + r);
		boolean resul = false;

		try {
			int affectedeRows = -1;
			KeyHolder keyHolder = new GeneratedKeyHolder();

			affectedeRows = this.jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, r.getNombre());
					ps.setString(2, r.getImagen());
					ps.setString(3, r.getDescripcion());
					ps.setLong(4, r.getUsuario().getId());
					return ps;
				}
			}, keyHolder);

			if (affectedeRows == 1) {
				r.setId(keyHolder.getKey().longValue());
				resul = true;
			}
		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public boolean update(Receta r) {

		logger.trace("update " + r);
		boolean resul = false;
		int affectedRows = -1;

		try {

			Object[] argumentos = { r.getNombre(), r.getImagen(), r.getDescripcion(), r.getUsuario().getId(),
					r.getId() };
			affectedRows = this.jdbcTemplate.update(SQL_UPDATE, argumentos);

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
		int affectedRows = -1;

		try {

			affectedRows = this.jdbcTemplate.update(SQL_DELETE, id);

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

}
