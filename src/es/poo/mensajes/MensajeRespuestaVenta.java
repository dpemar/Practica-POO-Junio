package es.poo.mensajes;

public class MensajeRespuestaVenta extends MensajeVenta {
	private boolean estaRealizada;
	private int precioAccion;
	private int cantidadGanada;

	public MensajeRespuestaVenta(int operacionId, String nombreCliente, String nombreEmpresa, int numAcciones,
			boolean estaRealizada, int precioAccion, int cantidadGanada) {
		super(operacionId, nombreCliente, nombreEmpresa, numAcciones);
		this.estaRealizada = estaRealizada;
		this.precioAccion = precioAccion;
		this.cantidadGanada = cantidadGanada;
	}

	public boolean isEstaRealizada() {
		return estaRealizada;
	}

	public void setEstaRealizada(boolean estaRealizada) {
		this.estaRealizada = estaRealizada;
	}

	public int getPrecioAccion() {
		return precioAccion;
	}

	public void setPrecioAccion(int precioAccion) {
		this.precioAccion = precioAccion;
	}

	public int getCantidadGanada() {
		return cantidadGanada;
	}

	public void setCantidadGanada(int cantidadGanada) {
		this.cantidadGanada = cantidadGanada;
	}

	public String codificarMensaje() {
		return (this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.isEstaRealizada() + "|"
				+ this.getPrecioAccion() + "|" + this.getCantidadGanada());
	}
}
