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
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.service.ServiceIngrediente;

@Controller
@RequestMapping(value = "/api/ingrediente")
public class ApiIngredienteController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiIngredienteController.class);
	
	@Autowired
	ServiceIngrediente serviceIngrediente;
		
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Ingrediente>> listar(@RequestParam(value="order", required=false) String text){
		ResponseEntity<ArrayList<Ingrediente>> response = null;
		ArrayList<Ingrediente> lista = null;
		try {
			if (text == null) {
				LOG.info("Listado por defecto");
				lista = (ArrayList<Ingrediente>) this.serviceIngrediente.listar();
				response = new ResponseEntity<ArrayList<Ingrediente>>(lista, HttpStatus.OK);
			} else if("asc".equals(text.toLowerCase()) || "desc".equals(text.toLowerCase())) {
				LOG.info("Listado por:" + text);
				lista = (ArrayList<Ingrediente>) this.serviceIngrediente.listarOrdenado(text.toLowerCase());
				response = new ResponseEntity<ArrayList<Ingrediente>>(lista, HttpStatus.OK);
			} else {
				LOG.info("Parametros incorrectos");
				response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingrediente> detalle(@PathVariable int id){
		ResponseEntity<Ingrediente> response = null;
		Ingrediente ingrediente = this.serviceIngrediente.buscarPorId(id);
		
		if (ingrediente != null) {
			response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
		} else {
			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> altaIngrediente(@RequestBody Ingrediente ingrediente){
		ResponseEntity<Ingrediente> response = null;
		try{
			LOG.info("Crear nuevo ingrediente: " + ingrediente);
			response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			//Todo validar datos
			
			//TODO siempre usamos el mismo usuario para las recetas
			if (this.serviceIngrediente.crear(ingrediente)){
				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
			}
		}catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarIngrediente(@PathVariable int id){
		ResponseEntity<Void> response = null;
		try {
			if(this.serviceIngrediente.eliminar(id)) {
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
	public ResponseEntity<Ingrediente> modificaIngrediente(@RequestBody Ingrediente ingrediente){
		ResponseEntity<Ingrediente> response = null;
		try {
			if(this.serviceIngrediente.modificar(ingrediente)) {
				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Ingrediente>(HttpStatus.ACCEPTED);
			}
		} catch(Exception e) {
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}				
	}
}
