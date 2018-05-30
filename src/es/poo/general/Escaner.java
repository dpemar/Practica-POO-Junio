package es.poo.general;

import java.util.Scanner;

public class Escaner {

	private Scanner entrada = new Scanner(System.in);

	public int leerEntero() {
		return entrada.nextInt();
	}

	public String leerString() {
		return entrada.nextLine();
	}

	public double leerReal() {
		return entrada.nextDouble();
	}
	
	public float leerFloat() {
		return entrada.nextFloat();
	}

	public char leerChar() {
		return entrada.next().charAt(0);
	}
}
