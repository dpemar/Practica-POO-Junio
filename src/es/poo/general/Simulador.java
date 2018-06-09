package es.poo.general;

import java.util.ArrayList;
import java.util.HashSet;
import es.poo.banco.AgenteDeInversiones;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;
import es.poo.banco.PaqueteDeAcciones;
import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;
import es.poo.mensajes.Mensaje;

public class Simulador {

	static HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
	private static Cliente cli;
	private static Banco banco;

	// Lista de Peticiones
	public static ArrayList<Mensaje> listaPeticiones = new ArrayList<Mensaje>();
	// Lista de Acciones
	public static ArrayList<PaqueteDeAcciones> listaAcciones = new ArrayList<PaqueteDeAcciones>();
	// Lista de Empresas
	public static HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	// Bolsa de Valores
	public static BolsaDeValores bolsa1 = new BolsaDeValores("Bolsa1", listaEmpresas);
	// Agente de Inversiones
	// public static AgenteDeInversiones broker = new AgenteDeInversiones("Broker",
	// "50121232D");
	public static AgenteDeInversiones broker = new AgenteDeInversiones("Broker1", "50505050R", listaPeticiones);

	public static void iniciar() {
		// Acciones
		PaqueteDeAcciones accion1 = new PaqueteDeAcciones("Empresa1", 10000, 18);
		listaAcciones.add(accion1);

		// Clientes
		Cliente cliente1 = new Cliente("nombre", "dni", 150, listaAcciones);
		Cliente cliente2 = new Cliente("nombre2", "dn2", 150, listaAcciones);
		Cliente cliente3 = new Cliente("nombre3", "dni3", 150, listaAcciones);

		// Banco
		banco = new Banco("Santander", bolsaCli, null/* broker */);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		banco.eliminarCliente(cliente3);

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

	// 3.- Anadir cliente
	public static void clienteConDatos() {
		Escaner escaner = new Escaner();

		System.out.println("Introduzca su nombre");
		String nombre = escaner.leerString();

		System.out.println("Introduzca su dni");
		String dni = escaner.leerString();

		System.out.println("Introduzca su saldo");
		float saldo = escaner.leerFloat();

		cli = new Cliente(nombre, dni, saldo, null);
		banco.anadirCliente(cli);
	}

	// 4.- Eliminar cliente
	public static void eliminarCliNombre() {
		Escaner escaner = new Escaner();

		System.out.println("Introduzca el nombre del cliente que quieres borar");
		String nombre = escaner.leerString();

		banco.eliminarCliente(nombre);
	}

	// 5.- Realizar copia de seguridad
	public static void realizarCopiaSeguridadCliente() {
		banco.copiaSeguridadClientes("copiaSeguridadBolsaClientes");
		System.out.println("\nSerializacion realizada...Compruebe el archivo especifico");
	}

	// 6.- Restaurar copia de seguridad
	public static void restaurarCopiaSeguridadCliente() {
		System.out.println("\nDeserializando datos ...Compruebe los datos restaurados");
		banco.restaurarCopiaSeguridadClientes("copiaSeguridadBolsaClientes");
	}

	// 7. Mejorar cliente a PREMIUM
	public static void mejorarClienteAPremium() {
		Escaner escaner = new Escaner();

		System.out.println("Introduce DNI del cliente a mejorar: ");
		String dniCliente = escaner.leerString();

		banco.hacerClientePremium(dniCliente);
		System.out.println("Cliente mejorado como ClientePREMIUM");
	}

	// 8.- Solicita recomendacion de inversion

	// 9. Anadir Empresa a la Bolsa
	public static void anadirEmpresa() {
		Escaner escaner = new Escaner();

		System.out.println("Nombre empresa: ");
		String nombreEmpresa = escaner.leerString();

		System.out.println("Valor accion actual: ");
		Float valorAccionActual = escaner.leerFloat();

		System.out.println("Valor accion previo: ");
		Float valorAccionPrevio = escaner.leerFloat();

		Empresa empresaNueva = new Empresa(nombreEmpresa, valorAccionActual, valorAccionPrevio);
		bolsa1.anadirEmpresa(empresaNueva);
		System.out.println(
				"Empresa " + nombreEmpresa + " anadida correctamente a la bolsa " + bolsa1.getNombreBolsa() + "\n");
	}

	// 10. Eliminar Empresa de la Bolsa
	public static void eliminarEmpresa() {
		Escaner escaner = new Escaner();

		System.out.println("Nombre empresa: ");
		String nombreEmpresa = escaner.leerString();

		bolsa1.eliminarEmpresa(nombreEmpresa);
		System.out.println("Empresa " + nombreEmpresa + " eliminada correctamente\n");
	}

	// 11. Actualizacion de Valores
	public static void actualizarValoresAcciones() {
		bolsa1.actualizarValorAcciones();
		System.out.println("Actualizados los valores de las acciones\n");
	}

	// 12.- Realizar copia de seguridad
	public static void realizarCopiaSeguridadBolsa() {
		bolsa1.copiaSeguridadBolsa("copiaSeguridadBolsa");
		System.out.println("Serializacion realizada...Compruebe el archivo especifico\n");
	}

	// 13.- Restaurar copia de seguridad
	public static void restaurarCopiaSeguridadBolsa() {
		System.out.println("\nDeserializando datos ...Compruebe los datos restaurados\n");
		bolsa1.restaurarCopiaSeguridadBolsa("copiaSeguridadBolsa");
	}

	// 14.- Solicitar compra de acciones
	public static void solicitarCompraDeAcciones() {
		Escaner escaner = new Escaner();

		System.out.println("Dni cliente: ");
		String dniCliente = escaner.leerString();

		System.out.println("Nombre empresa: ");
		String nombreEmpresa = escaner.leerString();

		System.out.println("Cantidad maxima a invertir: ");
		float cantidadMaxAInvertir = escaner.leerFloat();
		
		banco.realizarSolicitudCompra(dniCliente, nombreEmpresa, cantidadMaxAInvertir);
	}

	// 15.- Solicitar venta de acciones
	public static void solicitarVentaDeAcciones() {
		Escaner escaner = new Escaner();

		System.out.println("Dni cliente: ");
		String dniCliente = escaner.leerString();

		System.out.println("Nombre empresa: ");
		String nombreEmpresa = escaner.leerString();

		System.out.println("Cantidad de acciones para su venta: ");
		int cantidadAcciones = escaner.leerEntero();
		
		banco.realizarSolicitudVenta(dniCliente, nombreEmpresa, cantidadAcciones);
	}

	// 16.- Solicitar actualizacion de valores
	public static void solicitarActualizacionValores() {
		Escaner escaner = new Escaner();

		System.out.println("Dni cliente: ");
		String dniCliente = escaner.leerString();

		banco.realizarSolicitudActualizacion(dniCliente);
	}

	// 17.- Imprimir operaciones pendientes
	public static void imprimirOperacionesPendientes() {
		broker.imprimirOperacionPendientes();
	}

	// 18.- Ejecutar operacion pendientes
	public static void ejecutarOperacionesPendientes() {
		banco.ejecutardorDeOperacionesPendientes(bolsa1);
	}
}