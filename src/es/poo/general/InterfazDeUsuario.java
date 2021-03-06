package es.poo.general;

public class InterfazDeUsuario {

	public static void mostrarMenu() {

		System.out.println("0.- Salir");
		System.out.println("----------------- ESTADO --------------");
		System.out.println("1.- Imprimir estado de los clientes");
		System.out.println("2.- Imprimir estado de la bolsa");
		System.out.println("----------------- BANCO ---------------");
		System.out.println("3.- Anadir cliente");
		System.out.println("4.- Eliminar cliente");
		System.out.println("5.- Realizar copia de seguridad");
		System.out.println("6.- Restaurar copia de seguridad");
		System.out.println("7.- Mejorar cliente a PREMIUM");
		System.out.println("8.- Solicita recomendacion de inversion");
		System.out.println("----------------- BOLSA ----------------");
		System.out.println("9.- Anadir empresa a la bolsa");
		System.out.println("10.- Eliminar empresa de la bolsa");
		System.out.println("11.- Actualizacion de valores");
		System.out.println("12.- Realizar copia de seguridad");
		System.out.println("13.- Restaurar copia de seguridad");
		System.out.println("--------------- OPERACIONES ------------");
		System.out.println("14.- Solicitar compra de acciones");
		System.out.println("15.- Solicitar venta de acciones");
		System.out.println("16.- Solicitar actualizacion de valores");
		System.out.println("---------------- BROKER ----------------");
		System.out.println("17.- Imprimir operaciones pendientes");
		System.out.println("18.- Ejecutar operaciones pendientes\n");
		System.out.println("19.- Mostrar menu\n");
	}

	public static void seleccion() {
		Escaner escaner = new Escaner();
		int numero;
		mostrarMenu();
		do {
			System.out.println("Introduce opcion: ");
			numero = escaner.leerEntero();

			switch (numero) {
			case 0:
				System.out.println("0.- Salir");
				System.out.println("Acaba de salir de la aplicacion correctamente");
				break;
			case 1:
				System.out.println("1.- Imprimir estado de los clientes");
				Simulador.mostrarClientes();
				break;
			case 2:
				System.out.println("2.- Imprimir estado de la bolsa\n");
				Simulador.mostrarEstadoBolsa();
				break;
			case 3:
				System.out.println("3.- Anadir cliente");
				Simulador.clienteConDatos();
				break;
			case 4:
				System.out.println("4.- Eliminar cliente");
				Simulador.eliminarCliNombre();
				break;
			case 5:
				System.out.println("5.- Realizar copia de seguridad");
				Simulador.realizarCopiaSeguridadCliente();
				break;
			case 6:
				System.out.println("6.- Restaurar copia de seguridad");
				Simulador.restaurarCopiaSeguridadCliente();
				break;
			case 7:
				System.out.println("7.- Mejorar cliente a PREMIUM");
				Simulador.mejorarClienteAPremium();
				break;
			case 8:
				System.out.println("8.- Solicita recomendacion de inversion");
				Simulador.recomendarInversion();
				break;
			case 9:
				System.out.println("9.- Anadir empresa a la bolsa");
				Simulador.anadirEmpresa();
				break;
			case 10:
				System.out.println("10.- Eliminar empresa de la bolsa");
				Simulador.eliminarEmpresa();
				break;
			case 11:
				System.out.println("11.- Actualizacion de valores");
				Simulador.actualizarValoresAcciones();
				break;
			case 12:
				System.out.println("12.- Realizar copia de seguridad");
				Simulador.realizarCopiaSeguridadBolsa();
				break;
			case 13:
				System.out.println("13.- Restaurar copia de seguridad");
				Simulador.restaurarCopiaSeguridadBolsa();
				break;
			case 14:
				System.out.println("14.- Solicitar compra de acciones");
				Simulador.solicitarCompraDeAcciones();
				break;
			case 15:
				System.out.println("15.- Solicitar venta de acciones");
				Simulador.solicitarVentaDeAcciones();
				break;
			case 16:
				System.out.println("16.- Solicitar actualizacion de valores");
				Simulador.solicitarActualizacionValores();
				break;
			case 17:
				System.out.println("17.- Imprimir operaciones pendientes");
				Simulador.imprimirOperacionesPendientes();
				break;
			case 18:
				System.out.println("18.- Ejecutar operaciones pendientes");
				Simulador.ejecutarOperacionesPendientes();
				break;
			case 19:
				mostrarMenu();
				break;
			default:
				System.out.println("Opcion introducida no valida");
				System.out.println("Introduce opcion entre 1-19\n");
				break;
			}
		} while (numero != 0);
	}
}