package com.ipartek.formacion.domain;

public class Provincia {

	private int id;
	private String cod;
	private String nombre;
	
	public Provincia() {
		super();
		this.id = 0;
		this.cod = "";
		this.nombre = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Provincia [id=" + id + ", cod=" + cod + ", nombre=" + nombre + "]";
	}
	
	
}
