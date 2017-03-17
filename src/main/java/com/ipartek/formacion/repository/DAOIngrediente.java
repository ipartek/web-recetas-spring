package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Ingrediente;

public interface DAOIngrediente {

	void setDatasource(DataSource ds);

	int total();

	List<Ingrediente> getAll( String orden );

	List<Ingrediente> getAll(String filtroNombre, boolean ordenAscedente);

	List<Ingrediente> getAllByReceta(long idReceta);

	/**
	 * Recupera un Ingrediente de la bbdd, sin la 'cantidad' asociada a una
	 * Reeceta
	 * 
	 * @param id
	 *            ingrediente
	 * @return Ingrediente, null si no existe
	 */
	Ingrediente getById(long id);

	boolean insert(Ingrediente i);

	boolean update(Ingrediente i);

	boolean delete(long id);

	boolean deleteByReceta(long idReceta, long idIngrediente);

	/**
	 * Recupera Ingrediente de una Receta, con su 'cantidad'
	 * 
	 * @param idReceta
	 * @param idIngrediente
	 * @return Ingrediente, null si no existe
	 */
	Ingrediente getByReceta(long idReceta, long idIngrediente);

	boolean updateByReceta(long idReceta, Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

	List<Ingrediente> listadoFueraDeReceta(long recetaId);

}
