package com.ipartek.formacion;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		// request.setAtribute
		model.addAttribute("usuario", "Manolo Kabezabolo");
		model.addAttribute("serverTime", formattedDate);

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
