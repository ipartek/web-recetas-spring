package com.ipartek.formacion.api;

import java.util.ArrayList;

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
	ServiceReceta serviceReceta;

	@Autowired
	ServiceIngrediente serviceIngrediente;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Receta> listar(@RequestParam(value = "filter", required = false) String filter) {

		ArrayList<Receta> recetas = null;

		if (filter != null) {

			recetas = (ArrayList<Receta>) this.serviceReceta.listar(filter);

		} else {

			// TODO Fallo seguridad mostrar informacion del usuario privada
			recetas = (ArrayList<Receta>) this.serviceReceta.listarConUsuarios();

		}

		return recetas;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Receta detalle(@PathVariable int id) {

		// TODO controlar si no existe receta

		Receta receta = this.serviceReceta.buscarPorID(id);
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

			if (this.serviceReceta.crear(receta)) {
				response = new ResponseEntity<Receta>(receta, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	@RequestMapping(value = "{id}/likes", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ObjectNode> modificarLikes(@PathVariable int id) {

		ResponseEntity<ObjectNode> response = null;

		try {
			LOG.info("Autoincrementar en 1 los likes una Receta" + id);

			response = new ResponseEntity<ObjectNode>(HttpStatus.NO_CONTENT);

			if (this.serviceReceta.modificarLikes(id)) {

				Receta r = this.serviceReceta.buscarPorID(id);

				ObjectNode likes = JsonNodeFactory.instance.objectNode(); // initializing
				likes.put("likes", r.getLikes()); // building

				response = new ResponseEntity<ObjectNode>(likes, HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<ObjectNode>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

	/**
	 * Recupera los ingredientes que se encuentran en la BBDD. Si se envia el
	 * parametro "disponible" recuperaremos los ingredientes que estan
	 * disponibles sin incluir en la receta. Sino, recuperaremos todos los
	 * ingredientes de la BBDD.
	 * 
	 * @param id
	 *            id de la Receta
	 * @param disponibles
	 *            Parametro que nos indica si queremos todos los ingredientes o
	 *            solo los ingredientes que no esten incluidos en la receta
	 * 
	 * @return Retorna el codigo de estado Http y List<Ingrediente> en caso de
	 *         que todo valla OK.
	 */
	@RequestMapping(value = "{id}/ingrediente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ArrayList<Ingrediente>> recuperarIngredientes(@PathVariable int id,
			@RequestParam(value = "disponibles", required = false) boolean disponibles,
			@RequestParam(value = "filter", required = false) String filter) {

		ResponseEntity<ArrayList<Ingrediente>> response = null;

		try {

			response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.NO_CONTENT);

			ArrayList<Ingrediente> ingredientes;

			if (disponibles) {
				LOG.info("listar ingredientes disponibles" + id);

				if (filter != null) {
					ingredientes = (ArrayList<Ingrediente>) this.serviceReceta.listarIngredientesFueraReceta(id,
							filter);
				} else {
					ingredientes = null;
				}

			} else {

				ingredientes = (ArrayList<Ingrediente>) this.serviceReceta.listarIngredientes(id);

			}

			response = new ResponseEntity<ArrayList<Ingrediente>>(ingredientes, HttpStatus.OK);

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

	@RequestMapping(value = "{id}/ingrediente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> crearIngrediente(@PathVariable int id,
			@RequestBody() Ingrediente ingrediente) {

		ResponseEntity<?> response = null;
		ObjectNode mensaje;

		try {
			LOG.info("añadir ingrediente nuevo a la receta " + id);

			mensaje = JsonNodeFactory.instance.objectNode(); // initializing
			mensaje.put("mensaje", "Bad Request"); // building

			response = new ResponseEntity<ObjectNode>(mensaje, HttpStatus.BAD_REQUEST);

			// buscar si existe el ingrediente
			Ingrediente iExiste = this.serviceReceta.buscarNombreIngrediente(ingrediente);

			if (iExiste == null) {

				// El ingrediente no existe. Insertar nueva
				if (this.serviceReceta.crearIngrediente(ingrediente)) {

					// añadir el ingrediente nuevo a la receta
					if (this.serviceReceta.addIngrediente(id, ingrediente)) {
						response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
					} else {
						mensaje = JsonNodeFactory.instance.objectNode(); // initializing
						mensaje.put("mensaje", "No es posible insertar un nuevo ingrediente"); // building

						response = new ResponseEntity<ObjectNode>(mensaje, HttpStatus.BAD_REQUEST);
					}

				}

			} else {

				// Buscar si el ingrediente si se encuentra insertada en la
				// receta
				ingrediente.setId(iExiste.getId());
				Ingrediente iExisteReceta = this.serviceReceta.recuperarIngrediente(id, ingrediente.getId());

				if (iExisteReceta == null) {
					if (this.serviceReceta.addIngrediente(id, ingrediente)) {
						response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
					} else {
						mensaje = JsonNodeFactory.instance.objectNode(); // initializing
						mensaje.put("mensaje", "No es posible añadir ingrediente a la receta"); // building

						response = new ResponseEntity<ObjectNode>(mensaje, HttpStatus.BAD_REQUEST);
					}

				} else {
					// si existe en la receta
					mensaje = JsonNodeFactory.instance.objectNode(); // initializing
					mensaje.put("mensaje", "El ingrediente ya existe en la receta"); // building

					response = new ResponseEntity<ObjectNode>(mensaje, HttpStatus.ACCEPTED);
				}
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

	@RequestMapping(value = "{id}/ingrediente", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<Ingrediente> modificarIngrediente(@PathVariable() int id,
			@RequestBody() Ingrediente ingrediente) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("modificar ingrediente " + id);

			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);

			if (this.serviceReceta.modificarIngrediente(id, ingrediente)) {

				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);

			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

	@RequestMapping(value = "{id}/ingrediente/{idIngrediente}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() ResponseEntity<Ingrediente> EliminarIngrediente(@PathVariable() int id,
			@PathVariable() int idIngrediente) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("eliminar ingrediente " + id);

			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);

			if (this.serviceReceta.eliminarIngrediente(id, idIngrediente)) {

				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);

			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

}
