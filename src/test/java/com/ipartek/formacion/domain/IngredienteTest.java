package com.ipartek.formacion.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IngredienteTest {

	static final int ID = 2;
	static final String NOMBRE = "soja";
	static final boolean NOT_GLUTEN = false;

	static Ingrediente ingrediente = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ingrediente = new Ingrediente();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ingrediente = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {

		assertEquals(-1, ingrediente.getId());
		assertEquals("", ingrediente.getCantidad());
		assertEquals("", ingrediente.getNombre());
		assertTrue(ingrediente.isGluten());
		ingrediente = null;

		ingrediente = new Ingrediente(ID, NOMBRE, NOT_GLUTEN);
		assertEquals(ID, ingrediente.getId());
		assertEquals(NOMBRE, ingrediente.getNombre());
		assertEquals(NOT_GLUTEN, ingrediente.isGluten());
	}

	@Test
	public void testSetterGetter() {

		ingrediente.setId(ID);
		assertEquals(ID, ingrediente.getId());

		ingrediente.setNombre(NOMBRE);
		assertEquals(NOMBRE, ingrediente.getNombre());

	}

}
