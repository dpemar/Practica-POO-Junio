package es.poo.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;

import es.poo.banco.AgenteDeInversiones;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;
import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;

public class Simulador {

	static HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
	private static Cliente cli;
	private static Banco banco;
	// Lista de Empresas
	public static HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	// Bolsa de Valores
	public static BolsaDeValores bolsa1 = new BolsaDeValores("Bolsa1", listaEmpresas);

	public static void iniciar() {

		// Agentes de Inversores
		AgenteDeInversiones broker = new AgenteDeInversiones();

		// Clientes
		Cliente cliente1 = new Cliente("nombre", " dni", 150, null);
		Cliente cliente2 = new Cliente("nombre2", " dn2", 150, null);
		Cliente cliente3 = new Cliente("nombre3", " dni3", 150, null);

		// Banco
		banco = new Banco("Santander", bolsaCli, broker);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		banco.eliminarCliente(cliente3);
		Banco banco2 = new Banco("Santander", bolsaCli, broker);

		// Empresa
		Empresa empresa1 = new Empresa("Empresa1", 12, 10);
		Empresa empresa2 = new Empresa("Empresa2", 10, 15);
		Empresa empresa3 = new Empresa("Empresa3", 20, 12);

		// BolsaDeValores
		bolsa1.anadirEmpresa(empresa1);
		bolsa1.anadirEmpresa(empresa2);
		bolsa1.anadirEmpresa(empresa3);

		// Interfaz de Usuario
		InterfazDeUsuario.seleccion();
	}

	// 1. Mostrar Estado de los Clientes
	public static void mostrarClientes() {
		for (Cliente bolsa : bolsaCli) {
			bolsa.mostrarEstadoClientes();
		}
	}

	// 2. Mostrar Estado de la Bolsa
	public static void mostrarEstadoBolsa() {
		for (Empresa empresa : listaEmpresas) {
			empresa.mostrarEstadoEmpresa();
		}
	}

	// 9. Anadir Empresa a la Bolsa
	public static void anadirEmpresa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre empresa: ");
		String nombreEmpresa = sc.nextLine();

		System.out.println("Valor accion actual: ");
		Float valorAccionActual = sc.nextFloat();

		System.out.println("Valor accion previo: ");
		Float valorAccionPrevio = sc.nextFloat();

		Empresa empresaNueva = new Empresa(nombreEmpresa, valorAccionActual, valorAccionPrevio);
		bolsa1.anadirEmpresa(empresaNueva);
		System.out.println("Empresa " + nombreEmpresa + " anadida correctamente a la bolsa " + bolsa1.getNombreBolsa());
	}

	// 10. Eliminar Empresa de la Bolsa
	public static void eliminarEmpresa() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Nombre empresa: ");
		String nombreEmpresa = sc.nextLine();

		bolsa1.eliminarEmpresa(nombreEmpresa);
		System.out.println("Empresa " + nombreEmpresa + " eliminada correctamente.");
	}

	// 11. Actualizacion de Valores
	public static void actualizarValoresAcciones() {
		bolsa1.actualizarValorAcciones();
	}

	// 12.- Realizar copia de seguridad
	public static void realizarCopiaSeguridadBolsa() {
		bolsa1.copiaSeguridadBolsa("copiaSeguridadBolsa.txt");
		System.out.println("\nSerializacion realizada...Compruebe el archivo especifico");
	}

	// 13.- Restaurar copia de seguridad
	public static void restaurarCopiaSeguridadBolsa() {
		bolsa1.restaurarCopiaSeguridadBolsa("copiaSeguridadBolsa.txt");
		System.out.println("\nDeserializando datos ...Compruebe los datos restaurados");
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

	public static void copiaSeguridad() {
		Cliente cliente = null;
		String string = "prueba serializable";
		try {
			FileOutputStream fs = new FileOutputStream("BolsaDeClientes.txt");// Creamos el archivo
			ObjectOutputStream os = new ObjectOutputStream(fs);// Esta clase tiene el m�todo writeObject() que
																// necesitamos
			for (Cliente cli : banco.bolsaClientes) {
				cliente = cli;
				os.writeObject(cliente);// El m�todo writeObject() serializa el objeto y lo escribe en el archivo
			}
			os.close();// Hay que cerrar siempre el archivo
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void restaurarCopia() {
		banco.bolsaClientes.toArray(banco.array);

		try {
			FileInputStream fis = new FileInputStream("BolsaDeClientes.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object aux = ois.readObject();
			while (aux != null) {
				if (aux instanceof Cliente)
					System.out.println(aux); // Se escribe en pantalla el objeto
				aux = ois.readObject();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// cli2.mostrarEstadoClientes();

	}
}