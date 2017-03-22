package com.ipartek.formacion.ws.client.mapper;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ipartek.formacion.domain.Provincia;

public class MapperProvincia {

	/**
	 * Parsea un String con formato XML a ArrayList<Provincias>
	 * 
	 * @param xmlProvincias
	 *            String obtenido a traves del cliente para el WS de
	 *            OficinasBuzones
	 * @return ArrayList<Provincias> si falla return null
	 */
	public static ArrayList<Provincia> parse(String xmlProvincias) {

		ArrayList<Provincia> provincias = null;

		if (!xmlProvincias.isEmpty()) {

			int id = 0;
			String cod = "";
			String prov = "";

			try {

				// pasar de string a XML
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(xmlProvincias));
				Document doc = db.parse(is);

				// Inicializar ArrayList<Provincia>
				provincias = new ArrayList<Provincia>();

				// sacar informacion del XML
				NodeList nodeList = doc.getDocumentElement().getChildNodes();

				for (int i = 0; i < nodeList.getLength(); i++) {

					Node node = nodeList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						// Get the value of the ID attribute.
						id = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
						cod = node.getAttributes().getNamedItem("comaut").getNodeValue();

						// Get the value of all sub-elements.
						prov = node.getTextContent();

						provincias.add(new Provincia(id, cod, prov));
					}

				}

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

		return provincias;
	}

}
