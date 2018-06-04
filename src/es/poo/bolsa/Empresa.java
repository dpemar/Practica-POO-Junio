package es.poo.bolsa;

import java.io.Serializable;

import es.poo.general.Utilidades;

public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreEmpresa;
	private float valorAccionActual;
	private float valorAccionPrevio;
	private float incremento;
	// Valor min/max acciones aleatorias
	private int minValor = 1;
	private int maxValor = 99;

	public Empresa(String nombreEmpresa, float valorAccionActual, float valorAccionPrevio) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.valorAccionActual = valorAccionActual;
		this.valorAccionPrevio = valorAccionPrevio;
	}

	public Empresa() {

	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public float getValorAccionActual() {
		return valorAccionActual;
	}

	public void setValorAccionActual(float valorAccionActual) {
		this.valorAccionActual = valorAccionActual;
	}

	public float getValorAccionPrevio() {
		return valorAccionPrevio;
	}

	public void setValorAccionPrevio(float valorAccionPrevio) {
		this.valorAccionPrevio = valorAccionPrevio;
	}

	public float getIncremento() {
		return (valorAccionActual - valorAccionPrevio);
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}

	public boolean equals(Empresa e) {
		return super.equals(e);
	}

	@Override
	public String toString() {
		return "Empresa [nombreEmpresa=" + nombreEmpresa + ", valorAccionActual=" + valorAccionActual
				+ ", valorAccionPrevio=" + valorAccionPrevio + ", incremento=" + incremento + ", minValor=" + minValor
				+ ", maxValor=" + maxValor + "]";
	}

	// Actualizar el valor de las acciones
	public void actualizarValorAcciones() {
		this.valorAccionPrevio = valorAccionActual;
		this.valorAccionActual = Math.round(Utilidades.generarNumerosAleatorios());
	}

	// Mostrar estado empresa
	public void mostrarEstadoEmpresa() {
		System.out.println("Nombre empresa: " + this.nombreEmpresa);
		System.out.println("Valor accion previo: " + this.valorAccionPrevio);
		System.out.println("Valor accion actual: " + this.valorAccionActual);
		if (this.valorAccionActual > this.valorAccionPrevio) {
			System.out.println("Incrementa: " + this.getIncremento());
		} else {
			System.out.println("Decrementa: " + this.getIncremento());
		}
		System.out.println("-----------------");
	}

}