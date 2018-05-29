package es.poo.general;

import java.util.Random;

public class Utilidades {

	// Valor min/max acciones aleatorias
	private static int minValor = 1;
	private static int maxValor = 99;

	public static float generarNumerosAleatorios() {
		Random random = new Random();
		float numeroAleatorio = minValor + random.nextFloat() * (maxValor - minValor);

		return numeroAleatorio;
	}

}
