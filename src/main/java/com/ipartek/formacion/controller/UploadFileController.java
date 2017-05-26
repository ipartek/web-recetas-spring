package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.formacion.domain.Imagen;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.service.ServiceReceta;

@Controller
public class UploadFileController /* implements HandlerExceptionResolver */ {

	private static final Logger LOG = LoggerFactory.getLogger(UploadFileController.class);

	private static final String MIME_TYPE_JPEG = "image/jpeg";
	static final String APP_IMAGES_RESOURCES = "uploads\\";

	String mensaje = "Error subiendo imagen";

	@Autowired
	private ServiceReceta serviceReceta;

	/**
	 * Controlador para subida Imagenes formato 'image/jpeg', tamaño maximo 1Mb
	 * 
	 * @param file
	 *            Imagen a subir
	 * @return form.jsp
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("imagen") MultipartFile file, @RequestParam("ruta") String ruta,
			@Valid Receta receta, BindingResult bindingResult, Model model) throws SizeLimitExceededException {
		try {

			if (!file.isEmpty()) {

				validateImage(file);
				saveImagen(file, model);

				// guardar en la BBDD la ruta
				Imagen i = new Imagen();
				i.setNombre(file.getOriginalFilename());
				i.setReceta(receta);

				this.serviceReceta.subirImagen(i);

			} else {
				LOG.warn("Fichero vacio");
				mensaje = "fichero vacio";
			}

		} catch (Exception e) {
			LOG.error("Excepcion: " + e);
			e.printStackTrace();
		}

		model.addAttribute("mensaje", mensaje);

		return "redirect:" + ruta;
	}

	private void validateImage(MultipartFile file) {

		final String contentType = file.getContentType();
		if (!MIME_TYPE_JPEG.equals(contentType)) {
			throw new UnsupportedOperationException(contentType + " no soportado");
		}

	}

	private void saveImagen(MultipartFile file, Model model) throws IOException {

		String rootPath = System.getProperty("catalina.home") + "\\webapps\\";
		File f = new File(rootPath + APP_IMAGES_RESOURCES + file.getOriginalFilename());
		FileUtils.writeByteArrayToFile(f, file.getBytes());
		mensaje = "imagen subida { formato: " + file.getContentType() + " , tamaño: " + file.getSize() + " Kb }";
		model.addAttribute("imagen", "\\" + APP_IMAGES_RESOURCES + file.getOriginalFilename());
		LOG.info("Fichero subido " + f.getAbsoluteFile());
	}

	/*
	 * @Override public ModelAndView resolveException(HttpServletRequest
	 * request, HttpServletResponse response, Object handler, Exception
	 * exception) { LOG.error("resolveException: " + exception.getMessage());
	 * Map<String, Object> model = new HashMap<String, Object>(); if (exception
	 * instanceof MaxUploadSizeExceededException) { model.put("mensaje",
	 * exception.getMessage()); } else { model.put("mensaje",
	 * "Unexpected error: " + exception.getMessage()); } return new
	 * ModelAndView("redirect:index", model); }
	 */

}