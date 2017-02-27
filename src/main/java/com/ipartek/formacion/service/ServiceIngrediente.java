package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Ingrediente;

public interface ServiceIngrediente {

	List<Ingrediente> listar();

	Ingrediente buscarPorId(long id);

	boolean crear(Ingrediente i);

	boolean modificar(Ingrediente i);

	boolean eliminar(long id);
	
	List<Ingrediente> getAllFiltro(String nombre);

}
