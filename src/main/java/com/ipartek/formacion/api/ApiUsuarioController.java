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

import com.ipartek.formacion.domain.Usuario;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
@RequestMapping(value = "api/usuario/")
public class ApiUsuarioController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiUsuarioController.class);

	@Autowired
	private ServiceUsuario serviceUsuario;

	@SuppressWarnings("finally")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ArrayList<Usuario>> listar() {

		ResponseEntity<ArrayList<Usuario>> response = null;

		try {

			LOG.info("Listar todos los usuarios");

			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) this.serviceUsuario.listarRestringido();

			if (usuarios.isEmpty()) {
				response = new ResponseEntity<ArrayList<Usuario>>(HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<ArrayList<Usuario>>(usuarios, HttpStatus.OK);
			}

		} catch (Exception e) {

			response = new ResponseEntity<ArrayList<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Usuario> detalle(@PathVariable int id) {

		ResponseEntity<Usuario> response = null;

		try {

			LOG.info("Mostrar detalle del usuario " + id);

			// TODO controlar si no existe ingrediente
			Usuario usuario = this.serviceUsuario.buscarPorIDRestringido(id);

			if (usuario != null) {
				response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			} else {

				response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> altaIngrediente(@RequestBody Usuario usuario) {

		ResponseEntity<Usuario> response = null;

		try {
			LOG.info("Dar de alta nuevo usuario" + usuario);

			response = new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);

			// TODO validar datos

			// TODO llamar al servicio

			if (this.serviceUsuario.crear(usuario)) {
				response = new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> eliminar(@PathVariable int id) {

		ResponseEntity<Usuario> response = null;

		try {
			LOG.info("Eliminar un usuario" + id);

			// TODO comprobar que los parametros son validos.
			response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);

			if (this.serviceUsuario.eliminar(id)) {
				response = new ResponseEntity<Usuario>(HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {

			return response;

		}

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> modificar(@RequestBody Usuario usuario) {

		ResponseEntity<Usuario> response = null;

		try {
			LOG.info("Modificar un usuario" + usuario);

			// TODO validar datos
			response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);

			// TODO llamar al servicio

			if (this.serviceUsuario.modificar(usuario)) {
				response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			}

		} catch (Exception e) {

			LOG.error("Excepcion sin controlar", e);
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);

		} finally {
			return response;
		}

	}
}
