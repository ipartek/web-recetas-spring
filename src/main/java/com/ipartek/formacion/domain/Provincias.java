package com.ipartek.formacion.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultado")
public class Provincias {
	
	ArrayList<Provincia> provincias;

	@XmlElement(name="provincia")
	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
	}

	@Override
	public String toString() {
		return "Provincias [provincias=" + provincias + "]";
	}
	
}
