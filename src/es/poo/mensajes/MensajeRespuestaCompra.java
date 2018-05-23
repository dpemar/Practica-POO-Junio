package es.poo.mensajes;

public class MensajeRespuestaCompra extends MensajeCompra {

	private boolean estaRealizada;
	private int numAccionesCompradas;
	private int precioAccion;
	private int cantidadRestante;

	public MensajeRespuestaCompra(int maxInversion, boolean estaRealizada, int numAccionesCompradas, int precioAccion,
			int cantidadRestante) {
		super(maxInversion);
		this.estaRealizada = estaRealizada;
		this.numAccionesCompradas = numAccionesCompradas;
		this.precioAccion = precioAccion;
		this.cantidadRestante = cantidadRestante;
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

	public String mostrarMensajeRespuestaCompra() {
		return (this.getOperacionId() + "|"+ this.getNombreCliente() + "|" + this.getNombreEmpresa() + "|" + this.getMaxInversion() + "|" + this.isEstaRealizada() + "|" + this.getNumAccionesCompradas() + "|" + this.getPrecioAccion() + "|" + this.getCantidadRestante());
	}

}
