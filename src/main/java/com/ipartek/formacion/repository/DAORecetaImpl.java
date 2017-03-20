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
import com.ipartek.formacion.repository.mapper.RecetaUsuarioMapper;

@Repository("daoReceta")
public class DAORecetaImpl implements DAOReceta {

	private final Log LOG = LogFactory.getLog(getClass());

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
	private static final String SQL_GET_ALL_WITH_USERS = "SELECT u.id AS user_id,u.nombre AS usuario_nombre,u.email AS user_email ,u.imagen AS usuario_imagen,r.id AS receta_id,r.nombre AS receta_nombre,r.imagen AS recetas_imagen,r.descripcion FROM (receta as r) left join  (usuario as u)  ON u.id = r.usuario_id;";

	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, `imagen`, `descripcion` FROM `receta` WHERE `id` = ?";
	private static final String SQL_DELETE = "DELETE FROM `receta` WHERE `id` = ?;";
	private static final String SQL_UPDATE = "UPDATE `receta` SET `nombre`= ? , `imagen`= ?, `descripcion`= ?, `usuario_id`= ? WHERE `id`= ? ;";
	private static final String SQL_INSERT = "INSERT INTO `receta` (`nombre`, `imagen`, `descripcion`, `usuario_id`) VALUES (?, ?, ?, ?);";
	private static final String SQL_GET_RECETAS_BY_USER = "SELECT `r`.`id`, `r`.`nombre`, `r`.`imagen`, `r`.`descripcion` FROM `receta` AS `r`, `usuario` AS `u` WHERE `r`.`usuario_id` = `u`.`id` AND `r`.`usuario_id` = ?;";

	@Override
	public List<Receta> getAll() {

		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL, new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public List<Receta> getAllWhitUsers() {
		this.LOG.trace("Recuperando recetas con usuarios");
		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL_WITH_USERS, new RecetaUsuarioMapper());
			this.LOG.debug("Recuperado" + lista.size() + "recetas con usuarios");

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public Receta getById(long id) {

		Receta r = new Receta();

		try {

			r = this.jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return r;
	}

	@Override
	public boolean insert(final Receta r) {

		LOG.trace("insert " + r);
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

			this.LOG.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public boolean update(Receta r) {

		LOG.trace("update " + r);
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

			this.LOG.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public boolean delete(long id) {

		LOG.trace("eliminar " + id);
		boolean resul = false;
		int affectedRows = -1;

		try {

			affectedRows = this.jdbcTemplate.update(SQL_DELETE, id);

			if (affectedRows == 1) {
				resul = true;
			}
		} catch (DataIntegrityViolationException e) {

			this.LOG.warn(e.getMessage());

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public List<Receta> getRecetasUser(long idUsuario) {

		LOG.trace("Get Recetas del Usuario " + idUsuario);
		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_RECETAS_BY_USER, new Object[] { idUsuario },
					new RecetaMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.info("No hay recetas para el usuario" + idUsuario);

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return lista;
	}

}
