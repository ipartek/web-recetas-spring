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

	private static final String SQL_GET_ALL = "SELECT `id`, `nombre`, `imagen`, `descripcion`, `likes` FROM `receta` ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_ALL_WITH_USER = "SELECT r.nombre  as receta_nombre ,r.id as receta_id, r.imagen  as receta_imagen,r.descripcion as receta_descripcion, r.likes,u.id as usuario_id,u.nombre as usuario_nombre, u.email as usuario_email,u.imagen as usuario_imagen FROM usuario as u INNER JOIN receta as r ON u.id = r.usuario_id; ";
	private static final String SQL_GET_ALL_BY_USER = "SELECT `id`, `nombre`, `imagen`, `descripcion`,`likes` FROM `receta` WHERE `usuario_id`=?  ORDER BY `id` DESC LIMIT 1000;";
	private static final String SQL_GET_BY_ID = "SELECT `id`, `nombre`, `imagen`, `descripcion`,`likes` FROM `receta` WHERE `id` = ?";
	private static final String SQL_DELETE = "DELETE FROM `receta` WHERE `id` = ?;";
	private static final String SQL_UPDATE = "UPDATE `receta` SET `nombre`= ? , `imagen`= ?, `descripcion`= ?, `usuario_id` = ? WHERE `id`= ? ;";
	private static final String SQL_INSERT = "INSERT INTO `receta` (`nombre`, `imagen`, `descripcion`, `usuario_id`) VALUES (?, ?, ?, ?);";
	private static final String SQL_GET_LIKES = "SELECT `likes` FROM receta WHERE id=?;";
	private static final String SQL_ADD_LIKES = "UPDATE `receta` SET `likes` = `likes` +1 WHERE id = ?;";
	
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
	public List<Receta> getAllWithUSer() {
		
		this.LOG.trace("Recuperando Recetas con Usuarios");
		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL_WITH_USER, new RecetaUsuarioMapper());
			this.LOG.debug("Recuperandas " + lista.size()  + " Recetas"  );
			
		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen recetas todavia", e);

		} catch (Exception e) {

			this.LOG.error("Excepion inseperada" , e);

		}

		return lista;
	}
	
	
	public List<Receta> getAllwithUsers() {

		ArrayList<Receta> lista = new ArrayList<Receta>();

		try {

			//lista = (ArrayList<Receta>) this.jdbcTemplate.query(SQL_GET_ALL_AND_USER, new RecetaUsuarioMapper());

		} catch (EmptyResultDataAccessException e) {

			this.LOG.warn("No existen recetas todavia");

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

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

			Object[] argumentos = { r.getNombre(), r.getImagen(), r.getDescripcion() , r.getUsuario().getId(),
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
	public int getLikes(long id) {
		int resul = -1;
		try {

			resul = this.jdbcTemplate.queryForInt(SQL_GET_LIKES, id);

		} catch (DataIntegrityViolationException e) {

			this.LOG.warn(e.getMessage());

		} catch (Exception e) {

			this.LOG.error(e.getMessage());

		}

		return resul;
	}
	
	@Override
	public boolean addLikes(long id) {

		boolean resul = false;
		int affectedRows = -1;

		try {

			affectedRows = this.jdbcTemplate.update(SQL_ADD_LIKES, id);

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

}
