package com.ipartek.formacion.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Ingrediente;

@Controller
public class IngredienteController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	ArrayList<Ingrediente> list = null;

	@RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
	public String listar(Model model) {

		list = new ArrayList<Ingrediente>();
		list.add(new Ingrediente(0, "Sal", false));
		list.add(new Ingrediente(1, "Patatas", false));
		list.add(new Ingrediente(2, "Huevos", false));
		list.add(new Ingrediente(3, "Pan", true));

		model.addAttribute("ingredientes", list);

		return "ingrediente/index";
	}

	@RequestMapping(value = "/ingrediente/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("ingrediente", new Ingrediente());
		return "ingrediente/form";
	}

	@RequestMapping(value = "/ingrediente/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		model.addAttribute("ingrediente", list.get(id));
		return "ingrediente/form";
	}

	@RequestMapping(value = "/ingrediente/crear", method = RequestMethod.POST)
	public String crear(@Valid Ingrediente ingrediente, BindingResult bindingResult) {

		logger.info("recibimos datos del formulario " + ingrediente);

		return "ingrediente/form";
	}

}
