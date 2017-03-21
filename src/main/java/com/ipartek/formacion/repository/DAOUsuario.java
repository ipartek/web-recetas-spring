package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Usuario;

public interface DAOUsuario {
	/**
	 *
	 * @param ds
	 */
	void setDatasource(DataSource ds);

	/**
	 * retorna lista de usuarios con datos basicos
	 *
	 * @return List<Usuario>
	 */
	List<Usuario> getAll();

	/**
	 * retorna lista de usuarios con datos basicos sin password
	 *
	 * @return List<Usuario>
	 */
	List<Usuario> getAllRestricted();

	/**
	 * retorna lista de usuarios con Sus recetas sin password
	 *
	 * @return List<Usuario>
	 */
	List<Usuario> getAllWithRecetas();

	Usuario getById(long id);

	Usuario getByIdRestricted(long id);

	Usuario getUserByReceta(long idReceta);

	boolean insert(Usuario u);

	boolean update(Usuario u);

	boolean delete(long id) throws DataIntegrityViolationException;

	Usuario exist(String nombre);

}
