package com.ipartek.formacion.domain;

import java.util.ArrayList;

import javax.validation.constraints.Size;

public class Receta {

	private long id;

	@Size(min = 3, max = 255)
	private String nombre;

	@Size(min = 4, max = 255)
	private String imagen;

	@Size(min = 100, max = 65535)
	private String descripcion;

	private ArrayList<Ingrediente> ingredientes;

	private Usuario usuario;

	public Receta() {
		super();
		this.id = -1;
		this.nombre = "";
		this.imagen = "";
		this.descripcion = "";
		this.ingredientes = new ArrayList<Ingrediente>();
		this.usuario = null;
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

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", descripcion=" + descripcion
				+ ", ingredientes=" + ingredientes + ", usuario=" + usuario + "]";
	}

}
