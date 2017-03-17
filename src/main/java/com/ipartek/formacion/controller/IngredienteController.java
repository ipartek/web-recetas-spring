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

import com.ipartek.formacion.domain.FormularioBusqueda;
import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.service.ServiceIngrediente;

@Controller
public class IngredienteController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	ServiceIngrediente serviceIngrediente;

	@RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
	public String listar(Model model) {
		logger.info("Listado de ingredientes sin filtrar");
		//TODO comprobar ordenacion
		model.addAttribute("ingredientes", serviceIngrediente.listar(""));
		model.addAttribute("formularioBusqueda", new FormularioBusqueda());
		model.addAttribute("total", serviceIngrediente.total());
		return "ingrediente/index";
	}

	@RequestMapping(value = "/ingrediente", method = RequestMethod.POST)
	public String listarFiltrando(@Valid FormularioBusqueda formularioBusqueda, BindingResult bindingResult,
			Model model) {

		logger.info("Listado de ingredientes filtrados " + formularioBusqueda);

		ArrayList<Ingrediente> ingredientesFiltrados = new ArrayList<Ingrediente>();

		if (bindingResult.hasErrors()) {
			// mostrar ultimos ingredientes
			//TODO comprobar ordenacio0n
			ingredientesFiltrados = (ArrayList<Ingrediente>) serviceIngrediente.listar("");
		} else {
			ingredientesFiltrados = (ArrayList<Ingrediente>) serviceIngrediente.listar(formularioBusqueda.getNombre(),
					formularioBusqueda.isOrdenAscendente());
		}

		model.addAttribute("ingredientes", ingredientesFiltrados);
		model.addAttribute("total", serviceIngrediente.total());

		return "ingrediente/index";
	}

	@RequestMapping(value = "/ingrediente/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("ingrediente", new Ingrediente());
		return "ingrediente/form";
	}

	@RequestMapping(value = "/ingrediente/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		model.addAttribute("ingrediente", serviceIngrediente.buscarPorId(id));
		return "ingrediente/form";
	}

	@RequestMapping(value = "/ingrediente/crear", method = RequestMethod.POST)
	public String crear(@Valid Ingrediente ingrediente, BindingResult bindingResult) {

		logger.info("recibimos datos del formulario " + ingrediente);

		// validar datos del formulario
		if (!bindingResult.hasErrors()) {

			if (ingrediente.getId() == -1) {
				serviceIngrediente.crear(ingrediente);
			} else {
				serviceIngrediente.modificar(ingrediente);
			}
		} else {
			logger.info("formulario con datos no validos");
		}

		return "ingrediente/form";
	}

	@RequestMapping(value = "/ingrediente/delete/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable int id, Model model) {

		logger.info("eliminar ingrediente " + id);
		String msg = "Ingrediente no eliminado, posiblemente exista en una receta";

		if (serviceIngrediente.eliminar(id)) {
			msg = "Ingrediente Eliminado con exito";
		}
		model.addAttribute("msg", msg);
		//comprobar ordenacion
		model.addAttribute("ingredientes", serviceIngrediente.listar(""));
		return "ingrediente/index";
	}

}
