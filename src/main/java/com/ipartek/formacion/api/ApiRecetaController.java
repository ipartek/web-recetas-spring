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

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/receta")
public class ApiRecetaController {	
	
	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);
	
	@Autowired
	ServiceReceta serviceReceta;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Receta>> listar(){
		ResponseEntity<ArrayList<Receta>> response = null;
		ArrayList<Receta> receta = (ArrayList<Receta>) this.serviceReceta.listar();
		if (receta.size() > 0) {
			response = new ResponseEntity<ArrayList<Receta>>(receta, HttpStatus.OK);
		} else {
			response = new ResponseEntity<ArrayList<Receta>>(HttpStatus.NO_CONTENT);
		}	
		return response;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Receta> detalle(@PathVariable int id){
		ResponseEntity<Receta> response = null;
		Receta receta = this.serviceReceta.buscarPorID(id);
		
		if (receta.getId() != -1) {
			response = new ResponseEntity<Receta>(receta, HttpStatus.OK);
		} else {
			response = new ResponseEntity<Receta>(HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	/**
	 * 
	 * @param receta
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> altaReceta(@RequestBody Receta receta){
		ResponseEntity response = null;
		try{
			LOG.info("Dar de alta nueva receta: " + receta);
			response = new ResponseEntity<Receta>(HttpStatus.OK);
			//Todo validar datos
			
			//TODO siempre usamos el mismo usuario para las recetas
			Usuario client = new Usuario();
			client.setId(1);
			receta.setUsuario(client);
			if (this.serviceReceta.crear(receta)){
				response = new ResponseEntity<Receta>(receta, HttpStatus.CREATED);
			}
		}catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarReceta(@PathVariable int id){
		ResponseEntity<Void> response = null;
		try {
			if(this.serviceReceta.eliminar(id)) {
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
	public ResponseEntity<Receta> modificaIngrediente(@RequestBody Receta receta){
		ResponseEntity<Receta> response = null;
		try {
			if(this.serviceReceta.modificar(receta)) {
				response = new ResponseEntity<Receta>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Receta>(HttpStatus.ACCEPTED);
			}
		} catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}				
	}
}