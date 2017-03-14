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
@RequestMapping(value = "api/receta/")
public class ApiRecetaController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRecetaController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Receta> listar() {

		return (ArrayList<Receta>) this.serviceReceta.listarConUsuarios();

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Receta detale(@PathVariable int id) {
		// TODO controlar si no existe receta
		Receta receta = this.serviceReceta.buscarPorID(id);
		/*
		 * ResponseEntity response = null;
		 * 
		 * if (-1 == .getId()) { response = new
		 * ResponseEntity<>(HttpStatus.NO_CONTENT); } else {
		 * 
		 * }
		 */
		return receta;

	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receta> altaReceta(@RequestBody Receta receta) {

		ResponseEntity<Receta> response = null;

		try {
			LOG.info("Dar de alta nueva receta" + receta);

			response = new ResponseEntity<Receta>(HttpStatus.OK);

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
}
