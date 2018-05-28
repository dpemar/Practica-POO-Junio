package es.poo.bolsa;

import java.util.Random;

public class Empresa {

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

	public void setValorAccionActual(int valorAccionActual) {
		this.valorAccionActual = valorAccionActual;
	}

	public float getValorAccionPrevio() {
		return valorAccionPrevio;
	}

	public void setValorAccionPrevio(int valorAccionPrevio) {
		this.valorAccionPrevio = valorAccionPrevio;
	}

	public float getIncremento() {
		return (valorAccionActual - valorAccionPrevio);
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}

	// Actualizar el valor de las acciones
	public void actualizarValorAcciones() {
		Random random = new Random();
		float valorAleatorioActual = minValor + random.nextFloat() * (maxValor - minValor); //quiero generar esto desde utilidades

		this.valorAccionPrevio = valorAccionActual;
		this.valorAccionActual = Math.round(valorAleatorioActual);
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