package com.ipartek.repository;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.repository.DAOIngrediente;

@ContextConfiguration(locations = "classpath:context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DAOIngredienteTest {

	@Autowired
	private DAOIngrediente daoIngrediente;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@Transactional
	@Rollback(true)
	@Test
	public void test() {

		ArrayList<Ingrediente> lista = (ArrayList<Ingrediente>) daoIngrediente.getAll("");
		assertNotNull(lista);

	}

}
