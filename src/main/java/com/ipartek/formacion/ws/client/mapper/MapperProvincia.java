package com.ipartek.formacion.ws.client.mapper;

import java.util.ArrayList;

import com.ipartek.formacion.domain.Provincia;

public interface MapperProvincia {

	/**
	 * Parsea un String con formato XML a ArrayList<Provincias>
	 * 
	 * @param xmlProvincias
	 *            String obtenido a traves del cliente para el WS de
	 *            OficinasBuzones
	 * @return ArrayList<Provincias> si falla return null
	 */
	ArrayList<Provincia> parse(String xmlProvincias);

}
