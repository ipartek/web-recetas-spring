package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public interface ServiceUsuario {

	List<Usuario> listar();

	Usuario buscarPorID(long id);

	boolean crear(Usuario u);

	boolean modificar(Usuario u);

	boolean eliminar(long id);

	List<Receta> listarRecetasUsuario(long idUsuario);

}
