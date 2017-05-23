package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.repository.DAOIngrediente;
import com.ipartek.formacion.repository.DAOReceta;
import com.ipartek.formacion.repository.DAOUsuario;

@Service("serviceReceta")
public class ServiceRecetaImpl implements ServiceReceta {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DAOReceta daoReceta;

	@Autowired
	private DAOIngrediente daoIngrediente;

	@Autowired
	private DAOUsuario daoUsuario;

	@Override
	public List<Receta> listar() {
		logger.trace("listar recetas");
		return daoReceta.getAll();
	}

	@Override
	public List<Receta> listarConUsuarios() {
		logger.trace("listar recetas con usuarios asociados");
		ArrayList<Receta> recetas = (ArrayList<Receta>) daoReceta.getAllWithUSer();
		return recetas;
	}

	@Override
	public Receta buscarPorID(long id) {
		logger.trace("Buscamos receta por id: " + id);
		Receta receta = daoReceta.getById(id);
		receta.setIngredientes((ArrayList<Ingrediente>) daoIngrediente.getAllByReceta(id));
		receta.setUsuario(daoUsuario.getByRecetaId(id));
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
		logger.trace("eliminar ingrediente" + idIngrediente + "de una receta " + idReceta);
		return daoIngrediente.deleteByReceta(idReceta, idIngrediente);
	}

	@Override
	public boolean modificarIngrediente(long idReceta, Ingrediente i) {
		logger.trace("Modificacando Ingrediente" + i + "de una receta " + idReceta);
		return daoIngrediente.updateByReceta(idReceta, i);
	}

	@Override
	public boolean addIngrediente(long idReceta, Ingrediente i) {

		logger.trace("Añadiendo Ingrediente" + i + "a receta " + idReceta);
		return daoIngrediente.addIngrediente(idReceta, i);
	}

	@Override
	public Ingrediente recuperarIngrediente(long idReceta, long idIngrediente) {
		logger.trace("recuperando Ingrediente" + idIngrediente + "de una receta " + idReceta);
		return daoIngrediente.getByReceta(idReceta, idIngrediente);
	}
	@Override
	public List<Ingrediente> listarIngredientes(long idReceta) {

		logger.trace("recuperando Ingredientes usados en la siguiente receta " + idReceta);
		return daoIngrediente.getAllByReceta(idReceta);
	}

	@Override
	public List<Ingrediente> listarIngredientesFueraReceta(long idReceta) {

		logger.trace("recuperando Ingredientes no usados en la siguiente receta " + idReceta);
		return daoIngrediente.listadoFueraDeReceta(idReceta);
	}
	
	@Override
	public int getLikes(long idReceta) {

		logger.trace("recuperando Ingredientes no usados en la siguiente receta " + idReceta);
		return daoReceta.getLikes(idReceta);
	}
	
	@Override
	public boolean addLikes(long idReceta) {

		logger.trace("recuperando Ingredientes no usados en la siguiente receta " + idReceta);
		return daoReceta.addLikes(idReceta);
	}

}
