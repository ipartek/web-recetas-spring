package com.ipartek.formacion.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.service.ServiceIngrediente;
import com.ipartek.formacion.service.ServiceReceta;
import com.ipartek.formacion.service.ServiceUsuario;

@Controller
@RequestMapping(value = "/receta")
public class RecetaController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);

	@Autowired
	private ServiceReceta serviceReceta;

	@Autowired
	private ServiceIngrediente serviceIngrediente;

	@Autowired
	private ServiceUsuario serviceUsuario;

	/**
	 * Listado de las ultimas 500 recetas
	 * 
	 * @param model
	 *            "recetas" ArrayList<Receta>
	 * @return vista "receta/index.jsp"
	 */
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String listar(Model model) {

		ArrayList<Receta> recetas = (ArrayList<Receta>) serviceReceta.listarConUsuarios();
		model.addAttribute("recetas", recetas);

		return "receta/index";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String irFormularioNuevo(Model model) {

		model.addAttribute("receta", new Receta());
		model.addAttribute("usuarios", serviceUsuario.listar());

		return "receta/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String irFormularioEditar(@PathVariable int id, Model model) {
		model.addAttribute("imagenes", serviceReceta.getAllImg(id));
		model.addAttribute("receta", serviceReceta.buscarPorID(id));
		model.addAttribute("usuarios", serviceUsuario.listar());
		return "receta/form";
	}
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String irFormularioEditarShow(@PathVariable int id, Model model) {
		model.addAttribute("imagenes", serviceReceta.getAllImg(id));
		model.addAttribute("receta", serviceReceta.buscarPorID(id));
		model.addAttribute("usuarios", serviceUsuario.listar());
		model.addAttribute("ingredientes", serviceReceta.listaringredientesdereceta(id));
		return "receta/show";
	}
	
	@RequestMapping(value = "/{idReceta}/eliminarImagen/{idImagen}", method = RequestMethod.GET)
	public String eliminarImagen(@PathVariable int idReceta ,@PathVariable int idImagen, Model model) {
		serviceReceta.deleteImagen(idImagen);
		return "redirect:/receta/edit/"+idReceta+"/";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String crear(@Valid Receta receta, BindingResult bindingResult, Model model) {

		logger.info("recibimos datos del formulario " + receta);
		String msg = null;

		// validar datos del formulario
		if (!bindingResult.hasErrors()) {

			if (receta.getId() == -1) {

				serviceReceta.crear(receta);
				msg = "Creada nueva Receta";

			} else {

				serviceReceta.modificar(receta);
				msg = "Modificada Receta";

			}

		} else {

			logger.info("formulario con datos no validos");

		}

		model.addAttribute("receta", serviceReceta.buscarPorID(receta.getId()));
		model.addAttribute("usuarios", serviceUsuario.listar());
		model.addAttribute("msg", msg);

		return "receta/form";
	}

	/**
	 * Elimina receta
	 * 
	 * @param idReceta
	 *            identificador de la receta
	 * @param model
	 *            <ol>
	 *            <li>msg: Mensaje para el usuario</li>
	 *            </ol>
	 * @return Si se elimina receta llamamos a la accion "listar".<br>
	 *         Si no se puede eliminar llamamos a la accion
	 *         "irFormularioEditar".
	 * 
	 * 
	 */
	@RequestMapping(value = "/delete/{idReceta}", method = RequestMethod.GET)
	public String eliminar(@PathVariable int idReceta, Model model) {

		logger.info("eliminar ingrediente " + idReceta);
		String view = "redirect: ../";
		String msg = "Receta no eliminado, posiblemente exista en otro sitio";

		if (serviceReceta.eliminar(idReceta)) {
			msg = "Receta Eliminada con exito";

		} else {

			view = "redirect: ../edit/" + idReceta;
		}
		model.addAttribute("msg", msg);
		return view;
	}

	@RequestMapping(value = "/{idReceta}/delete/ingrediente/{idIngrediente}", method = RequestMethod.GET)
	public String eliminarIngrediente(@PathVariable int idReceta, @PathVariable int idIngrediente, Model model) {

		logger.info("eliminar ingrediente " + idIngrediente + " de Receta " + idReceta);
		String msg = "No se pudo eliminar ingrediente";

		if (serviceReceta.eliminarIngrediente(idReceta, idIngrediente)) {
			msg = "Ingrediente eliminado con exito";
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("msg", msg);
		return "receta/form";
	}

	@RequestMapping(value = "/{idReceta}/edit/ingrediente/{idIngrediente}", method = RequestMethod.GET)
	public String irIngredienteModificar(@PathVariable int idReceta, @PathVariable int idIngrediente, Model model) {

		logger.info("Ir a Mostrar formulario " + idIngrediente + " de Receta " + idReceta);
		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));
		model.addAttribute("ingrediente", serviceReceta.recuperarIngrediente(idReceta, idIngrediente));
		return "receta/formIngrediente";
	}

	@RequestMapping(value = "/{idReceta}/edit/ingrediente", method = RequestMethod.POST)
	public String editarIngrediente(@PathVariable int idReceta, @Valid Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {

		logger.info("Modificando ingrediente " + ingrediente + " de Receta " + idReceta);
		String msg = "No se pudo cambiar ingrediente";

		if (serviceReceta.modificarIngrediente(idReceta, ingrediente)) {
			msg = "Ingrediente cambiado";
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));

		model.addAttribute("msg", msg);

		return "receta/formIngrediente";
	}

	@RequestMapping(value = "/{idReceta}/nuevo/ingrediente", method = RequestMethod.POST)
	public String nuevoIngrediente(@PathVariable int idReceta, @Valid Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {

		logger.info("Modificando ingrediente " + ingrediente + " de Receta " + idReceta);
		String msg = "No se pudo cambiar ingrediente";

		if (serviceReceta.addIngrediente(idReceta, ingrediente)) {
			msg = "Ingrediente añadido";
		}

		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));

		model.addAttribute("msg", msg);

		return "receta/form";
	}

	@RequestMapping(value = "/{idReceta}/add/ingrediente/", method = RequestMethod.GET)
	public String addIngrediente(@PathVariable int idReceta, @Valid Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {

		logger.info("Añadiendoo ingrediente " + ingrediente + " a Receta " + idReceta);

		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("disponibles", serviceReceta.listarIngredientesFueraReceta(idReceta));
		model.addAttribute("receta", serviceReceta.buscarPorID(idReceta));

		return "receta/formIngrediente";
	}

}
