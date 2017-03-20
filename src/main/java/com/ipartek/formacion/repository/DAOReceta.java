package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Receta;

public interface DAOReceta {

	void setDatasource(DataSource ds);

	/**
	 * Listado de todas las Recetas sin Usuarios
	 * @return List<Recetas> si hay datos, si no List inicializada not null 
	 */
	List<Receta> getAll();
	
	/**
	 * Listado de todas las Recetas con Usuarios
	 * @return List<Recetas> si hay datos, si no List inicializada not null
	 */
	List<Receta> getAllWithUSer();

	List<Receta> getAllByUser(long idUsuario);

	Receta getById(long id);

	boolean insert(Receta r);

	boolean update(Receta r);

	boolean delete(long id);

}
