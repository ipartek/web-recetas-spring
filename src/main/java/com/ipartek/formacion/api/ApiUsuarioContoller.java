package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
@RequestMapping(value = "/api/usuario")
public class ApiUsuarioContoller {
	
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	/**
	 * Devuelve todos las recetas de la aplicacion
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Usuario>> listarRecetas() {
		ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) serviceUsuario.listarPintarUsuarios();
		ResponseEntity<ArrayList<Usuario>> response=new ResponseEntity<ArrayList<Usuario>>(listaUsuario, HttpStatus.OK);
		if (listaUsuario == null) {
			response=new ResponseEntity<ArrayList<Usuario>>(listaUsuario, HttpStatus.NO_CONTENT);
		} 
		return response;
	}

}
