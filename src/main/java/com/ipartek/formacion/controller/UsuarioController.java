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

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	ServiceUsuario serviceUsuario;

	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public String listar(Model model) {

		model.addAttribute("usuarios", serviceUsuario.listar());

		return "usuario/index";
	}

	@RequestMapping(value = "/usuario/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "usuario/form";
	}

	@RequestMapping(value = "/usuario/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {

		model.addAttribute("usuario", serviceUsuario.buscarPorId(id));
		return "usuario/form";
	}

	@RequestMapping(value = "/usuario/crear", method = RequestMethod.POST)
	public String crear(@Valid Usuario usuario, BindingResult bindingResult, Model model) {

		logger.info("recibimos datos del formulario " + usuario);
		String msg = null;

		// validar datos del formulario
		if (!bindingResult.hasErrors()) {

			if (usuario.getId() == -1) {

				serviceUsuario.crear(usuario);
				msg = "Creado nuevo Usuario";

			} else {

				serviceUsuario.modificar(usuario);
				msg = "Modificado Usuario";

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
		String msg = "Usuario no eliminado";

		if (serviceUsuario.eliminar(id)) {
			msg = "Usuario Eliminada con exito";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "usuario/index";
	}
}
