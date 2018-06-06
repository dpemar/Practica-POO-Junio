package es.poo.mensajes;

public class Mensaje {

	private int operacionId;
	private String nombreCliente;
	private String nombreEmpresa;

	public Mensaje(int operacionId, String nombreCliente, String nombreEmpresa) {
		this.operacionId = operacionId;
		this.nombreCliente = nombreCliente;
		this.nombreEmpresa = nombreEmpresa;
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
	public String codificarMensaje() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() ;
		return mensaje;
	}
	public enum tipoOperacion {
		COMPRA,
		VENTA,
		ACTUALIZACION;
	}

}
