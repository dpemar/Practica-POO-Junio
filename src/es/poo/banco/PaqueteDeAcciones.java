package es.poo.banco;

import es.poo.bolsa.Empresa;

public class PaqueteDeAcciones  {

	String nombreEmpresa;
	int numeroTitulos;
	float valorActual;
	float valorTotal;

	

	public PaqueteDeAcciones(String nombreEmpresa,int numeroTitulos,float valorActual) {
		super();
		this.nombreEmpresa=nombreEmpresa;
		this.numeroTitulos = numeroTitulos;
		this.valorActual = valorActual;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public float getValorActual() {
		return valorActual;
	}


	public void setValorActual(float valorActual) {
		this.valorActual = valorActual;
	}


	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}


	public float getValorTotal() {
		float total = numeroTitulos*valorActual;
		return total;
	}

	public void setValorTotal() {
		this.valorTotal = numeroTitulos * valorActual;
	}

	public void setNumeroTitulos(int numeroTitulos) {
		this.numeroTitulos = numeroTitulos;
	}

	public int getNumeroTitulos() {
		return numeroTitulos;
	}
	
	public String mostrarAcciones() {
		return (this.getNombreEmpresa() + "|" + this.getNumeroTitulos() + "|" + this.getValorTotal());
	}


}
