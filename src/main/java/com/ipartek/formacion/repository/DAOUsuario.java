package com.ipartek.formacion.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;

import com.ipartek.formacion.domain.Usuario;

public interface DAOUsuario {

	void setDatasource(DataSource ds);

	List<Usuario> getAll();
	
	List<Usuario> getAllWithRecetas();

	Usuario getById(long id);

	Usuario getByRecetaId(long id);

	boolean insert(Usuario u);

	boolean update(Usuario u);

	boolean delete(long id) throws DataIntegrityViolationException;

	Usuario existe(String nombre);

}
