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

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.repository.mapper.UsuarioMapper;

@Repository("daoUsuario")
public class DAOUsuarioImpl implements DAOUsuario {

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

	private static final String SQL_GET_ALL = "SELECT `id`, `nombre`, `email`, `password`,`imagen` FROM `usuario` ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, `email`, `password`,`imagen` FROM `usuario` WHERE `id` = ?";
	private static final String SQL_EXIST_BY_NAME = "SELECT `id`, `nombre`, `email`, `password`,`imagen` FROM `usuario` WHERE LOWER(`nombre`) = LOWER(?);";
	private static final String SQL_GET_BY_RECETA_ID = "SELECT u.id, u.nombre, u.email, u.password, u.imagen FROM receta as r, usuario as u WHERE r.usuario_id = u.id AND r.id = ?;";
	private static final String SQL_INSERT = "INSERT INTO `usuario` (`nombre`, `email`, `password`,`imagen`) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE `usuario` SET `nombre`= ? ,`email`=? ,`password`=? ,`imagen`= ? WHERE `id`= ? ;";
	private static final String SQL_DELETE = "DELETE FROM `usuario` WHERE `id` = ?;";

	@Override
	public List<Usuario> getAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try {

			lista = (ArrayList<Usuario>) this.jdbcTemplate.query(SQL_GET_ALL, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return lista;
	}

	@Override
	public Usuario getById(long id) {
		Usuario u = new Usuario();

		try {

			u = this.jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return u;
	}

	@Override
	public Usuario getByRecetaId(long idReceta) {
		Usuario u = new Usuario();

		try {

			u = this.jdbcTemplate.queryForObject(SQL_GET_BY_RECETA_ID, new Object[] { idReceta }, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia con este usuario");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return u;
	}

	@Override
	public boolean insert(final Usuario u) {
		logger.trace("insert " + u);
		boolean resul = false;

		try {
			int affectedeRows = -1;
			KeyHolder keyHolder = new GeneratedKeyHolder();

			affectedeRows = this.jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, u.getNombre());
					ps.setString(2, u.getEmail());
					ps.setString(3, u.getPassword());
					ps.setString(4, u.getImagen());

					return ps;
				}
			}, keyHolder);

			if (affectedeRows == 1) {
				u.setId(keyHolder.getKey().longValue());
				resul = true;
			}
		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public boolean update(Usuario u) {
		logger.trace("update " + u);
		boolean resul = false;
		int affectedRows = -1;

		try {

			Object[] argumentos = { u.getNombre(), u.getEmail(), u.getPassword(), u.getImagen(), u.getId() };
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
	public boolean delete(long id) throws DataIntegrityViolationException {
		logger.trace("eliminar usuario " + id);
		boolean resul = false;
		int affectedRows = -1;

		try {

			affectedRows = this.jdbcTemplate.update(SQL_DELETE, id);

			if (affectedRows == 1) {
				resul = true;
			}
		} catch (DataIntegrityViolationException e) {
			this.logger.warn(e.getMessage());
			throw new DataIntegrityViolationException("No se puede eliminar un cocinero con recetas");

		} catch (Exception e) {

			this.logger.error(e.getMessage());

		}

		return resul;
	}

	@Override
	public Usuario existe(String nombre) {
		Usuario u = null;

		try {

			u = this.jdbcTemplate.queryForObject(SQL_EXIST_BY_NAME, new Object[] { nombre }, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.logger.warn("No existen recetas todavia", e);

		} catch (Exception e) {

			this.logger.error(e);

		}

		return u;
	}

}
