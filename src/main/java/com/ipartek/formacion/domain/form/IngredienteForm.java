package com.ipartek.formacion.domain.form;

public class IngredienteForm {
	
	private long idReceta;
	
	private long idIngrediente;

	private String nombre;

	private String cantidad;
	
	private boolean gluten;
	
	public IngredienteForm() {
		super();
		this.setIdReceta(-1);
		this.setIdIngrediente(-1);
		this.setNombre("");
		this.setCantidad("");
	}

	public IngredienteForm(long id_receta, long id_ingrediente, String nombre, String cantidad) {
		super();
		this.setIdReceta(id_receta);
		this.setIdIngrediente(id_ingrediente);
		this.setNombre(nombre);
		this.setCantidad(cantidad);
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public long getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(long idReceta) {
		this.idReceta = idReceta;
	}

	public long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public boolean isGluten() {
		return gluten;
	}

	public void setGluten(boolean gluten) {
		this.gluten = gluten;
	}
	
	
	
	

}
