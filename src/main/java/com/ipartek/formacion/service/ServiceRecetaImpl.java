package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Ingrediente;
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
	public Receta buscarPorID(long id) {
		logger.trace("Buscamos receta por id: " + id);

		Receta receta = daoReceta.getById(id);
		receta.setIngredientes((ArrayList<Ingrediente>) daoIngrediente.getAllByReceta(id));

		return receta;
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
		logger.trace("Eliminando por id: " + id);
		return daoReceta.delete(id);
	}

	@Override
	public boolean eliminarIngrediente(long idReceta, long idIngrediente) {
		logger.trace("Eliminando por id: " + idIngrediente + "de la receta " + idReceta);
		return daoReceta.deleteIngrediente(idReceta, idIngrediente);
	}

	@Override
	public boolean modificarIngrediente(long idReceta, Ingrediente i) {
		logger.trace("Modificar ingrediente: " + i + "de la receta " + idReceta);
		return daoReceta.updateIngrediente(idReceta, i);
	}

	@Override
	public boolean addIngrediente(long idReceta, Ingrediente i) {
		logger.trace("Añadir ingrediente: " + i + "de la receta " + idReceta);
		return daoReceta.addIngrediente(idReceta, i);
	}

}
