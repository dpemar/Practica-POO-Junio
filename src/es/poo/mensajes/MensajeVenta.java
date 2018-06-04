package es.poo.mensajes;

public class MensajeVenta extends Mensaje {
	int numAcciones;

	public MensajeVenta(int operacionId, String nombreCliente, String nombreEmpresa, int numAcciones) {
		super(operacionId, nombreCliente, nombreEmpresa);
		this.numAcciones = numAcciones;
	}
	
	public int getNumAcciones() {
		return numAcciones;
	}
	public void setNumAcciones(int numAcciones) {
		this.numAcciones = numAcciones;
	}

	public String mostrarMensajeRespuestaVenta() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ getNumAcciones();
		return mensaje;
	}

	

}
