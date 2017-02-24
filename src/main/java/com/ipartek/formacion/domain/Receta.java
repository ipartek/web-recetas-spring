package com.ipartek.formacion.domain;

import javax.validation.constraints.Size;

public class Receta {

	private long id;

	@Size(min = 3, max = 255)
	private String nombre;
	private String imagen;
	private String descripcion;

	public Receta() {
		super();
		this.id = -1;
		this.nombre = "";
		this.imagen = "";
		this.descripcion = "";
	}

	public Receta(long id, String nombre, String imagen, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", descripcion=" + descripcion + "]";
	}

}
