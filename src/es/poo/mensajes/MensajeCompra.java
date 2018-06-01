package es.poo.mensajes;

public class MensajeCompra extends Mensaje {

	private int maxInversion;

	public MensajeCompra(int operacionId,String nombreCliente,String nombreEmpresa,int maxInversion) {
		super(operacionId,nombreCliente,nombreEmpresa);
		this.maxInversion = maxInversion;
	}

	public int getMaxInversion() {
		return maxInversion;
	}

	public void setMaxInversion(int maxInversion) {
		this.maxInversion = maxInversion;
	}

	public String mostrarMensajeRespuestaCompra() {
		String mensaje= this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ this.getMaxInversion();
		return mensaje;
	}

}