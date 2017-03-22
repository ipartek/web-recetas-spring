package com.ipartek.formacion.domain;

import java.util.ArrayList;

public class Usuario {

	private long id;

	private String nombre;

	private String email;

	private String password;

	private String imagen;

	private ArrayList<Receta> recetas;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.email = "";
		this.password = "";
		this.imagen = "";
		this.recetas = new ArrayList<Receta>();

	}

	public ArrayList<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(ArrayList<Receta> recetas) {
		this.recetas = recetas;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public void addReceta(Receta receta) {
		if ( this.recetas == null ){
			this.recetas = new ArrayList<Receta>();
		}
		if ( receta != null ){
			this.recetas.add(receta);
		}	
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", imagen="
				+ imagen + "]";
	}

}
