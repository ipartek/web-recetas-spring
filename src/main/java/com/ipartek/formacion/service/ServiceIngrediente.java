package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Ingrediente;

public interface ServiceIngrediente {

	List<Ingrediente> listar();

	Ingrediente buscarPorId(long id);

	List<Ingrediente> buscarPorNombre(String nombre, boolean ordenASC);

	boolean crear(Ingrediente i);

	boolean modificar(Ingrediente i);

	boolean eliminar(long id) throws DataIntegrityViolationException;

	int TotalIngrediente();

}
