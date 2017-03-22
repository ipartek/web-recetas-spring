package com.ipartek.formacion.ws.client.mapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ipartek.formacion.domain.Provincia;

public class mapperProvincia {
	/**
	 * Parsea un String formato xml a un array list de provincias El parametro
	 * xmlProvincias sera el String obtenido a traves del cliente para el
	 * wsOficinaCorreos
	 * 
	 * @see https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	 * @param xmlProvincias
	 * @return ArrayList<Provincias> si falla return null
	 */
	public static ArrayList<Provincia> parse(String xmlProvincias) {

		ArrayList<Provincia> resultado = null;
		int id;
		String cod;
		String nombre;
		try {

			resultado = new ArrayList<Provincia>();

			// instanciamos Documenr builder

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Parseamos xml string en objeto Doc

			InputSource is = new InputSource(new StringReader(xmlProvincias));
			Document doc = builder.parse(is);

			// Obtenemos node list
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {

				// instanciamos el nodo actual

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;

					// Get the value of the ID attribute.
					String idString = node.getAttributes().getNamedItem("id").getNodeValue();
					id = Integer.parseInt(idString);
					// Get the value of the ID attribute.
					cod = node.getAttributes().getNamedItem("comaut").getNodeValue();

					// Get the value of all sub-elements.
					nombre = node.getTextContent();
					System.out.println(nombre);
					resultado.add(new Provincia(nombre, id, cod));
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}

}
