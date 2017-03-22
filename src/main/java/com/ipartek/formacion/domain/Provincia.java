package com.ipartek.formacion.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

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

	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute(name = "comaut")
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@XmlValue
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
