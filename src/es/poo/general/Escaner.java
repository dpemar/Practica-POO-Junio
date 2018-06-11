package es.poo.general;

import java.util.Scanner;

public class Escaner {

	private Scanner entrada = new Scanner(System.in);

	public int leerEntero() {
		while (!entrada.hasNextInt()) {
			System.out.println("Introduce un número entero");
			entrada.next();
		}
		return entrada.nextInt();
	}

	public String leerString() {
		return entrada.nextLine();
	}

	public double leerReal() {
		return entrada.nextDouble();
	}

	public float leerFloat() {
		while (!entrada.hasNextFloat()) {
			System.out.println("Introduce un numero correcto");
			entrada.next();
		}
		return entrada.nextFloat();
	}

	public char leerChar() {
		return entrada.next().charAt(0);
	}
}
