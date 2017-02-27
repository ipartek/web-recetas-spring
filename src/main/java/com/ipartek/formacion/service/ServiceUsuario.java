package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Usuario;

public interface ServiceUsuario {

	List<Usuario> listar();

	Usuario buscarPorId(long id);

	boolean crear(Usuario u);

	boolean modificar(Usuario u);

	/**
	 * Eliminamos un Usuario
	 * 
	 * @param id
	 *            identificador del Usuario
	 * @return true si elimina, false en caso contrario
	 * @throws DataIntegrityViolationException
	 *             cuando un usuario contiene recetas lanza exception, puesto
	 *             que tiene una constraint o restriccion en bbdd
	 *             "fk_receta_usuario"
	 */
	boolean eliminar(long id) throws DataIntegrityViolationException;

}
