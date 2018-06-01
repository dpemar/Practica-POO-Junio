package es.poo.mensajes;

public class Mensaje {

	private int operacionId;
	private String nombreCliente;
	private String nombreEmpresa;
	private int cantidadMaxima;

	public Mensaje(int operacionId, String nombreCliente, String nombreEmpresa, int cantidadMaxima) {
		this.operacionId = operacionId;
		this.nombreCliente = nombreCliente;
		this.nombreEmpresa = nombreEmpresa;
		this.cantidadMaxima = cantidadMaxima;
	}

	public int getOperacionId() {
		return operacionId;
	}

	public void setOperacionId(int operacionId) {
		this.operacionId = operacionId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

}
