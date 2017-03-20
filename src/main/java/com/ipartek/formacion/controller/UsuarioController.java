package com.ipartek.formacion.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Mensaje;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	private ServiceUsuario serviceUsuario;

	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public String listar(Model model) {

		model.addAttribute("usuarios", serviceUsuario.listarConRecetas());

		return "usuario/index";
	}

	@RequestMapping(value = "/usuario/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("usuario", new Usuario());

		return "usuario/form";
	}

	@RequestMapping(value = "/usuario/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		model.addAttribute("usuario", serviceUsuario.buscarPorID(id));
		model.addAttribute("recetasUsuario", serviceUsuario.listarRecetasUsuario(id));

		return "usuario/form";
	}

	@RequestMapping(value = "/usuario/crear", method = RequestMethod.POST)
	public String crear(@Valid Usuario usuario, BindingResult bindingResult, Model model) {

		logger.info("recibimos datos del formulario " + usuario);
		Mensaje msg = new Mensaje();

		// validar datos del formulario
		if (!bindingResult.hasErrors()) {

			if (usuario.getId() == -1) {

				serviceUsuario.crear(usuario);
				msg.setDescripcion("Creada nueva Receta");
				msg.setClase(Mensaje.CLASE_SUCCESS);

			} else {

				serviceUsuario.modificar(usuario);
				msg.setDescripcion("Modificada Receta");
				msg.setClase(Mensaje.CLASE_SUCCESS);
			}

		} else {

			logger.info("formulario con datos no validos");

		}

		model.addAttribute("msg", msg);
		return "usuario/form";
	}

	@RequestMapping(value = "/usuario/delete/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable int id, Model model) {

		logger.info("eliminar usuario " + id);

		Mensaje msg = new Mensaje();

		msg.setDescripcion("Usuario no eliminado, posiblemente tenga recetas");

		try {

			if (serviceUsuario.eliminar(id)) {
				msg.setDescripcion("Usuario Eliminado con exito");
				msg.setClase(Mensaje.CLASE_SUCCESS);
			}

		} catch (DataIntegrityViolationException e) {
			msg.setDescripcion(e.getMessage());
			msg.setClase(Mensaje.CLASE_DANGER);
		}

		model.addAttribute("msg", msg);
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "usuario/index";
	}

}
