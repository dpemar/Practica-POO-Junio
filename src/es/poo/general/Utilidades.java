package es.poo.general;

import java.util.Calendar;
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

	public static String darFormatoFecha(Calendar fecha) {
		int year = fecha.get(Calendar.YEAR);
		int month = fecha.get(Calendar.MONTH) + 1;
		int dayOfMonth = fecha.get(Calendar.DAY_OF_MONTH);
		int hourOfDay = fecha.get(Calendar.HOUR_OF_DAY);
		int minute = fecha.get(Calendar.MINUTE);
		int second = fecha.get(Calendar.SECOND);

		return Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth)
				+ Integer.toString(hourOfDay) + Integer.toString(minute) + Integer.toString(second);
	}

}
