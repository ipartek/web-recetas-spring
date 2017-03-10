package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public interface ServiceUsuario {

	List<Usuario> listar();

	Usuario buscarPorID(long id);

	boolean crear(Usuario u);

	boolean modificar(Usuario u);

	/**
	 * Eliminamos un Usuario
	 * 
	 * @param id
	 *            identificador del Usuario
	 * @return true si lo elimina, false en caso contrario
	 * @throws DataIntegrityViolationException
	 *             cuando un usuario contiene recetas lanza excepcion, puesto
	 *             que tiene una constrain o restriccion en la BBDD
	 *             "fk_receta_usuario"
	 */
	boolean eliminar(long id) throws DataIntegrityViolationException;

	List<Receta> listarRecetasUsuario(long idUsuario);

	/**
	 * Buscamos si existe el nombre del usuario en la bbdd.<br>
	 * La busqueda NO es Case-sensitive.
	 * 
	 * @param nombre
	 *            String nombre del usuario a buscar
	 * @return Usuario si existe, null si no existe
	 */
	Usuario existe(String nombre);

}
