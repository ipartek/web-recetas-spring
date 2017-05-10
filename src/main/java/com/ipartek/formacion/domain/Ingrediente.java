package com.ipartek.formacion.domain;

import javax.validation.constraints.Size;

public class Ingrediente {

	public static final String CANTIDAD_POR_DEFECTO = "a ojimetro...";

	private long id;

	@Size(min = 2, max = 255)
	private String nombre;

	private boolean gluten;

	private String cantidad;

	public Ingrediente() {
		super();
		this.id = -1;
		this.nombre = "";
		this.gluten = true;
		this.cantidad = CANTIDAD_POR_DEFECTO;
	}

	public Ingrediente(long id, String nombre, boolean gluten) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.gluten = gluten;
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
		if (nombre == null) {
			this.nombre = "";
		} else {
			this.nombre = nombre.trim().replaceAll(" +", " ");
		}
	}

	public boolean isGluten() {
		return gluten;
	}

	public void setGluten(boolean gluten) {
		this.gluten = gluten;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		if (cantidad == null || "".equals(cantidad)) {
			this.cantidad = CANTIDAD_POR_DEFECTO;
		} else {
			this.cantidad = cantidad.trim().replaceAll(" +", " ");
		}
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nombre=" + nombre + ", gluten=" + gluten + ", cantidad=" + cantidad + "]";
	}

}
