package com.ipartek.formacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador para responder con HTML a una llamada Ajax desde el cliente
 * 
 * @author Curso
 *
 */
@Controller
public class EjemploCheckUserHtml {

	@RequestMapping("/testCheckUser")
	public @ResponseBody String loginDisponible(@RequestParam("nombre") String nombre) {

		return "<div class=\"alert alert-danger\" role=\"alert\"><strong>Error!</strong>Nombre <b>" + nombre
				+ "</b> Existente.</div>";
	}

}
