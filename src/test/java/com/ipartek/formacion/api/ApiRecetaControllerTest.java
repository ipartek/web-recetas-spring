package com.ipartek.formacion.api;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class ApiRecetaControllerTest {
	
	static final String END_POINT = "http://localhost:8080/formacion/api/";
	
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
		
		//llamar Api para realizar insert
		RestTemplate template = new RestTemplate();
		String uri = END_POINT + "receta/";
		
		URI Location = template.postForLocation(uri, recetaMock);
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
	public void test() {
		RestTemplate template = new RestTemplate();
		String uri = END_POINT + "receta/";
		ArrayList responseData = template.getForObject(uri, ArrayList.class);
		
		assertNotNull(responseData);
		assertTrue(responseData.size() >= 0);
	}

}
