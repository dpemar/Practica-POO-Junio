package es.poo.examenDiciembre;

public class JugueteEducativo extends Juguete {

	// 1
	public int edadMaxima;
	public int bonificacionPrecio;

	public JugueteEducativo(String nombre, int numeroJugadores, int edadMinima, int precioBase, int edadMaxima,
			int bonificacionPrecio) throws EinvalidPropertyException {
		super(nombre, numeroJugadores, edadMinima, precioBase);
		if (edadMaxima <= edadMinima) {
			throw new EinvalidPropertyException("Edad maxima debe de ser mayor o igual que la edad mínima");
		}
		this.edadMaxima = edadMaxima;
		if (precioBase <= bonificacionPrecio) {
			throw new EinvalidPropertyException("Bonificaciones han de ser menores que el precio base");
		}
		this.bonificacionPrecio = bonificacionPrecio;
	}

	// 2
	public int getPrecio() {
		return precioBase + this.bonificacionPrecio;
	}

}
