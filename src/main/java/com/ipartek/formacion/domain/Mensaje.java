package com.ipartek.formacion.domain;

/**
 * Gestion de mensajes para el usuario.<br>
 * Basado en el componente Alerts de Bootstrap3.<br>
 * 
 * @see: http://getbootstrap.com/components/#alerts
 * @author ur00
 *
 */
public class Mensaje {

	public static final String CLASE_SUCCESS = "alert-success";
	public static final String CLASE_INFO = "alert-info";
	public static final String CLASE_WARNING = "alert-warning";
	public static final String CLASE_DANGER = "alert-danger";

	private String descripcion;
	private String clase;

	public Mensaje() {
		super();
		this.clase = CLASE_DANGER;
		this.descripcion = "Error!!! Upsss algo esta fallando";
	}

	public Mensaje(String descripcion, String clase) {
		super();
		this.descripcion = descripcion;
		this.clase = clase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	@Override
	public String toString() {
		return "Mensaje [descripcion=" + descripcion + ", clase=" + clase + "]";
	}

}
