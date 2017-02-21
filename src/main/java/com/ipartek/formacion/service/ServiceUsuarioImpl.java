/*package com.ipartek.formacion.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.repository.DAOReceta;

@Service("serviceUsuario")
public class ServiceUsuarioImpl implements ServiceUsuario {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DAOReceta daoUsuario;

	@Override
	public List<Usuario> listar() {
		logger.trace("listar recetas");
		return daoUsuario.getAll();
	}

	@Override
	public Usuario buscarPorID(long id) {
		logger.trace("Buscamos receta por id: " + id);
		return daoUsuario.getById(id);
	}

	@Override
	public boolean crear(Usuario u) {
		logger.trace("Creando receta: " + u);
		return daoUsuario.insert(u);
	}

	@Override
	public boolean modificar(Usuario u) {
		logger.trace("Modificando receta: " + u);
		return daoUsuario.update(u);
	}

	@Override
	public boolean eliminar(long id) {
		logger.trace("Eliminando por id: " + id);
		return daoUsuario.delete(id);
	}

}
*/