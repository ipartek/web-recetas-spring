package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Receta;

public interface DAOReceta {

	void setDatasource(DataSource ds);

	/**
	 * Todas las recetas sin usuarios
	 *
	 * @return
	 */
	List<Receta> getAll();

	/**
	 * Todas las recetas con usuarios
	 *
	 * @return
	 */
	List<Receta> getAllWhitUsers();

	Receta getById(long id);

	boolean insert(Receta r);

	boolean update(Receta r);

	boolean delete(long id);

	/**
	 * Buscamos todas las recetas que tenga el usuario.<br>
	 *
	 * @param idUsuario
	 * @return List<Receta>
	 */
	List<Receta> getRecetasUser(long idUsuario);

}
