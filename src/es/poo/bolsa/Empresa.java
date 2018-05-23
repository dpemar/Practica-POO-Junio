package es.poo.bolsa;

import java.util.Random;

public class Empresa {

	private String nombreEmpresa;
	private int valorAccionActual;
	private int valorAccionPrevio;
	private int incremento = (valorAccionPrevio - valorAccionActual);

	// Constructor
	public Empresa(String nombreEmpresa, int valorAccionActual, int valorAccionPrevio) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.valorAccionActual = valorAccionActual;
		this.valorAccionPrevio = valorAccionPrevio;
	}

	// Get&Set
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getValorAccionActual() {
		return valorAccionActual;
	}

	public void setValorAccionActual(int valorAccionActual) {
		this.valorAccionActual = valorAccionActual;
	}

	public int getValorAccionPrevio() {
		return valorAccionPrevio;
	}

	public void setValorAccionPrevio(int valorAccionPrevio) {
		this.valorAccionPrevio = valorAccionPrevio;
	}

	public int getIncremento() {
		return incremento;
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}

	// Actualizar valor de las Acciones
	public void actualizarValorAcciones() {
		Random random = new Random();
		int valorAleatorio = random.nextInt();

		this.valorAccionPrevio = this.valorAccionActual;
		this.valorAccionActual = valorAleatorio;
	}

	// Mostrar datos empresa
	public void mostrarEstadoEmpresa() {
		System.out.println("Nombre empresa: " + this.nombreEmpresa);
		System.out.println("Valor accion previo: " + this.valorAccionPrevio);
		System.out.println("Valor accion actual: " + this.valorAccionActual);
		if (this.valorAccionActual > this.valorAccionPrevio) {
			System.out.println("↑" + this.incremento);
		} else {
			System.out.println("↓" + this.incremento);
		}
	}

}
