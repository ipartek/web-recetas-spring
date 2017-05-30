package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;

public interface ServiceReceta {

	/**
	 * Lista recetas sin ingredientes y sin usuario
	 * 
	 *
	 * @param filter
	 *            filtro por el nombre si viene rellenado, sino, lista todo.
	 * @return
	 */
	List<Receta> listar(String filter);

	/**
	 * Lista Recetas con Usuario asociado
	 * 
	 * @return
	 */
	List<Receta> listarConUsuarios();

	/**
	 * Busca una receta con sus Imagenes asociado
	 * 
	 * @return
	 */
	Receta listarConImagenes(long id);

	Receta buscarPorID(long id);

	boolean crear(Receta r);

	boolean modificar(Receta r);

	boolean modificarLikes(long idReceta);

	boolean eliminar(long id);

	boolean eliminarIngrediente(long idReceta, long idIngrediente);

	boolean modificarIngrediente(long idReceta, Ingrediente i);

	Ingrediente buscarNombreIngrediente(Ingrediente i);

	boolean crearIngrediente(Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

	Ingrediente recuperarIngrediente(long idReceta, long idIngrediente);

	/**
	 * Buscamos todos los ingredintes que no esten en esa receta Nos sirve para
	 * poder añadir un nuevo ingrediente
	 * 
	 * @param idReceta
	 * @return listado de ingredientes disponibles
	 */
	List<Ingrediente> listarIngredientesFueraReceta(long idReceta, String filter);

	List<Ingrediente> listarIngredientes(long idReceta);

	boolean subirImagen(Imagen i);
}
