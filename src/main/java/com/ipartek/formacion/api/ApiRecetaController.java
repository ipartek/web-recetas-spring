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

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "/api/receta/")
public class ApiRecetaController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);

	@Autowired
	ServiceReceta serviceReceta;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Receta> listar() {

		// TODO Fallo seguridad mostrar informacion del usuario privada
		return (ArrayList<Receta>) this.serviceReceta.listarConUsuarios();
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
	public @ResponseBody ResponseEntity<String> modificarLikes(@PathVariable int id) {

		ResponseEntity<String> response = null;

		try {
			LOG.info("Autoincrementar en 1 los likes una Receta" + id);

			response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);

			if (this.serviceReceta.modificarLikes(id)) {

				Receta r = this.serviceReceta.buscarPorID(id);

				String likes = "{\"likes\": " + r.getLikes() + "}";
				response = new ResponseEntity<String>(likes, HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return response;

	}

}
