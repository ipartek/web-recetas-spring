package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
@RequestMapping(value = "api/receta/")
public class ApiRecetaController {

	@Autowired
	private ServiceReceta serviceReceta;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Receta> listar() {

		return (ArrayList<Receta>) this.serviceReceta.listar();

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Receta detale(@PathVariable int id) {
		// TODO controlar si no existe receta
		Receta receta = this.serviceReceta.buscarPorID(id);
		ResponseEntity response = null;

		if (-1 == receta.getId()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {

		}
		return response;

	}
}
