package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Receta;

public interface DAOReceta {

	void setDatasource(DataSource ds);

	/**
	 * Listado de todas las recetas sin Usuarios
	 * 
	 * @return List<Receta> si hay datos, si no List inicializada not null
	 */
	List<Receta> getAll();

	/**
	 * Listado de todas las recetas con usuarios
	 * 
	 * @return List<Receta> si hay datos, si no List inicializada not null
	 */
	List<Receta> getAllWithUser();

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
