package es.poo.general;

import java.util.HashSet;
import java.util.Scanner;

import es.poo.banco.AgenteDeInversiones;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;

public class Simulador {
	static HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
	private static Cliente cli;
	private static Banco banco;

	public static void iniciar() {
		AgenteDeInversiones broker = new AgenteDeInversiones();
		Cliente cliente1 = new Cliente("nombre", " dni", 150, null);
		Cliente cliente2 = new Cliente("nombre2", " dn2", 150, null);
		Cliente cliente3 = new Cliente("nombre3", " dni3", 150, null);
		banco = new Banco("Santander", bolsaCli, broker);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		banco.eliminarCliente(cliente3);
		InterfazDeUsuario.seleccion();
	}

	public static void mostrarClientes() {
		for (Cliente bolsa : bolsaCli) {
			bolsa.mostrarEstadoClientes();
		}
	}

	public static void clienteConDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su nombre");
		String nombre = sc.nextLine();

		System.out.println("Introduzca su dni");
		String dni = sc.nextLine();

		System.out.println("Introduzca su saldo");
		double saldo = sc.nextDouble();
		cli = new Cliente(nombre, dni, saldo, null);
		banco.anadirCliente(cli);
	}

	public static void eliminarCliNombre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del cliente que quieres borar");
		String nombre = sc.nextLine();
		banco.eliminarCliente(nombre);
	}

}