package com.ipartek.formacion.domain;

public class Provincia {

	private String nombre;
	private int id;
	private String codigo;

	public Provincia() {
		super();
		this.nombre = "";
		this.id = -1;
		this.codigo = "";
	}

	public Provincia(String nombre, int id, String codigo) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", id=" + id + ", codigo=" + codigo + "]";
	}

}
