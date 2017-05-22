package com.ipartek.formacion.api;

import java.util.ArrayList;

import javax.validation.Valid;

import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.hibernate.validator.internal.engine.ServiceLoaderBasedConstraintDefinitionContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceIngrediente;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/receta/")
public class ApiRecetaController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);

	@Autowired
	ServiceReceta servideReceta;

	@Autowired
	ServiceIngrediente serviceIngrediente;

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

	@RequestMapping(value = "{idReceta}/ingrediente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> addIngrediente(
					@PathVariable int idReceta, 
					@RequestBody Ingrediente ingred		
				) {

		ResponseEntity<?> response = null;
		Ingrediente i = null;
		Receta r = null;
		
		
		r = this.servideReceta.buscarPorID(idReceta);
		if (r.getId() != -1 && ingred.getNombre() != "") {

			i = this.serviceIngrediente.buscarPorNombre(ingred.getNombre());
			if (i == null) {
				this.serviceIngrediente.crear(ingred);
			} else {
				ingred.setId(i.getId());
			}

			if (this.servideReceta.recuperarIngrediente(idReceta, ingred.getId()) == null) {

				if (this.servideReceta.addIngrediente(idReceta, ingred)) {
					response = new ResponseEntity<Ingrediente>(ingred, HttpStatus.CREATED);
				}
			} else {

				ObjectNode responseBody = JsonNodeFactory.instance.objectNode();
				responseBody.put("error", "El ingrediente ya existe en la receta");
				response = new ResponseEntity<ObjectNode>(responseBody, HttpStatus.ACCEPTED);

			}
		} // End if de si la receta existe
		else {
			// Si la receta no exite le hago saber al cliente que la receta no
			// existe
			ObjectNode responseBody = JsonNodeFactory.instance.objectNode();
			responseBody.put("error", "La receta no existe o el nombre del ingrediente esta vacio");
			response = new ResponseEntity<ObjectNode>(responseBody, HttpStatus.BAD_REQUEST);
		}
		return response;

	}
	
	@RequestMapping(value = "{idReceta}/ingrediente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<Ingrediente> recuperarIngrediente(
									@PathVariable int idReceta, 
									@RequestParam(required=false, value="disponibles") boolean disp,
									@RequestParam(required=false, value="filter") String filter
									){
		ArrayList<Ingrediente> ingredientes = null;
		if(disp){
			if(filter!=null){
				ingredientes=(ArrayList<Ingrediente>) servideReceta.listarlikeIngredientesFueraReceta(idReceta, filter);
			}else{
				ingredientes=(ArrayList<Ingrediente>) servideReceta.listarIngredientesFueraReceta(idReceta);
			}
		}else{
			ingredientes=(ArrayList<Ingrediente>) serviceIngrediente.listar("asc");
		}
		return ingredientes;
	}
	
	@RequestMapping(value = "{idReceta}/ingrediente/{idIngrediente}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> eliminarRecetaIngrediente(@PathVariable int idReceta, @PathVariable int idIngrediente) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("Eliminar un ingrediente" + idIngrediente);

			// TODO comprobar que los parametros son validos.
			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);

			if (this.servideReceta.eliminarIngrediente(idReceta, idIngrediente)) {
				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException e) {

			LOG.error("error", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.ACCEPTED);

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		} 
		
		return response;


	}

	@RequestMapping(value = "{idReceta}/ingrediente/{idIngrediente}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> modificar(@PathVariable int idReceta, @RequestBody Ingrediente ingrediente) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("Modificar un ingrediente" + ingrediente);

			// TODO validar datos
			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);

			// TODO llamar al servicio

			if (this.serviceIngrediente.modificar(ingrediente)) {
				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}
	
}
