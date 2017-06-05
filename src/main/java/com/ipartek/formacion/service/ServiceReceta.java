package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;

public interface ServiceReceta {

	/**
	 * Lista Recetas con Usuario asociado
	 * 
	 * @return
	 */
	List<Receta> listarConUsuarios();

	Receta buscarPorID(long id);

	boolean crear(Receta r);

	boolean modificar(Receta r);

	boolean eliminar(long id);

	boolean eliminarIngrediente(long idReceta, long idIngrediente);

	boolean modificarIngrediente(long idReceta, Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

	Ingrediente recuperarIngrediente(long idReceta, long idIngrediente);

	/**
	 * Buscamos todos los ingredintes que no esten en esa receta Nos sirve para
	 * poder añadir un nuevo ingrediente
	 * 
	 * @param idReceta
	 * @return listado de ingredientes disponibles
	 */
	List<Ingrediente> listarIngredientesFueraReceta(long idReceta);

	int getLikes(long idReceta);

	boolean addLikes(long idReceta);
	
	List<Ingrediente> listaringredientesdereceta( long idReceta);
	
	boolean insertImagen(Imagen img);
	
	boolean deleteImagen(long id);
	
	List<Imagen> getAllImg( long idReceta);

	List<Receta> listar(String filter);

}
