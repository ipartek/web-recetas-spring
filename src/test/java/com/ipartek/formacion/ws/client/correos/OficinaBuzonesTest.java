package com.ipartek.formacion.ws.client.correos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import com.ipartek.formacion.domain.Provincia;
import com.ipartek.formacion.ws.client.mapper.mapperProvincia;

public class OficinaBuzonesTest {

	@Test
	public void testConsultaProvincias() {
		// OficinasBuzones cliente = new OficinasBuzones();
		// OficinasBuzonesSoap clienteSoap = cliente.getOficinasBuzonesSoap();

		String xmlProvincias = "<?xml version=\"1.0\" encoding=\"Windows-1252\"?><resultado idioma=\"ES \"><provincia id=\"39\" comaut=\"CAB\">CANTABRIA</provincia><provincia id=\"12\" comaut=\"CVA\">CASTELLON DE LA PLANA</provincia><provincia id=\"51\" comaut=\"CEU\">CEUTA</provincia><provincia id=\"13\" comaut=\"CLA\">CIUDAD REAL</provincia><provincia id=\"14\" comaut=\"AND\">CORDOBA</provincia></resultado>";// clienteSoap.consultaProvincias("");
		// convertir string en aray list provincias
		ArrayList<Provincia> provincias = mapperProvincia.parse(xmlProvincias);
		assertNotNull(xmlProvincias);
		assertEquals(6, provincias.size());
		System.out.println();
	}

}
