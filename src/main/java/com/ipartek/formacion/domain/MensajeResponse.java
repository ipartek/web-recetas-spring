package com.ipartek.formacion.domain;

import java.sql.Timestamp;

public class MensajeResponse {

	private String mensaje;
	private String codigo;
	private Timestamp tiempo = null;
	public MensajeResponse() {
		super();
		this.mensaje = "";
		this.codigo = "";
		this.tiempo = new Timestamp(System.currentTimeMillis());
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Timestamp getTiempo() {
		return tiempo;
	}
	public void setTiempo(Timestamp tiempo) {
		this.tiempo = tiempo;
	}
	@Override
	public String toString() {
		return "MensajeResponse [mensaje=" + mensaje + ", codigo=" + codigo + ", tiempo=" + tiempo + "]";
	}
	
	
}