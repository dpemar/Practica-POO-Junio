package es.poo.laboratorio;

public class laboratorioII {

	// Realizar un programa que muestre por pantalla la siguiente pirámide

	public static void piramide() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}

	// Hacer un fragmento de código que cree un array unidimensional de 50
	// posiciones y lo inicialice con los siguientes valores, para después
	// imprimirlo: 50, 49, 48, 47, ...3, 2, 1

	public static void cuentaAtras() {
		int[] array = new int[50];
		for (int i = 0; i <= 49; i++) {
			array[i] = 50 - i;
		}
		for (int i = 0; i <= 49; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	// Realizar un programa que dado el número de créditos en primera, segunda,
	// tercera y cuarta matrícula o sucesivas, imprima el importe a pagar de las
	// tasas universitarias de la Universidad
	
	public static void precios() {
		final double PRIMERA = 26.81;
		final double SEGUNDA = 47.61;
		final double TERCERA = 89.28;
		final double CUARTA = 119.04;
		
		int uno = 24;
		int dos = 6;
		int tres = 12;
		int cuatro = 6;
		
		double precio = PRIMERA * uno + SEGUNDA * dos + TERCERA * tres + CUARTA * cuatro;
		System.out.println("El precio de la matricula es: " + precio + " €");
	}

	// Menu
	public static void main(String[] args) {
		// piramide();
//		cuentaAtras();
		precios();
	}

}
