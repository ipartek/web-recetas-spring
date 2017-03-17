package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceIngrediente;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/ingrediente")
public class ApiIngredientesController {

	@Autowired
	private ServiceIngrediente serviceIngrediente;
	

	
	/**
	 * Devuelve todos las recetas de la aplicacion
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Ingrediente>> listarRecetas(@RequestParam("order") String order) {
		ArrayList<Ingrediente> listaR = (ArrayList<Ingrediente>) serviceIngrediente.listar(order);
		ResponseEntity<ArrayList<Ingrediente>> response=new ResponseEntity<ArrayList<Ingrediente>>(listaR, HttpStatus.OK);
		if (listaR == null) {
			response=new ResponseEntity<ArrayList<Ingrediente>>(listaR, HttpStatus.NO_CONTENT);
		} 
		return response;
	}
	
	/**
	 * DEVUELVE UNA RECETA
	 * 
	 * @param idReceta
	 * @return
	 */
	@RequestMapping(value = "/{idIngrediente}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Ingrediente> getingrediente(@RequestParam("id") String id) {
		Ingrediente i=serviceIngrediente.buscarPorId(Long.parseLong(id));
		ResponseEntity<Ingrediente> response=new ResponseEntity<Ingrediente>(i, HttpStatus.OK);
		if (i == null) {
			return new ResponseEntity<Ingrediente>(i, HttpStatus.NO_CONTENT);
		} 
		return response;
	}
	
	/**
	 * Elimina una receta
	 * 
	 * @param idReceta
	 * @return
	 */
	@RequestMapping(value = "/{idIngrediente}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable long idIngrediente) {
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		if (serviceIngrediente.eliminar(idIngrediente)) {
			response= new ResponseEntity<Void>(HttpStatus.OK);
		} 
		return response;
	}
	
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Ingrediente> insertUser(@RequestBody Ingrediente ingrediente) {
		ResponseEntity<Ingrediente> response=new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
		try {
			if (!serviceIngrediente.crear(ingrediente)) {
				response= new ResponseEntity<Ingrediente>(HttpStatus.OK);
			}
		} catch (Exception e) {
			response= new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
