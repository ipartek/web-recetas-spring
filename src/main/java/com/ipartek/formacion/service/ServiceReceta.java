package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Receta;

public interface ServiceReceta {

	List<Receta> listar();

	Receta buscarPorId(long id);

	boolean crear(Receta r);

	boolean modificar(Receta r);

	boolean eliminar(long id);

}
