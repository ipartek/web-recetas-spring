package com.ipartek.formacion.domain;

import javax.validation.constraints.Size;

public class Usuario {

	private long id;

	@Size(min = 3, max = 255)
	private String nombre;

	@Size(min = 3, max = 255)
	private String email;

	@Size(min = 3, max = 255)
	private String password;

	@Size(min = 3, max = 255)
	private String imagen;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.email = "";
		this.password = "";
		this.imagen = "";

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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", imagen="
				+ imagen + "]";
	}

}
