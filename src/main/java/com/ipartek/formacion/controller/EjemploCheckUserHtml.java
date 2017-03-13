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
 * Controlador para responder con un Html a una llamada Ajax desde el cleinte.
 * @author Curso
 *
 */
@Controller
public class EjemploCheckUserHtml {

	@Autowired
	ServiceUsuario serviceusuario;
	
	
	@RequestMapping("/testCheckUser")
	public @ResponseBody String loginDisponible(@RequestParam("nombre") String nombre){
		String msg = "<div class=\"alert alert-danger\" role=\"alert\"><span>Error! " + nombre + " EXISTE</span></div>";
		if(serviceusuario.existe(nombre) == null){
			msg = "<div class=\"alert alert-success\" role=\"alert\"><span> " + nombre + " NO EXISTE</span></div>";
		}
		
		return msg;
	}
	
	@RequestMapping("/testLisrUser")
	public @ResponseBody ArrayList<Usuario> listarUsuarios(){
		return (ArrayList<Usuario>) serviceusuario.listar();
		
	}
	
	
	
}
