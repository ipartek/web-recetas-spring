package com.ipartek.formacion;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.service.ServiceIngrediente;
import com.ipartek.formacion.service.ServiceReceta;
import com.ipartek.formacion.service.ServiceUsuario;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@Autowired
	private ServiceIngrediente serviceIngrediente;

	@Autowired
	private ServiceUsuario serviceUsuario;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// request.setAtribute
		ArrayList<Receta> recetas = (ArrayList<Receta>) serviceReceta.listarConUsuarios();
		model.addAttribute("recetas", recetas);

		// forward
		return "home";
	}

	@RequestMapping(value = "/canciones", method = RequestMethod.GET)
	public String listadoCanciones(Model model) {

		model.addAttribute("artista", "Manolo Kabezabolo");

		return "canciones";
	}

	@RequestMapping(value = "/saluda/{nombre}", method = RequestMethod.GET)
	public String saludar(@PathVariable String nombre, Model model) {

		model.addAttribute("usuario", nombre);

		return "home";
	}

}
