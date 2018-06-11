package es.poo.mensajes;

import java.util.Calendar;
import java.util.HashSet;

import es.poo.bolsa.Empresa;

public class MensajeActualizacion extends Mensaje {

	HashSet<Empresa> bolsaEmpresasAActualizar;
	String fecha;

	public MensajeActualizacion(int operacionId, String nombreCliente, String nombreEmpresa, String fecha) {
		super(operacionId, nombreCliente, nombreEmpresa);
		this.fecha = fecha;
		this.bolsaEmpresasAActualizar = new HashSet<Empresa>();
	}

	public HashSet<Empresa> getBolsaEmpresasAActualizar() {
		return bolsaEmpresasAActualizar;
	}

	public String getFecha() {
		return "Fecha: " + fecha;
	}

	public void setBolsaEmpresasAActualizar(HashSet<Empresa> bolsaEmpresasAActualizar) {
		this.bolsaEmpresasAActualizar = bolsaEmpresasAActualizar;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String codificarMensaje() {
		String mensaje = this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|"
				+ this.getFecha();

		return mensaje;
	}

}
