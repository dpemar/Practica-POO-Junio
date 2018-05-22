package es.poo.banco;

public class PaqueteDeAcciones {
	String nombreEmpresa;
	int numeroAcciones;
	double precioTotal;
	public PaqueteDeAcciones(String nombreEmpresa, int numeroAcciones, double precioTotal) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.numeroAcciones = numeroAcciones;
		this.precioTotal = precioTotal;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getNumeroAcciones() {
		return numeroAcciones;
	}
	public void setNumeroAcciones(int numeroAcciones) {
		this.numeroAcciones = numeroAcciones;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;//*prcio de cada accion//
	}
	

}
