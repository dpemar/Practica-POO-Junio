package es.poo.general;

import java.util.HashSet;

import com.sun.corba.se.pept.broker.Broker;

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
	// Agente de Inversiones
	//public static AgenteDeInversiones broker = new AgenteDeInversiones("Broker", "50121232D");
	public static AgenteDeInversiones broker = new AgenteDeInversiones();
	public static void iniciar() {

		// Agentes de Inversores
		//AgenteDeInversiones broker = new AgenteDeInversiones("Broker", "50122321D");
		
		// Clientes
		Cliente cliente1 = new Cliente("nombre", "dni", 150, null);
		Cliente cliente2 = new Cliente("nombre2", "dn2", 150, null);
		Cliente cliente3 = new Cliente("nombre3", "dni3", 150, null);

		// Banco
		banco = new Banco("Santander", bolsaCli, null/*broker*/);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		banco.eliminarCliente(cliente3);
		// Banco banco2 = new Banco("Santander", bolsaCli, broker); no se utiliza!!

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

	// 7. Mejorar cliente a PREMIUM
	public static void mejorarClienteAPremium() {
		Escaner escaner = new Escaner();

		System.out.println("Introduce DNI del cliente a mejorar: ");
		String dniCliente = escaner.leerString();

		banco.hacerClientePremium(dniCliente);
		System.out.println("Cliente mejorado como ClientePREMIUM");
	}

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
		bolsa1.copiaSeguridadBolsa("copiaSeguridadBolsa.txt");
		System.out.println("Serializacion realizada...Compruebe el archivo especifico\n");
	}

	// 13.- Restaurar copia de seguridad
	public static void restaurarCopiaSeguridadBolsa() {
		System.out.println("\nDeserializando datos ...Compruebe los datos restaurados\n");
		bolsa1.restaurarCopiaSeguridadBolsa("copiaSeguridadBolsa.txt");
	}

	public static void clienteConDatos() {
		Escaner escaner = new Escaner();

		System.out.println("Introduzca su nombre");
		String nombre = escaner.leerString();

		System.out.println("Introduzca su dni");
		String dni = escaner.leerString();

		System.out.println("Introduzca su saldo");
		double saldo = escaner.leerReal();

		cli = new Cliente(nombre, dni, saldo, null);
		banco.anadirCliente(cli);
	}

	public static void eliminarCliNombre() {
		Escaner escaner = new Escaner();

		System.out.println("Introduzca el nombre del cliente que quieres borar");
		String nombre = escaner.leerString();

		banco.eliminarCliente(nombre);
	}

	public static void realizarCopiaSeguridadCliente() {
		banco.copiaSeguridadClientes("copiaSeguridadBolsaClientes.txt");
		System.out.println("\nSerializacion realizada...Compruebe el archivo especifico");
	}

	public static void restaurarCopiaSeguridadCliente() {
		System.out.println("\nDeserializando datos ...Compruebe los datos restaurados");
		banco.restaurarCopiaSeguridadClientes("copiaSeguridadBolsaClientes.txt");
	}

	// public static void solicitudRecomendacion(){
	// System.out.println("La mejor opcion para invertir es:");
	// gestor.recomendacion();
	// }
	public static void solicitarCompra(){
		System.out.println(bolsa1.descomponerMensaje());
	}
}