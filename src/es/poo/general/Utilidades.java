package es.poo.general;

import java.util.Random;

public class Utilidades {

	// Valor min/max acciones aleatorias
	private int minValor = 1;
	private int maxValor = 99;

	public float generarNumerosAleatorios() {
		Random random = new Random();
		float numeroAleatorio = minValor + random.nextFloat() * (maxValor - minValor);

		return numeroAleatorio;
	}

}
