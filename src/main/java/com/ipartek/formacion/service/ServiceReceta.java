package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;

public interface ServiceReceta {

	List<Receta> listar();

	Receta buscarPorID(long id);

	boolean crear(Receta r);

	boolean modificar(Receta r);

	boolean eliminar(long id);

	boolean eliminarIngrediente(long idReceta, long idIngrediente);

	boolean modificarIngrediente(long idReceta, Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

}
