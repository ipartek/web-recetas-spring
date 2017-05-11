package com.ipartek.formacion.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;
import com.ipartek.formacion.domain.Usuario;

public class ApiRecetaControllerTest {

	
	//TODO inyectar contesto de spring para test
	
	static final String END_POINT = "http://localhost:8080/formacion/api/";

	static ApiRecetaController apiReceta;
	
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

		apiReceta = new ApiRecetaController();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		apiReceta = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListar() {

		assertNotNull(apiReceta.listar());		

	}

	@Ignore
	public void testAddIngrediente() {
		
		
		ResponseEntity<Ingrediente> response = (ResponseEntity<Ingrediente>) apiReceta.addIngrediente(2, new Ingrediente());
		assertEquals( HttpStatus.NO_CONTENT , response.getStatusCode());
		assertNotNull( response.getBody() );
		
		
	}

}
