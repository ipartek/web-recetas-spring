package com.ipartek.formacion.ws.client.mapper;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.ipartek.formacion.domain.Provincia;
import com.ipartek.formacion.domain.Provincias;

public class MapperProvincia {

	/**
	 * Parsea un String con formato XML a ArrayList<Provincia>
	 * @param xmlProvincias
	 * String obtenido a traves del cliente para el WS de OficinasBuzones
	 * @return ArrayList<Provincia> si falla return null
	 */
	public static ArrayList<Provincia> parse (String xmlProvincias){
		
		ArrayList<Provincia> resul = null; 
		Provincias provin;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Provincias.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlProvincias);
		
		provin = (Provincias) unmarshaller.unmarshal( reader );
		resul = provin.getProvincias();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return resul;
	}
	
}
