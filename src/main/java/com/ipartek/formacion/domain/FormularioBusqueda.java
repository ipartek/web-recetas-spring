package com.ipartek.formacion.domain;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class FormularioBusqueda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(min = 3, max = 20, message = "La busqueda tener contener entre {min} y {max} letras")
	private String nombre;

	private boolean ordenAscendente;

	public FormularioBusqueda() {
		super();
		this.nombre = "";
		this.ordenAscendente = true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isOrdenAscendente() {
		return ordenAscendente;
	}

	public void setOrdenAscendente(boolean ordenAscendente) {
		this.ordenAscendente = ordenAscendente;
	}

	@Override
	public String toString() {
		return "FormularioBusqueda [nombre=" + nombre + ", ordenAscendente=" + ordenAscendente + "]";
	}

}
