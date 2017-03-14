package com.ipartek.formacion.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class ApiRecetaControllerTest {

	static final String END_POINT = "http://localhost:8080/formacion/api/";

	// receta de prueba
	static Receta recetaMock = null;
	static final String NOMBRE_RECETA_MOCK = "Mojito Mock";

	static Usuario usuarioMock = null;
	static final int ID_USUARIO_MOCK = 1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		usuarioMock = new Usuario();
		usuarioMock.setId(ID_USUARIO_MOCK);

		recetaMock = new Receta();
		recetaMock.setNombre(NOMBRE_RECETA_MOCK);
		recetaMock.setUsuario(usuarioMock);

		// llamar Api para realizar insert
		RestTemplate restTemplate = new RestTemplate();
		String uri = END_POINT + "receta/";

		// TODO retorna null, acordarse de borrar a mano de la bbdd el recurso
		// creado
		URI uriLocation = restTemplate.postForLocation(uri, recetaMock);

		assertNotNull(uriLocation);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListar() {

		RestTemplate restTemplate = new RestTemplate();
		String uri = END_POINT + "receta/";

		ArrayList responseData = restTemplate.getForObject(uri, ArrayList.class);

		assertNotNull(responseData);
		assertTrue(responseData.size() >= 0);

	}

	@Ignore
	public void testDetalle() {
		/*
		 * RestTemplate restTemplate = new RestTemplate(); String uri =
		 * END_POINT + "receta/{id}/";
		 * 
		 * 
		 * restTemplate.getForObject(uri,Receta.class, );
		 */

	}

}
