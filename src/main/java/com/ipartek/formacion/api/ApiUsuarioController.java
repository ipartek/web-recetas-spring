package com.ipartek.formacion.api;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Usuario> listar() {

		return (ArrayList<Usuario>) this.serviceUsuario.listar();

	}

}
