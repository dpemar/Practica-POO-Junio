package es.poo.mensajes;

public class MensajeRespuestaCompra extends MensajeCompra {

	private boolean estaRealizada;
	private int numAccionesCompradas;
	private int precioAccion;
	private int cantidadRestante;

	public MensajeRespuestaCompra(int operacionId, String nombreCliente, String nombreEmpresa, int cantidadMaxima,
			float maxInversion, boolean estaRealizada, int numAccionesCompradas, int precioAccion, int cantidadRestante) {
		super(operacionId, nombreCliente, nombreEmpresa, maxInversion);
		this.estaRealizada = estaRealizada;
		this.numAccionesCompradas = numAccionesCompradas;
		this.precioAccion = precioAccion;
		this.cantidadRestante = cantidadRestante;
	}

	public MensajeRespuestaCompra(int operacionId, String nombreCliente, String nombreEmpresa, float maxInversion,
			boolean estaRealizada) {
		super(operacionId, nombreCliente, nombreEmpresa, maxInversion);
		this.estaRealizada = estaRealizada;
	}

	public boolean isEstaRealizada() {
		return estaRealizada;
	}

	public void setEstaRealizada(boolean estaRealizada) {
		this.estaRealizada = estaRealizada;
	}

	public int getNumAccionesCompradas() {
		return numAccionesCompradas;
	}

	public void setNumAccionesCompradas(int numAccionesCompradas) {
		this.numAccionesCompradas = numAccionesCompradas;
	}

	public int getPrecioAccion() {
		return precioAccion;
	}

	public void setPrecioAccion(int precioAccion) {
		this.precioAccion = precioAccion;
	}

	public int getCantidadRestante() {
		return cantidadRestante;
	}

	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}

	public String codificarMensaje() {
		return (this.getOperacionId() + "|" + this.getNombreCliente() + "|" + this.isEstaRealizada() + "|"
				+ this.getNumAccionesCompradas() + "|" + this.getPrecioAccion() + "|" + this.getCantidadRestante());
	}


}