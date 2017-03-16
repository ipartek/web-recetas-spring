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
@RequestMapping(value = "api/receta/")
public class ApiRecetaController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Receta>> listar() {

		ResponseEntity<ArrayList<Receta>> response = null;

		try {

			LOG.info("Listar todas las recetas");

			ArrayList<Receta> recetas = (ArrayList<Receta>) this.serviceReceta.listarConUsuarios();

			if (recetas.isEmpty()) {
				response = new ResponseEntity<ArrayList<Receta>>(HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<ArrayList<Receta>>(recetas, HttpStatus.OK);
			}

		} catch (Exception e) {

			response = new ResponseEntity<ArrayList<Receta>>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Receta> detalle(@PathVariable int id) {

		ResponseEntity<Receta> response = null;

		try {

			LOG.info("Mostrar detalle de la receta " + id);

			// TODO controlar si no existe receta
			Receta receta = this.serviceReceta.buscarPorID(id);

			if (receta != null) {
				response = new ResponseEntity<Receta>(receta, HttpStatus.OK);
			} else {

				response = new ResponseEntity<Receta>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> altaReceta(@RequestBody Receta receta) {

		ResponseEntity<Receta> response = null;

		try {
			LOG.info("Dar de alta nueva receta" + receta);

			response = new ResponseEntity<Receta>(HttpStatus.ACCEPTED);

			// TODO validar datos

			// TODO llamar al servicio

			// TODO siempre usamos el mismo usuario para las recetas
			Usuario client = new Usuario();
			client.setId(4);
			receta.setUsuario(client);

			if (this.serviceReceta.crear(receta)) {
				response = new ResponseEntity<Receta>(receta, HttpStatus.CREATED);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> eliminar(@PathVariable int id) {

		ResponseEntity<Receta> response = null;

		try {
			LOG.info("Eliminar una receta" + id);

			// TODO comprobar que los parametros son validos.
			response = new ResponseEntity<Receta>(HttpStatus.NO_CONTENT);

			if (this.serviceReceta.eliminar(id)) {
				response = new ResponseEntity<Receta>(HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> modificar(@RequestBody Receta receta) {

		ResponseEntity<Receta> response = null;

		try {
			LOG.info("Modificar una Receta" + receta);

			// TODO validar datos
			response = new ResponseEntity<Receta>(HttpStatus.NO_CONTENT);

			// TODO llamar al servicio

			if (this.serviceReceta.modificar(receta)) {
				response = new ResponseEntity<Receta>(receta, HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Receta>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}
	}
}
