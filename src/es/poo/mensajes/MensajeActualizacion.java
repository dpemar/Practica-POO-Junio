package es.poo.mensajes;

import java.util.Calendar;
import java.util.HashSet;

import es.poo.bolsa.Empresa;

public class MensajeActualizacion extends Mensaje {

	private HashSet<Empresa> bolsaEmpresasAActualizar;
	private Calendar fecha;

	public MensajeActualizacion(int operacionId, String nombreCliente, String nombreEmpresa, Calendar fecha) {
		super(operacionId, nombreCliente, nombreEmpresa);
		this.fecha = fecha;
	}

	public HashSet<Empresa> getBolsaEmpresasAActualizar() {
		return bolsaEmpresasAActualizar;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setBolsaEmpresasAActualizar(HashSet<Empresa> bolsaEmpresasAActualizar) {
		this.bolsaEmpresasAActualizar = bolsaEmpresasAActualizar;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Override
	public String codificarMensaje() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ this.getFecha();

		return mensaje;
	}

}
