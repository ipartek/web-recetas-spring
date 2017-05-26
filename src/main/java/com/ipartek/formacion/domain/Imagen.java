package com.ipartek.formacion.domain;

import java.io.Serializable;

public class Imagen implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String nombre;

	private Receta receta;

	public Imagen() {
		super();
		this.id = -1;
		this.nombre = "";
		this.receta = null;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	@Override
	public String toString() {
		return "Imagen [id=" + this.id + ", nombre=" + this.nombre + ", receta=" + this.receta + "]";
	}

}