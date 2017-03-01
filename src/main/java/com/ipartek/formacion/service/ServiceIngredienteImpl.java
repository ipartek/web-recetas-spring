package com.ipartek.formacion.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.repository.DAOIngrediente;

@Service("serviceIngrediente")
public class ServiceIngredienteImpl implements ServiceIngrediente {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	DAOIngrediente daoIngrediente;

	@Override
	public List<Ingrediente> listar() {
		logger.trace("listar ingredientes");
		return daoIngrediente.getAll();
	}

	@Override
	public Ingrediente buscarPorId(long id) {
		logger.trace("Buscamos ingrediente id: " + id);
		return daoIngrediente.getById(id);
	}

	@Override
	public boolean crear(Ingrediente i) {
		logger.trace("Creando ingrediente: " + i);
		return daoIngrediente.insert(i);
	}

	@Override
	public boolean modificar(Ingrediente i) {
		logger.trace("Modificando ingrediente: " + i);
		return daoIngrediente.update(i);
	}

	@Override
	public boolean eliminar(long id) throws DataIntegrityViolationException {
		logger.trace("eliminar " + id);
		return daoIngrediente.delete(id);
	}

	@Override
	public List<Ingrediente> buscarPorNombre(String nombre, boolean ordenASC) {
		logger.trace("buscando por nombre que contien: " + nombre + "en orden ASC a " + ordenASC);
		return daoIngrediente.buscarPorNombre(nombre, ordenASC);
	}

	@Override
	public int TotalIngrediente() {
		logger.trace("sacando el total de ingredientes");
		return daoIngrediente.getTotalIngrediente();
	}

}
