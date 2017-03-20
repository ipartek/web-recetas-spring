package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.repository.mapper.UsuarioMapper;
import com.ipartek.formacion.repository.mapper.UsuarioRecetaResultSetExtractor;
import com.ipartek.formacion.repository.mapper.UsuarioRestringidoMapper;

@Repository("daoUsuario")
public class DAOUsuarioImpl implements DAOUsuario {

	private final Log LOG = LogFactory.getLog(getClass());

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
	private static final String SQL_GET_ALL = "SELECT `id`, `nombre`, `email`, `password`, `imagen` FROM `usuario` ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_ALL_RESTRICTED = "SELECT `id`, `nombre`, `email`, `imagen` FROM `usuario` ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_ALL_WITH_RECETA_RESTRICTED = "SELECT u.id AS id_usuario, u.nombre AS nombre_usuario, u.email AS email_usuario, u.imagen AS imagen_usuario, r.id AS id_receta, r.nombre AS nombre_receta, r.imagen AS imagen_receta, r.descripcion AS descripcion_receta FROM usuario AS u LEFT JOIN receta AS r ON u.id = r.usuario_id ORDER BY u.id ASC;";
	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, `email`, `password`, `imagen` FROM `usuario` WHERE `id` = ?;";
	private static final String SQL_GET_BY_ID_RESTRICTED = "SELECT `id`, `nombre`, `email`, `imagen` FROM `usuario` WHERE `id` = ?;";
	private static final String SQL_GET_USER_BY_RECETA = "SELECT `u`.`id`, `u`.`nombre`, `u`.`email`, `u`.`password`, `u`.`imagen` FROM `usuario` AS `u`, `receta` AS `r` WHERE `r`.`usuario_id` = `u`.id AND `r`.`id` = ?;";
	private static final String SQL_DELETE = "DELETE FROM `usuario` WHERE `id` = ?;";
	private static final String SQL_INSERT = "INSERT INTO `usuario` (`nombre`, `email`, `password`, `imagen`) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE `usuario` SET `nombre`= ? , `email`= ?, `password`= ?, `imagen`= ? WHERE `id`= ? ;";
	private static final String SQL_UPDATE_RESTRICTED = "UPDATE `usuario` SET `nombre`= ? , `email`= ?, `imagen`= ? WHERE `id`= ? ;";
	private static final String SQL_GET_BY_NAME = "SELECT `id`, `nombre`, `email`, `password`, `imagen` FROM `usuario` WHERE LOWER(`nombre`)= LOWER( ? );";

	@Override
	public List<Usuario> getAll() {

		this.LOG.trace("Recuperando usuarios");

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try {

			lista = (ArrayList<Usuario>) this.jdbctemplate.query(SQL_GET_ALL, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen usuarios todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public List<Usuario> getAllRestricted() {

		this.LOG.trace("Recuperando usuarios restringido");

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try {

			lista = (ArrayList<Usuario>) this.jdbctemplate.query(SQL_GET_ALL_RESTRICTED,
					new UsuarioRestringidoMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen usuarios todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public List<Usuario> getAllWithRecetas() {

		this.LOG.trace("Recuperando usuarios con recetas restringido");
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try {

			HashMap<Long, Usuario> hmUsuarios = this.jdbctemplate.query(SQL_GET_ALL_WITH_RECETA_RESTRICTED,
					new UsuarioRecetaResultSetExtractor());
			lista.addAll(hmUsuarios.values());

			this.LOG.debug("Recuperados " + lista.size() + "usuarios con recetas");

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen usuarios todavia", e);

		} catch (Exception e) {

			this.LOG.error("Excepcion inesperada", e);

		}

		return lista;
	}

	@Override
	public Usuario getById(long id) {

		Usuario u = null;

		try {

			u = this.jdbctemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen ingredientes todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return u;
	}

	@Override
	public Usuario getByIdRestricted(long id) {

		Usuario u = null;

		try {

			u = this.jdbctemplate.queryForObject(SQL_GET_BY_ID_RESTRICTED, new Object[] { id },
					new UsuarioRestringidoMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen ingredientes todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return u;
	}

	@Override
	public Usuario getUserByReceta(long idReceta) {

		LOG.trace("Get Usuario de Receta " + idReceta);
		Usuario u = new Usuario();

		try {

			u = this.jdbctemplate.queryForObject(SQL_GET_USER_BY_RECETA, new Object[] { idReceta },
					new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.info("Todavia no hay usuarios");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}
		return u;
	}

	@Override
	public boolean insert(final Usuario u) {

		LOG.trace("insert " + u);
		boolean resul = false;

		try {

			int affectedRows = -1;
			KeyHolder keyHolder = new GeneratedKeyHolder();

			affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, u.getNombre());
					ps.setString(2, u.getEmail());
					ps.setString(3, u.getPassword());
					ps.setString(4, u.getImagen());
					return ps;
				}
			}, keyHolder);

			if (affectedRows == 1) {
				resul = true;
				u.setId(keyHolder.getKey().longValue());
			}

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}
		return resul;
	}

	@Override
	public boolean update(Usuario u) {

		LOG.trace("update " + u);
		boolean resul = false;
		int affectedRows = -1;
		String sql = null;
		Object[] argumentos;

		try {

			if (u.getPassword() != "") {

				sql = SQL_UPDATE;
				argumentos = new Object[] { u.getNombre(), u.getEmail(), u.getPassword(), u.getImagen(), u.getId() };

			} else {

				sql = SQL_UPDATE_RESTRICTED;
				argumentos = new Object[] { u.getNombre(), u.getEmail(), u.getImagen(), u.getId() };

			}
			affectedRows = this.jdbctemplate.update(sql, argumentos);

			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			this.LOG.error(e.getMessage());
		}

		return resul;
	}

	@Override
	public boolean delete(long id) throws DataIntegrityViolationException {

		LOG.trace("eliminar " + id);
		boolean resul = false;

		try {

			int affectedRows = this.jdbctemplate.update(SQL_DELETE, id);

			if (affectedRows == 1) {
				resul = true;
			}

		} catch (DataIntegrityViolationException e) {

			this.LOG.warn(e.getMessage());
			throw new DataIntegrityViolationException("No se puede eliminar un cocinero con recetas");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}
		return resul;
	}

	@Override
	public Usuario exist(String nombre) {
		LOG.trace("Buscando si exite el usuario " + nombre);

		Usuario u = null;

		try {

			u = this.jdbctemplate.queryForObject(SQL_GET_BY_NAME, new Object[] { nombre }, new UsuarioMapper());

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}
		return u;
	}

}
