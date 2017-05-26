package com.ipartek.formacion.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.Size;

public class Receta implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	@Size(min = 3, max = 255)
	private String nombre;

	@Size(min = 4, max = 255)
	private String imagen;

	@Size(min = 100, max = 65535)
	private String descripcion;

	private ArrayList<Ingrediente> ingredientes;

	private Usuario usuario;

	private int likes;

	private ArrayList<Imagen> imagenes;

	public Receta() {
		super();
		this.id = -1;
		this.nombre = "";
		this.imagen = "";
		this.descripcion = "";
		this.ingredientes = new ArrayList<Ingrediente>();
		this.usuario = null;
		this.likes = 0;
		this.imagenes = null;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getLikes() {
		return this.likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public ArrayList<Imagen> getImagenes() {
		return this.imagenes;
	}

	public void setImagenes(ArrayList<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public void addImagen(Imagen imagenes) {
		if (this.imagenes == null) {
			this.imagenes = new ArrayList<Imagen>();
		}
		if (imagenes != null) {
			this.imagenes.add(imagenes);
		}
	}

	@Override
	public String toString() {
		return "Receta [id=" + this.id + ", nombre=" + this.nombre + ", imagen=" + this.imagen + ", descripcion="
				+ this.descripcion + ", ingredientes=" + this.ingredientes + ", usuario=" + this.usuario + ", imagenes="
				+ this.imagenes + "]";
	}

}
