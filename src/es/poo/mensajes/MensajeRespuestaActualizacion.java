package es.poo.mensajes;

import java.util.Calendar;
import java.util.HashSet;

import es.poo.bolsa.Empresa;

public class MensajeRespuestaActualizacion extends MensajeActualizacion {

	private boolean estaRealizada;

	public MensajeRespuestaActualizacion(int operacionId, String nombreCliente, String nombreEmpresa, Calendar fecha, boolean estaRealizada) {
		super(operacionId, nombreCliente, nombreEmpresa, fecha);
		this.estaRealizada = estaRealizada;
	}

	public boolean isEstaRealizada() {
		return estaRealizada;
	}

	public void setEstaRealizada(boolean estaRealizada) {
		this.estaRealizada = estaRealizada;
	}

	public String codificarMensaje() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ this.isEstaRealizada();
		return mensaje;
	}
}
