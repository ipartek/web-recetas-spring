package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Usuario;

public interface ServiceUsuario {

	/**
	 * Listado de usuarios con los datos minimos
	 * @return List<Usuario> ordenados de forma desdecente y limitado a los ultimos 1000 creados. Lista vacias si no existe ninguno
	 */
	List<Usuario> listar();
	
	/**
	 * Listado de usuarios con sus recetas asociadas 
	 * @return @return List<Usuario> ordenados de forma desdecente y limitado a los ultimos 1000 creados. Lista vacias si no existe ninguno
	 */
	List<Usuario> listarConRecetas();

	Usuario buscarPorId(long id);

	Usuario buscarPorIdConRecetas(long id);

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

	/**
	 * Buscamos si existe el nombre del usuario en la bbdd.<br>
	 * La busqueda NO es Case-Sensitive
	 * 
	 * @param nombre
	 *            String nombre del usuario a buscar
	 * @return Usuario si existe, null si no existe
	 */
	Usuario existe(String nombre);

}
