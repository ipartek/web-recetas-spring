package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Ingrediente;
import com.ipartek.formacion.domain.Receta;

public interface DAOReceta {

	void setDatasource(DataSource ds);

	List<Receta> getAll();

	Receta getById(long id);

	boolean insert(Receta r);

	boolean update(Receta r);

	boolean delete(long id);

	boolean deleteIngrediente(long idReceta, long idIngrediente);

	boolean updateIngrediente(long idReceta, Ingrediente i);

	boolean addIngrediente(long idReceta, Ingrediente i);

}
