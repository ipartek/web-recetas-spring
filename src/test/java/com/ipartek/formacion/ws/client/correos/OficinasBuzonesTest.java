package com.ipartek.formacion.ws.client.correos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.ipartek.formacion.domain.Provincia;
import com.ipartek.formacion.ws.client.mapper.MapperProvincia;

public class OficinasBuzonesTest {

	@Test
	public void testConsultaProvincias() {
		
		
		OficinasBuzones cliente = new OficinasBuzones();
		OficinasBuzonesSoap clienteSoap = cliente.getOficinasBuzonesSoap();
		
		
		String xmlProvincias = clienteSoap.consultaProvincias("");
		
		assertNotNull(xmlProvincias);
		
		//convertir String => ArrayList<Provincia>
		ArrayList<Provincia> provincias = MapperProvincia.parse(xmlProvincias);
		
		//testear numero de provincias == X
		assertNotNull(provincias);
		assertEquals( 49 ,  provincias.size() );
		
		
		
	}

}
