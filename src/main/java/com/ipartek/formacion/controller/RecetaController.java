package com.ipartek.formacion.controller;

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

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
public class RecetaController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@RequestMapping(value = "/receta", method = RequestMethod.GET)
	public String listar(Model model) {

		model.addAttribute("recetas", serviceReceta.listar());

		return "receta/index";
	}

	@RequestMapping(value = "/receta/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("receta", new Receta());

		return "receta/form";
	}

	@RequestMapping(value = "/receta/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		model.addAttribute("receta", serviceReceta.buscarPorID(id));

		return "receta/form";
	}

	@RequestMapping(value = "/receta/crear", method = RequestMethod.POST)
	public String crear(@Valid Receta receta, BindingResult bindingResult, Model model) {

		logger.info("recibimos datos del formulario " + receta);
		String msg = null;

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
		model.addAttribute("recetas", serviceReceta.listar());

		return "receta/index";
	}

	@RequestMapping(value = "/receta/{idReceta}/delete/ingrediente/{idIngrediente}", method = RequestMethod.GET)
	public String eliminarIngrediente(@PathVariable int idReceta, @PathVariable int idIngrediente, Model model) {

		logger.info("eliminar ingrediente " + idIngrediente + " de Receta " + idReceta);
		String msg = null;

		msg = "Elimnado ingrediente X";

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("msg", msg);
		return "receta/form";
	}

}
