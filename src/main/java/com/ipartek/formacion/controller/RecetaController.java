package com.ipartek.formacion.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceIngrediente;
import com.ipartek.formacion.service.ServiceReceta;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
public class RecetaController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@Autowired
	private ServiceIngrediente serviceIngrediente;

	@Autowired
	private ServiceUsuario serviceUsuario;

	@RequestMapping(value = "/receta", method = RequestMethod.GET)
	public String listar(Model model) {

		ArrayList<Receta> recetas = (ArrayList<Receta>) serviceReceta.listarConUsuarios();

		model.addAttribute("recetas", recetas);

		return "receta/index";
	}

	@RequestMapping(value = "/receta/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("receta", new Receta());
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "receta/form";
	}

	@RequestMapping(value = "/receta/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		Receta receta = serviceReceta.buscarPorID(id);
		// Usuario usuario = serviceReceta.getUsuarioReceta(id);
		// receta.setUsuario(usuario);

		// ArrayList<Usuario> usuarios = (ArrayList<Usuario>)
		// serviceUsuario.listar();
		// usuarios.remove(usuario);

		model.addAttribute("receta", receta);
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "receta/form";
	}

	@RequestMapping(value = "/receta/crear", method = RequestMethod.POST)
	public String crear(@Valid Receta receta, BindingResult bindingResult, Model model) {

		logger.info("recibimos datos del formulario " + receta);
		String msg = null;

		Usuario usuario = serviceUsuario.buscarPorID(receta.getUsuario().getId());
		receta.setUsuario(usuario);

		// validar datos del formulario
		if (!bindingResult.hasErrors()) {

			if (receta.getId() == -1) {

				serviceReceta.crear(receta);
				msg = "Creada nueva Receta";

			} else {

				serviceReceta.modificar(receta);
				msg = "Modificada Receta";

			}

		} else {

			logger.info("formulario con datos no validos");

		}

		model.addAttribute("msg", msg);
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "receta/form";
	}

	@RequestMapping(value = "/receta/delete/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable int id, Model model) {

		logger.info("eliminar ingrediente " + id);
		String msg = "Receta no eliminado, posiblemente exista en otro sitio";

		if (serviceReceta.eliminar(id)) {
			msg = "Receta Eliminada con exito";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("recetas", serviceReceta.listarConUsuarios());

		return "receta/index";
	}

	@RequestMapping(value = "/receta/{idReceta}/delete/ingrediente/{idIngrediente}", method = RequestMethod.GET)
	public String eliminarIngrediente(@PathVariable int idReceta, @PathVariable int idIngrediente, Model model) {

		logger.info("eliminar ingrediente " + idIngrediente + " de Receta " + idReceta);
		String msg = null;

		if (serviceReceta.eliminarIngrediente(idReceta, idIngrediente)) {
			msg = "Elimnado ingrediente: " + idIngrediente;
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("msg", msg);
		return "receta/form";
	}

	@RequestMapping(value = "/receta/{idReceta}/edit/ingrediente/{idIngrediente}", method = RequestMethod.GET)
	public String irIngredienteModificar(@PathVariable int idReceta, @PathVariable int idIngrediente, Model model) {

		logger.info("Ir a mostrar formulario " + idIngrediente + " de Receta " + idReceta);

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("ingrediente", serviceReceta.recuperarIngrediente(idReceta, idIngrediente));

		return "receta/formIngrediente";
	}

	@RequestMapping(value = "/receta/{idReceta}/edit/ingrediente", method = RequestMethod.POST)
	public String editarIngrediente(@PathVariable int idReceta, @Valid Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {

		logger.info("Modificando ingrediente " + ingrediente + " de Receta " + idReceta);
		String msg = "No se pudo cambiar ingrediente";

		if (serviceReceta.modificarIngrediente(idReceta, ingrediente)) {
			msg = "Ingrediente cambiado";
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("msg", msg);

		return "receta/formIngrediente";
	}

	@RequestMapping(value = "/receta/{idReceta}/recuperar/ingredientes", method = RequestMethod.GET)
	public String recuperarIngredientes(@PathVariable int idReceta, Model model) {

		logger.info("Recuperando ingredientes para la Receta " + idReceta);

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("ingredientes", serviceReceta.listarIngredientesNoIncluidas(idReceta));

		return "receta/formIngrediente";
	}

	@RequestMapping(value = "/receta/{idReceta}/add/ingrediente", method = RequestMethod.POST)
	public String addIngrediente(@PathVariable int idReceta, @Valid Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {

		logger.info("Añadiendo ingrediente " + ingrediente + " de Receta " + idReceta);
		String msg = "No se pudo añadir ingrediente";

		if (serviceReceta.addIngrediente(idReceta, ingrediente)) {
			msg = "Se ha añadido el ingrediente:  " + ingrediente.getNombre();
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("msg", msg);
		model.addAttribute("ingredientes", serviceReceta.listarIngredientesNoIncluidas(idReceta));

		return "receta/formIngrediente";
		// return ("receta/edit/" + idReceta);
	}

}
