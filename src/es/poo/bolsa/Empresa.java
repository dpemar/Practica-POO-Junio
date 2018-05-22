package es.poo.bolsa;

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
	

}
