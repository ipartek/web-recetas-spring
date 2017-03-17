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
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/receta")
public class ApiRecetaController {

	@Autowired
	private ServiceReceta serviceReceta;
	

	/**
	 * Testea que el nombre de la receta no exista en la BBDD
	 * 
	 * @param nombre
	 * @return
	 */
	@RequestMapping(value = "/testCheckReceta", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Receta> altaUsuario(@RequestParam("nombre") String nombre) {
		Receta r = serviceReceta.buscarPorNombre(nombre);
		ResponseEntity<Receta> response=new ResponseEntity<Receta>(r, HttpStatus.OK);
		if (r == null) {
			response=new ResponseEntity<Receta>(r, HttpStatus.NO_CONTENT);
		} 
		return response;
	}

	/**
	 * Devuelve todos las recetas de la aplicacion
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Receta>> listarRecetas(@RequestParam("order") String order) {
		ArrayList<Receta> listaR = (ArrayList<Receta>) serviceReceta.listar(order);
		ResponseEntity<ArrayList<Receta>> response=new ResponseEntity<ArrayList<Receta>>(listaR, HttpStatus.OK);
		if (listaR == null) {
			response=new ResponseEntity<ArrayList<Receta>>(listaR, HttpStatus.NO_CONTENT);
		} 
		return response;
	}

	/**
	 * DEVUELVE UNA RECETA
	 * 
	 * @param idReceta
	 * @return
	 */
	@RequestMapping(value = "/{idReceta}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Receta> getReceta(@RequestParam("id") String id) {
		Receta r = serviceReceta.buscarPorID(Long.parseLong(id));
		ResponseEntity<Receta> response=new ResponseEntity<Receta>(r, HttpStatus.OK);
		if (r == null) {
			return new ResponseEntity<Receta>(r, HttpStatus.NO_CONTENT);
		} 
		return response;
	}

	/**
	 * Crea una receta
	 * 
	 * @param receta
	 */
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Receta> insertUser(@RequestBody Receta receta) {
		ResponseEntity<Receta> response=new ResponseEntity<Receta>(receta, HttpStatus.CREATED);
		try {
			if (!serviceReceta.crear(receta)) {
				response= new ResponseEntity<Receta>(HttpStatus.OK);
			}
		} catch (Exception e) {
			response= new ResponseEntity<Receta>(receta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	

	/**
	 * Modifica una receta
	 * 
	 * @param idReceta
	 * @param receta
	 */
	@RequestMapping(value = "/{idReceta}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable long idReceta, @RequestBody Receta receta) {
		serviceReceta.modificar(receta);
	}

	/**
	 * Elimina una receta
	 * 
	 * @param idReceta
	 * @return
	 */
	@RequestMapping(value = "/{idReceta}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable long idReceta) {
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		if (serviceReceta.eliminar(idReceta)) {
			response= new ResponseEntity<Void>(HttpStatus.OK);
		} 
		return response;

	}
	@RequestMapping(value = "/{idReceta}/ingrediente/{idIngrediente}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Receta>> insertIngredienteOnReceta(@RequestParam("idIngrediente") String idIngrediente,@RequestParam("idReceta") String idReceta,@RequestParam("cantidad") String cantidad) {
		System.out.println("HGOLAAAA");
		Ingrediente i=new Ingrediente();
		i.setId(Long.parseLong(idIngrediente));
		i.setCantidad(cantidad);
		ResponseEntity<ArrayList<Receta>>  response=new ResponseEntity<ArrayList<Receta>>(serviceReceta.listar("desc"), HttpStatus.CREATED);
		try {
			if (!serviceReceta.addIngrediente(Long.parseLong(idReceta), i)) {
				response= new ResponseEntity<ArrayList<Receta>>(serviceReceta.listar("desc"),HttpStatus.OK);
			}
		} catch (Exception e) {
			response= new ResponseEntity<ArrayList<Receta>>(serviceReceta.listar("desc"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
