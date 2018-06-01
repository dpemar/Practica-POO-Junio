package es.poo.banco;

public class PaqueteDeAcciones {

	String nombreEmpresa;
	int numeroTitulos;
	int valorActualTitulo;
	float valorTotal;

	public PaqueteDeAcciones(String nombreEmpresa, int numeroTitulos, int valorActualTitulo, float valorTotal) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.numeroTitulos = numeroTitulos;
		this.valorActualTitulo = valorActualTitulo;
		this.valorTotal = valorTotal;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getValorActualTitulo() {
		return valorActualTitulo;
	}

	public void setValorActualTitulo(int valorActualTitulo) {
		this.valorActualTitulo = valorActualTitulo;
	}

	public float getValorTotal() {
		return (numeroTitulos * valorActualTitulo);
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setNumeroTitulos(int numeroTitulos) {
		this.numeroTitulos = numeroTitulos;
	}

	public int getNumeroTitulos() {
		return numeroTitulos;
	}

	public void setNumeroAcciones(int numeroTitulos) {
		this.numeroTitulos = numeroTitulos;
	}

}
