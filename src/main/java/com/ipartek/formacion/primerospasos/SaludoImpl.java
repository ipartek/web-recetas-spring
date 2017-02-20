package com.ipartek.formacion.primerospasos;

public class SaludoImpl implements Saludo {

	private String saludo;

	public SaludoImpl() {
		super();
		this.saludo = "Soy un borde y no saludo";
	}

	@Override
	public String getSaludo() {
		return this.saludo;
	}

	@Override
	public void setSaludo(String saludo) {
		this.saludo = saludo;

	}

	@Override
	public void saluda() {
		System.out.println(this.saludo);

	}

}
