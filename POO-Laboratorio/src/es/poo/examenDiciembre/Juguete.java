package es.poo.examenDiciembre;

import java.util.Comparator;

public class Juguete implements Comparable<Juguete> {

	// 1
	public String nombre;
	protected int numeroJugadores;
	public int edadMinima;
	public int precioBase;

	public Juguete(String nombre, int numeroJugadores, int edadMinima, int precioBase) {
		this.nombre = nombre;
		this.numeroJugadores = numeroJugadores;
		this.edadMinima = edadMinima;
		this.precioBase = precioBase;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// 2
	public int getPrecio() {
		return precioBase;
	}

	// 3 falta clase alternativa
	public int compareTo(Juguete j) {
		if (this.precioBase < j.precioBase) {
			return -1;
		} else if (this.precioBase >= j.precioBase) {
			return 1;
		} else
			return 0;
	}

	// 4
	public boolean equals(Juguete j) {
		return nombre.equals(j.nombre);
	}


}
