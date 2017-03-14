package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void altaReceta(@RequestBody Receta receta) {

		LOG.info("Dar de alta nueva receta: " + receta);

		// TODO validar datos

		// TODO siempre usamos el mismo usuario para las recetas
		Usuario client = new Usuario();
		client.setId(1);
		receta.setUsuario(client);

		boolean alta = this.servideReceta.crear(receta);

	}

}
