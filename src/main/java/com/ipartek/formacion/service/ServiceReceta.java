package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public interface ServiceReceta {

	List<Receta> listar();

	Receta buscarPorID(long id);

	boolean crear(Receta r);

	boolean modificar(Receta r);

	boolean eliminar(long id);

	boolean eliminarIngrediente(long idReceta, long idIngrediente);

	boolean modificarIngrediente(long idReceta, Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

	/**
	 * Buscamos todos los ingredientes que no esten en esa receta.<br>
	 * Nos sirve para poder añadir un nuevo ingrediente.
	 * 
	 * @param idReceta
	 * @return List<Ingrediente> disponibles
	 */
	List<Ingrediente> listarIngredientesNoIncluidas(long idReceta);

	Ingrediente recuperarIngrediente(long idReceta, long idIngrediente);

	Usuario getUsuarioReceta(long idReceta);

}
