package com.ipartek.formacion.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

/**
 * Controlador para responder con HTML a una llamada Ajax desde el cliente.<br>
 * 
 * @author Curso
 *
 */
@Controller
public class EjemploCheckUserHtml {

	@Autowired
	private ServiceUsuario serviceUsuario;

	@RequestMapping("/testCheckUser")
	public @ResponseBody String loginDisponible(@RequestParam("nombre") String nombre) {

		Usuario u = serviceUsuario.existe(nombre);
		String msgHtml = null;
		if (u == null) {

			msgHtml = "<div class=\"alert alert-success\" role=\"alert\"><strong>OK!!</strong> El nombre <b>" + nombre
					+ "</b> no existe.</div>";

		} else {

			msgHtml = "<div class=\"alert alert-danger\" role=\"alert\"><strong>Error!!</strong> Nombre <b>" + nombre
					+ "</b> Existente</div>";

		}

		return msgHtml;

	}

	@RequestMapping("/testListUser")
	public @ResponseBody ArrayList<Usuario> listarUsuario() {

		return (ArrayList<Usuario>) serviceUsuario.listar();

	}
}
