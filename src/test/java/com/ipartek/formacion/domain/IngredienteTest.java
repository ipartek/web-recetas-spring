package com.ipartek.formacion.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IngredienteTest {

	@Test
	public void testConstructor() {

		Ingrediente ingrediente = new Ingrediente();

		assertEquals(-1, ingrediente.getId());
		assertEquals("", ingrediente.getCantidad());
		assertEquals("", ingrediente.getNombre());
		assertTrue(ingrediente.isGluten());

		final int id = 2;
		final String nombre = "soja";
		final boolean notGluten = false;

		ingrediente = new Ingrediente(id, nombre, notGluten);

		assertEquals(id, ingrediente.getId());
		assertEquals(nombre, ingrediente.getNombre());
		assertEquals(notGluten, ingrediente.isGluten());
	}

}
