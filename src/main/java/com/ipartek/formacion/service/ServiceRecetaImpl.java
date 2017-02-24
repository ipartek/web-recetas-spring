package com.ipartek.formacion.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.repository.DAOIngrediente;
import com.ipartek.formacion.repository.DAOReceta;

@Service("serviceReceta")
public class ServiceRecetaImpl implements ServiceReceta {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DAOReceta daoReceta;

	@Autowired
	private DAOIngrediente daoIngrediente;

	@Override
	public List<Receta> listar() {
		logger.trace("listar recetas");
		return daoReceta.getAll();
	}

	@Override
	public Receta buscarPorId(long id) { //Aupi lozoya, aqui dejaste el proyecto cuando te fuiste a soce
		logger.trace("Buscamos receta id: " + id);
		Receta receta  = 
		
		return daoReceta.getById(id);
	}

	@Override
	public boolean crear(Receta r) {
		logger.trace("Creando receta: " + r);
		return daoReceta.insert(r);
	}

	@Override
	public boolean modificar(Receta r) {
		logger.trace("Modificando receta: " + r);
		return daoReceta.update(r);
	}

	@Override
	public boolean eliminar(long id) {
		logger.trace("eliminar " + id);
		return daoReceta.delete(id);
	}

}
