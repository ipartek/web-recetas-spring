package com.ipartek.formacion.service;

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

	@Override()
	public List<Usuario> listar() {
		LOG.trace("listar usuarios");
		return daoUsuario.getAllWithRecetas();
	}

	@Override()
	public List<Usuario> listarRestringido() {
		LOG.trace("listar usuarios con datos restringidos");
		return daoUsuario.getAllRestricted();
	}

	@Override()
	public List<Usuario> listarConRecetas() {
		LOG.trace("listar con recetas");
		return null;
	}

	@Override()
	public Usuario buscarPorID(long id) {
		LOG.trace("Buscamos receta por id: " + id);
		return daoUsuario.getById(id);
	}

	@Override()
	public Usuario buscarPorIDRestringido(long id) {
		LOG.trace("Buscamos receta por id con datos restringidos: " + id);
		return daoUsuario.getByIdRestricted(id);
	}

	@Override()
	public boolean crear(Usuario u) {
		LOG.trace("Creando receta: " + u);
		return daoUsuario.insert(u);
	}

	@Override()
	public boolean modificar(Usuario u) {
		LOG.trace("Modificando receta: " + u);
		return daoUsuario.update(u);
	}

	@Override()
	public boolean eliminar(long id) throws DataIntegrityViolationException {
		LOG.trace("Eliminando por id: " + id);
		return daoUsuario.delete(id);
	}

	@Override()
	public List<Receta> listarRecetasUsuario(long idUsuario) {
		LOG.trace("Listar recetas del Usuario: " + idUsuario);
		return daoReceta.getRecetasUser(idUsuario);
	}

	@Override()
	public Usuario existe(String nombre) {
		LOG.trace("Buscando si exite en la bbdd el usuario: " + nombre);
		return daoUsuario.exist(nombre);
	}

}
