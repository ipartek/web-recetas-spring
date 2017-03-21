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

	@Override()
	public List<Receta> listar() {
		logger.trace("listar recetas");
		return daoReceta.getAll();
	}

	@Override()
	public List<Receta> listarConUsuarios() {

		logger.trace("listar recetas con usuarios asociados");

		// TODO Usar un ResultSetExtraxtor en vez de llamar a dos DAOS
		ArrayList<Receta> recetas = (ArrayList<Receta>) daoReceta.getAllWhitUsers();

		return recetas;
	}

	@Override()
	public Receta buscarPorID(long id) {
		logger.trace("Buscamos receta por id: " + id);

		Receta receta = daoReceta.getById(id);
		receta.setIngredientes((ArrayList<Ingrediente>) daoIngrediente.getAllByReceta(id));
		receta.setUsuario(daoUsuario.getUserByReceta(id));

		return receta;
	}

	@Override()
	public boolean crear(Receta r) {
		logger.trace("Creando receta: " + r);
		return daoReceta.insert(r);
	}

	@Override()
	public boolean modificar(Receta r) {
		logger.trace("Modificando receta: " + r);
		return daoReceta.update(r);
	}

	@Override()
	public boolean eliminar(long id) {
		logger.trace("Eliminando por id: " + id);
		return daoReceta.delete(id);
	}

	@Override()
	public boolean eliminarIngrediente(long idReceta, long idIngrediente) {
		logger.trace("Eliminando por id: " + idIngrediente + "de la receta " + idReceta);
		return daoIngrediente.deleteByReceta(idReceta, idIngrediente);
	}

	@Override()
	public boolean modificarIngrediente(long idReceta, Ingrediente i) {
		logger.trace("Modificar ingrediente: " + i + "de la receta " + idReceta);
		return daoIngrediente.updateByReceta(idReceta, i);
	}

	@Override()
	public Ingrediente recuperarIngrediente(long idReceta, long idIngrediente) {
		logger.trace("Recuperar ingrediente: " + idIngrediente + "de la receta " + idReceta);
		return daoIngrediente.getByReceta(idReceta, idIngrediente);
	}

	@Override()
	public boolean addIngrediente(long idReceta, Ingrediente i) {
		logger.trace("Añadir ingrediente: " + i + "de la receta " + idReceta);
		return daoIngrediente.insertByReceta(idReceta, i);
	}

	@Override()
	public List<Ingrediente> listarIngredientesNoIncluidas(long idReceta) {
		logger.trace("Listar ingredientes no incluidos en la receta " + idReceta);
		return daoIngrediente.getAllByNotInReceta(idReceta);
	}

	@Override()
	public Usuario getUsuarioReceta(long idReceta) {
		logger.trace("Conseguir usuario de la receta " + idReceta);
		return daoUsuario.getUserByReceta(idReceta);
	}

}
