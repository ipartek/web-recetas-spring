package com.ipartek.formacion.domain;

public class Imagen {

	private long id;
	private String url;
	private long id_receta;
	
	public Imagen() {
		super();
		this.id = -1;
		this.url = "cerveza.jpg";
		this.id_receta = -1;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getId_receta() {
		return id_receta;
	}
	public void setId_receta(long id_receta) {
		this.id_receta = id_receta;
	}
	
}
