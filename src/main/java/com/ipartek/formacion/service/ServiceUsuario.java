package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public interface ServiceUsuario {

	List<Usuario> listar();

	Usuario buscarPorId(long id);

	boolean crear(Usuario u);

	boolean modificar(Usuario u);

	boolean eliminar(long id) throws DataIntegrityViolationException;
	
	List<Receta> getAllByUsuarioId(long id);

}
