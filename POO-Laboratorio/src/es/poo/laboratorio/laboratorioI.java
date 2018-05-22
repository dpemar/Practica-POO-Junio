package es.poo.laboratorio;

import java.util.Random;

public class laboratorioI {

	// Escribir un programa en java que imprima números del 1 al 10 e informe de si
	// son pares o impares

	public static void ParOImpar() {
		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0) {
				System.out.println("El número " + i + " es par");
			} else {
				System.out.println("El número " + i + " es impar");
			}
		}
	}

	// Generar numeros aleatorios

	Random generadorDeNumerosAleatorios = new Random();
	int numeroAleatorioUno = generadorDeNumerosAleatorios.nextInt(100);

	private static int getRandomInt(int limite) {
		Random generadorDeNumerosAleatorios = new Random();
		int numeroAleatorio = generadorDeNumerosAleatorios.nextInt(limite);
		// System.out.println("El numero aleatorio es: " + numeroAleatorio);
		return numeroAleatorio;
	}

	// Escribir un programa que calcule la longitud de la circunferencia y el área
	// de un círculo a partir del valor del radio

	public static void CircunferenciaYArea() {
		int radio = getRandomInt(10);

		System.out.println("El radio es: " + radio);

		double longitudCircunferencia = 2 * Math.PI * radio;
		double areaDelCirculo = Math.PI * Math.pow(radio, 2);

		System.out.println("La longitud de la circunferencia es: " + longitudCircunferencia);
		System.out.println("El área del círculo es: " + areaDelCirculo);
	}

	// Escribir tres programas que calculen el factorial de un número empleando en
	// cada uno de ellos un tipo de bucle diferente: for, while y do-while.

	public static void FactorialConBucleFor() {
		int numero = getRandomInt(10);
		int factorial = 1;

		for (int i = 1; i <= numero; i++) {
			factorial = factorial * i;
		}
		System.out.println("El factorial de " + numero + " es " + factorial);
	}

	public static void FactorialConBucleWhile() {
		int numero = getRandomInt(10);
		int factorial = 1;
		int i = 1;

		while (i <= numero) {
			factorial = factorial * i;
			i = i + 1;
		}
		System.out.println("El factorial de " + numero + " es " + factorial);
	}

	public static void FactorialConBucleDoWhile() {
		int numero = getRandomInt(10);
		int factorial = 1;
		int i = 1;

		do {
			factorial = factorial * i;
			i = i + 1;
		} while (i <= numero);
		System.out.println("El factorial de " + numero + " es " + factorial);
	}

	// Calcular la media de un conjunto de valores almacenados en un array

	public static void MediaArray() {
		int[] numero = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// int[] numero = new int[10];
		int suma = 0;

		// for (int i = 0; i < numero.length; i++){
		// numero[i] = getRandomInt(100); }

		for (int i = 0; i < numero.length; i++) {
			suma = suma + numero[i];
		}

		double media = (double) suma / numero.length;
		System.out.println("La media de los valores del array es: " + media);

	}

	// Escribir un programa que genere la representación en binario de un número

	public static void ConvertidorABinario() {
		int numeroAleatorio = getRandomInt(64);
		String binario = "";

		int numero = numeroAleatorio;
		int resto;

		while (numero > 0) {
			resto = numero % 2;
			binario = binario + resto;
			numero = numero / 2;
		}
		binario = new StringBuilder(binario).reverse().toString();

		System.out.println("La representación en binario de " + numeroAleatorio + " es " + binario);
	}

	// Escribir un programa que genere la representación en hexadecimal de un
	// número

	public static void ConvertidorAHexadecimal() {
		int numeroAleatorio = getRandomInt(64);
		String hexadecimal = "";

		int numero = numeroAleatorio;
		int resto;

		while (numero > 0) {
			resto = numero % 16;
			if (resto == 10) {
				hexadecimal = hexadecimal + 'A';
			} else if (resto == 11) {
				hexadecimal = hexadecimal + 'B';
			} else if (resto == 12) {
				hexadecimal = hexadecimal + 'C';
			} else if (resto == 13) {
				hexadecimal = hexadecimal + 'D';
			} else if (resto == 14) {
				hexadecimal = hexadecimal + 'E';
			} else if (resto == 15) {
				hexadecimal = hexadecimal + 'F';
			} else {
				hexadecimal = hexadecimal + resto;
			}
			numero = numero / 16;
		}
		hexadecimal = new StringBuilder(hexadecimal).reverse().toString();

		System.out.println("La representación en hexadecimal de " + numeroAleatorio + " es " + hexadecimal);
	}

	// Escribir un programa que demuestre el uso de los operadores matemáticos
	// básicos

	public static void OperacionesMatematicas() {
		int n1 = getRandomInt(100);
		int n2 = getRandomInt(100);

		int suma = n1 + n2;
		int resta = n1 - n2;
		int multiplicacion = n1 * n2;
		int division = n1 / n2;
		int resto = n1 % n2;

		System.out.println("Suma: " + n1 + " + " + n2 + " = " + suma);
		System.out.println("Resta: " + n1 + " - " + n2 + " = " + resta);
		System.out.println("Multiplicacion: " + n1 + " x " + n2 + " = " + multiplicacion);
		System.out.println("Division: " + n1 + " / " + n2 + " = " + division);
		System.out.println("Resto: " + n1 + " % " + n2 + " = " + resto);
	}

	// Escribir un programa que imprima números del 1 al 31 e informe de a qué
	// día de la semana corresponden partiendo de que el día 1 es lunes

	public static void Calendario() {
		for (int i = 1; i <= 31; i++) {
			switch (i % 7) {
			case 1:
				System.out.println("El dia " + i + " es lunes");
				break;
			case 2:
				System.out.println("El dia " + i + " es martes");
				break;
			case 3:
				System.out.println("El dia " + i + " es miercoles");
				break;
			case 4:
				System.out.println("El dia " + i + " es jueves");
				break;
			case 5:
				System.out.println("El dia " + i + " es viernes");
				break;
			case 6:
				System.out.println("El dia " + i + " es sabado");
				break;
			case 0:
				System.out.println("El dia " + i + " es domingo");
				break;
			}
		}
	}

	// Menu
	public static void main(String[] args) {
		System.out.println("Hola mundo");
		// ParOImpar();
		// CircunferenciaYArea();
		// FactorialConBucleFor();
		// FactorialConBucleWhile();
		// FactorialConBucleDoWhile();
		// MediaArray();
		// ConvertidorABinario();
		// ConvertidorAHexadecimal();
		// OperacionesMatematicas();
		Calendario();

	}

}
