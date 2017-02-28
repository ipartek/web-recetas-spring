package com.ipartek.formacion.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.repository.DAOIngrediente;

@Service("serviceIngrediente")
public class ServiceIngredienteImpl implements ServiceIngrediente {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	DAOIngrediente daoIngrediente;

	@Override
	public int total() {
		int resul = -1;
		resul = daoIngrediente.total();
		return resul;
	}

	@Override
	public List<Ingrediente> listar() {
		logger.trace("listar ingredientes");
		return daoIngrediente.getAll();
	}

	@Override
	public List<Ingrediente> listar(String filtroNombre, boolean ordenAscedente) {
		logger.trace("listar ingredientes filtrador por " + filtroNombre + " ordenacion ascendente " + ordenAscedente);
		return daoIngrediente.getAll(filtroNombre, ordenAscedente);
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
	public boolean eliminar(long id) {
		logger.trace("eliminar " + id);
		return daoIngrediente.delete(id);
	}

}
