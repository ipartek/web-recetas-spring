package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Ingrediente;

public interface DAOIngrediente {

	void setDatasource(DataSource ds);

	List<Ingrediente> getAll();

	List<Ingrediente> getAllByReceta(long idReceta);

	/**
	 * Recupera un Ingrediente de la bbdd, sin la 'cantidad' asociada a una
	 * Receta
	 * 
	 * @param id
	 *            ingrediente
	 * @return Ingrediente, null si no existe
	 */
	Ingrediente getById(long id);

	List<Ingrediente> buscarPorNombre(String nombre, boolean ordenASC);

	boolean insert(Ingrediente i);

	boolean update(Ingrediente i);

	boolean delete(long id) throws DataIntegrityViolationException;

	boolean deleteByReceta(long idReceta, long idIngrediente);

	boolean updateByReceta(long idReceta, Ingrediente i);

	/**
	 * Añadir nuevo ingrediente a Receta
	 * 
	 * @param idReceta
	 * @param i
	 * @return
	 */
	boolean insertByReceta(long idReceta, Ingrediente i);

	List<Ingrediente> getAllByNotInReceta(long idReceta);

	/**
	 * Recupera Ingrediente de una Receta, con su 'cantidad'
	 * 
	 * @param idReceta
	 * @param idIngrediente
	 * @return Ingrediente, null si no existe
	 */
	Ingrediente getByReceta(long idReceta, long idIngrediente);
}
