package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
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

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/receta/")
public class ApiRecetaController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);

	@Autowired
	ServiceReceta servideReceta;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Receta> listar() {

		// TODO Fallo seguridad mostrar informacion del usuario privada
		return (ArrayList<Receta>) this.servideReceta.listarConUsuarios();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Receta detalle(@PathVariable int id) {

		// TODO controlar si no existe receta

		Receta receta = this.servideReceta.buscarPorID(id);
		return receta;
	}

	/**
	 * 
	 * @param receta
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> altaReceta(@RequestBody Receta receta) {

		ResponseEntity<Receta> response = null;
		try {
			LOG.info("Dar de alta nueva receta: " + receta);

			response = new ResponseEntity<Receta>(HttpStatus.OK);
			// TODO validar datos

			// TODO siempre usamos el mismo usuario para las recetas
			Usuario client = new Usuario();
			client.setId(1);
			receta.setUsuario(client);

			if (this.servideReceta.crear(receta)) {
				response = new ResponseEntity<Receta>(receta, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			return response;
		}
	}
	
	@RequestMapping(value = "{id}/likes", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getLike(@PathVariable int id) {
		ResponseEntity<String> response = null;
		int resul = 0;
	    resul = this.servideReceta.getLikes(id);
	    String likes = "{\"likes\": " + resul + "}";
	    if (resul > -1) {
	    	response = new ResponseEntity<String>(likes, HttpStatus.OK);
	    } else {
	    	response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	    }
	    return response;

	}

	@RequestMapping(value = "{id}/likes", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> addtLike(@PathVariable int id) {
		boolean resul;
		ResponseEntity<ObjectNode> response = null;
		resul = this.servideReceta.addLikes(id);
		
		if (resul) {
			int cantidad = this.servideReceta.getLikes(id);
			ObjectNode responseBody = JsonNodeFactory.instance.objectNode();
			responseBody.put("likes", cantidad);
			response = new ResponseEntity<ObjectNode>(responseBody, HttpStatus.OK);
		} else {
			response = new ResponseEntity<ObjectNode>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
		
}
