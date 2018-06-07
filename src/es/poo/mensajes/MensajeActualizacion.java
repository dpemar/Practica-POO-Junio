package es.poo.mensajes;

import java.util.Calendar;
import java.util.HashSet;

import es.poo.bolsa.Empresa;

public class MensajeActualizacion extends Mensaje {

	protected HashSet<Empresa> bolsaEmpresasAActualizar;
	protected Calendar fecha;

	public MensajeActualizacion(int operacionId, String nombreCliente, String nombreEmpresa,
			HashSet<Empresa> bolsaEmpresasAActualizar, Calendar fecha) {
		super(operacionId, nombreCliente, nombreEmpresa);
		this.bolsaEmpresasAActualizar = bolsaEmpresasAActualizar;
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
		String mensaje = this.getOperacionId() + "|" + this.getFecha() + "|" + this.getNombreCliente() + "|"
				+ this.getNombreEmpresa();
		// String mensaje = this.getOperacionId() + "|" + this.getFecha(); uno u otro,
		// por lo campos para diferenciarlos del resto
		return mensaje;
	}

}
