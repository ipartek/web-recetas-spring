package com.ipartek.formacion.ws.client.mapper;

import java.util.ArrayList;

import com.ipartek.formacion.domain.Provincia;

public interface MapperProvincia {

	/**
	 * Parsea un String con formato XML a ArrayList<Provincia>
	 * @param xmlProvincias 
	 * 				String obtemnido a traves del cliente para el WS de OfinasBuzones
	 * @return 
	 * 				ArrayList<Provincia> si falla return null
	 * 
	 */
	ArrayList<Provincia> parse ( String xmlProvincias );
	
}
