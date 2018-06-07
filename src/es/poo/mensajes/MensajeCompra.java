package es.poo.mensajes;

public class MensajeCompra extends Mensaje {

	private float maxInversion;

	public MensajeCompra(int operacionId, String nombreCliente, String nombreEmpresa, float maxInversion) {
		super(operacionId, nombreCliente, nombreEmpresa);
		this.maxInversion = maxInversion;
	}

	public float getMaxInversion() {
		return maxInversion;
	}

	public void setMaxInversion(float maxInversion) {
		this.maxInversion = maxInversion;
	}

	public String codificarMensaje() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ this.getMaxInversion();
		return mensaje;
	}

}