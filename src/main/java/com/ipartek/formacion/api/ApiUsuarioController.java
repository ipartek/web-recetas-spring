package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.MensajeResponse;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
@RequestMapping(value = "/api/usuario")
public class ApiUsuarioController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApiUsuarioController.class);
	
	@Autowired
	ServiceUsuario serviceUsuario;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Usuario>> listar(){
		ResponseEntity<ArrayList<Usuario>> response = null;
		ArrayList<Usuario> ingredientes = (ArrayList<Usuario>) this.serviceUsuario.listarSinPassword();
		if (ingredientes.size() > 0) {
			response = new ResponseEntity<ArrayList<Usuario>>(ingredientes, HttpStatus.OK);
		} else {
			response = new ResponseEntity<ArrayList<Usuario>>(HttpStatus.NO_CONTENT);
		}	
		return response;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> detalle(@PathVariable int id){
		ResponseEntity<Usuario> response = null;
		Usuario usuario = this.serviceUsuario.buscarPorId(id);
		
		if (usuario != null) {
			response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> altaUsuario(@RequestBody Usuario usuario){
		ResponseEntity<Usuario> response = null;
		try{
			LOG.info("Crear nuevo usuario: " + usuario);
			response = new ResponseEntity<Usuario>(HttpStatus.OK);
			//Todo validar datos
			
			if (this.serviceUsuario.crear(usuario)){
				response = new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
			}
		}catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarUsuario(@PathVariable int id){
		ResponseEntity<Void> response = null;
		try {
			if(this.serviceUsuario.eliminar(id)) {
				response = new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Void>(HttpStatus.ACCEPTED);
			}
		} catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<?> modificaUsuario(@RequestBody Usuario usuario){
		ResponseEntity<?> response = null;
		try {
			if(this.serviceUsuario.modificar(usuario)) {
				response = new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
			} else {
				MensajeResponse msg = new MensajeResponse();
				msg.setCodigo("202 ACCEPTED");
				msg.setMensaje("No se pueden repetir los nombres ni emails.");
				response = new ResponseEntity<MensajeResponse>(msg, HttpStatus.ACCEPTED);
			}
		} catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}				
	}
}