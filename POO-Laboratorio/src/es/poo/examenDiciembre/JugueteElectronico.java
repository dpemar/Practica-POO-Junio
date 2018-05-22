package es.poo.examenDiciembre;

public class JugueteElectronico extends Juguete {

	// 1
	public int recargoPrecio;

	public JugueteElectronico(String nombre, int numeroJugadores, int edadMinima, int precioBase, int recargoPrecio)
			throws EinvalidPropertyException {
		super(nombre, numeroJugadores, edadMinima, precioBase);
		if (precioBase <= recargoPrecio) {
			throw new EinvalidPropertyException("El recargo de precio debe de ser menor que el precio base");
		} else {
			this.recargoPrecio = recargoPrecio;
		}
	}

	// 2
	public int getPrecio() {
		return precioBase + this.recargoPrecio;
	}

}
