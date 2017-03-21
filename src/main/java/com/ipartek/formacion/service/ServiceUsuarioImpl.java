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

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOReceta daoReceta;

	@Override
	public List<Usuario> listar() {
		logger.trace("listar recetas");
		return daoUsuario.getAll();
	}
	
	@Override
	public List<Usuario> listarPintarUsuarios() {
		logger.trace("listar recetas");
		return daoUsuario.getAll();
	}

	@Override
	public Usuario buscarPorId(long id) {
		logger.trace("Buscamos usuario por id: " + id);
		Usuario usuario = daoUsuario.getById(id);
		return usuario;
	}

	@Override
	public Usuario buscarPorIdConRecetas(long id) {
		logger.trace("Buscamos usuario por id: " + id + " y sus recetas");
		Usuario usuario = daoUsuario.getById(id);
		usuario.setRecetas((ArrayList<Receta>) daoReceta.getAllByUser(id));
		return usuario;
	}

	@Override
	public boolean crear(Usuario u) {
		logger.trace("Creando usuario: " + u);
		return daoUsuario.insert(u);
	}

	@Override
	public boolean modificar(Usuario u) {
		logger.trace("Modificando usuario: " + u);
		return daoUsuario.update(u);
	}

	@Override
	public boolean eliminar(long id) throws DataIntegrityViolationException {
		logger.trace("Eliminando por id: " + id);
		return daoUsuario.delete(id);
	}



}
