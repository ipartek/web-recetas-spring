package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Ingrediente;

public interface ServiceIngrediente {

	int total();

	List<Ingrediente> listar();

	List<Ingrediente> listar(String filtroNombre, boolean ordenAscedente);

	Ingrediente buscarPorId(long id);

	boolean crear(Ingrediente i);

	boolean modificar(Ingrediente i);

	boolean eliminar(long id);

}
