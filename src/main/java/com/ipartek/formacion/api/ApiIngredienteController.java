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
@RequestMapping(value = "api/ingrediente/")
public class ApiIngredienteController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiIngredienteController.class);

	@Autowired
	private ServiceIngrediente serviceIngrediente;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Ingrediente>> listar(
			@RequestParam(value = "order", required = false) String order) {

		ResponseEntity<ArrayList<Ingrediente>> response = null;
		ArrayList<Ingrediente> ingredientes = null;

		try {

			LOG.info("Listar todos los ingredientes");

			if (order == null) {

				ingredientes = (ArrayList<Ingrediente>) this.serviceIngrediente.listar("ASC");

			} else if ("DESC".equals(order.toUpperCase()) || "ASC".equals(order.toUpperCase())) {

				ingredientes = (ArrayList<Ingrediente>) this.serviceIngrediente.listar(order.toUpperCase());

			} else {

				response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.BAD_REQUEST);
			}

			if (ingredientes != null) {
				if (ingredientes.isEmpty()) {
					response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.NO_CONTENT);
				} else {
					response = new ResponseEntity<ArrayList<Ingrediente>>(ingredientes, HttpStatus.OK);
				}
			}
		} catch (Exception e) {

			response = new ResponseEntity<ArrayList<Ingrediente>>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingrediente> detalle(@PathVariable int id) {

		ResponseEntity<Ingrediente> response = null;

		try {

			LOG.info("Mostrar detalle del ingrediente " + id);

			// TODO controlar si no existe ingrediente
			Ingrediente ingrediente = this.serviceIngrediente.buscarPorId(id);

			if (ingrediente != null) {
				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
			} else {

				response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> altaIngrediente(@RequestBody Ingrediente ingrediente) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("Dar de alta nuevo ingrediente" + ingrediente);

			response = new ResponseEntity<Ingrediente>(HttpStatus.ACCEPTED);

			// TODO validar datos

			// TODO llamar al servicio

			if (this.serviceIngrediente.crear(ingrediente)) {
				response = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> eliminar(@PathVariable int id) {

		ResponseEntity<Ingrediente> response = null;

		try {
			LOG.info("Eliminar un ingrediente" + id);

			// TODO comprobar que los parametros son validos.
			response = new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);

			if (this.serviceIngrediente.eliminar(id)) {
				response = new ResponseEntity<Ingrediente>(HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Ingrediente>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingrediente> modificar(@RequestBody Ingrediente ingrediente) {

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
