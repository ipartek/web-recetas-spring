package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.repository.DAOReceta;
import com.ipartek.formacion.repository.DAOUsuario;

@Service("serviceUsuario")
public class ServiceUsuarioImpl implements ServiceUsuario {

	private final Log LOG = LogFactory.getLog(getClass());

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOReceta daoReceta;

	@Override
	public List<Usuario> listar() {
		LOG.trace("listar usuario");
		return daoUsuario.getAll();
	}
	
	@Override
	public List<Usuario> listarConRecetas() {
		LOG.trace("listar usuario con recetas");
		return daoUsuario.getAllConRecetas();
	}

	

	@Override
	public Usuario buscarPorId(long id) {
		LOG.trace("Buscamos usuario por id: " + id);
		Usuario usuario = daoUsuario.getById(id);
		return usuario;
	}

	@Override
	public Usuario buscarPorIdConRecetas(long id) {
		LOG.trace("Buscamos usuario por id: " + id + " y sus recetas");
		Usuario usuario = daoUsuario.getById(id);
		usuario.setRecetas((ArrayList<Receta>) daoReceta.getAllByUser(id));
		return usuario;
	}

	@Override
	public boolean crear(Usuario u) {
		LOG.trace("Creando usuario: " + u);
		return daoUsuario.insert(u);
	}

	@Override
	public boolean modificar(Usuario u) {
		LOG.trace("Modificando usuario: " + u);
		return daoUsuario.update(u);
	}

	@Override
	public boolean eliminar(long id) throws DataIntegrityViolationException {
		LOG.trace("Eliminando por id: " + id);
		return daoUsuario.delete(id);
	}

	@Override
	public Usuario existe(String nombre) {
		LOG.trace("Buscando usuario por nombre: " + nombre);
		return daoUsuario.existe(nombre);
	}

	
}
