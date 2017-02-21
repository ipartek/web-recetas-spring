package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Ingrediente;

public interface DAOIngrediente {

	void setDatasource(DataSource ds);

	List<Ingrediente> getAll();

	List<Ingrediente> getAllByReceta(long idReceta);

	Ingrediente getById(long id);

	boolean insert(Ingrediente i);

	boolean update(Ingrediente i);

	boolean delete(long id);

	boolean deleteByReceta(long idReceta, long idIngrediente);

}
