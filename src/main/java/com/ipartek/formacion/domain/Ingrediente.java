package com.ipartek.formacion.domain;

import javax.validation.constraints.Size;

public class Ingrediente {

	public static final String CANTIDAD_POR_DEFECTO = "A Ojimetro";
	
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
		this.setNombre(nombre);
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
		if ( nombre == null ){
			this.nombre = "";
		}else{
			nombre = nombre.trim();
			nombre = nombre.replaceAll(" +", " ");
			this.nombre = nombre;	
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
		if ( cantidad == null ){
			this.cantidad = CANTIDAD_POR_DEFECTO;
		}else{
			if ( "".equals(cantidad.trim())){
			  this.cantidad = CANTIDAD_POR_DEFECTO;
			}else{
				this.cantidad = cantidad;
			}	
		}		
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nombre=" + nombre + ", gluten=" + gluten + ", cantidad=" + cantidad + "]";
	}
	
}
