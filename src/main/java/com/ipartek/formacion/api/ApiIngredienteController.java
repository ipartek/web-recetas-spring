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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.service.ServiceIngrediente;

@Controller
@RequestMapping(value = "/api/ingrediente/")
public class ApiIngredienteController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiIngredienteController.class);
	
	@Autowired
	ServiceIngrediente serviceIngrediente;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Ingrediente> listar() {

		return (ArrayList<Ingrediente>) this.serviceIngrediente.listar();
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Ingrediente detalle(@PathVariable int id) {

		// TODO controlar si no existe receta

		Ingrediente ingrediente = this.serviceIngrediente.buscarPorId(id);
		return ingrediente;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> altaIngrediente(@RequestBody Ingrediente ingrediente){
		ResponseEntity response = null;
		
		try{
			LOG.info("Dar de alta nueva receta: " + ingrediente);
			response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
			//TODO validar datos
			
			if(this.serviceIngrediente.crear(ingrediente)){
				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
			}
			
		}catch(Exception e){
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}finally{
			
			return response;
			
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> deleteIngrediente(@RequestBody int id){
		ResponseEntity response = null;
		
		try{			
			if(this.serviceIngrediente.eliminar(id)){
				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			}
			
		}catch(Exception e){
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}finally{
			
			return response;
			
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> modificarIngrediente(@RequestBody Ingrediente ingrediente){
		ResponseEntity response = null;
		
		try{			
			if(this.serviceIngrediente.modificar(ingrediente)){
				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			}
			
		}catch(Exception e){
			LOG.error("Excepcion sin controlar",e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}finally{
			
			return response;
			
		}
	}
	
	
}
