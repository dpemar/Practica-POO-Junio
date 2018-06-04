package es.poo.banco;

import es.poo.general.Escaner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;
import es.poo.mensajes.Mensaje;
import es.poo.mensajes.MensajeCompra;
import es.poo.mensajes.MensajeRespuestaCompra;
import es.poo.mensajes.MensajeVenta;

public class AgenteDeInversiones extends Persona {

	private HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	private BolsaDeValores bolsa1 = new BolsaDeValores("bolsa1", listaEmpresas);
	private static ArrayList<Mensaje> listaPeticiones;
	private static ArrayList<Mensaje> listaPeticionesVenta;
	private static ArrayList<Mensaje> peticionesEjecutar;

	public AgenteDeInversiones(String nombre, String dni, ArrayList<Mensaje> listaPeticiones) {
		super(nombre, dni);
		this.listaPeticiones = listaPeticiones;
		this.peticionesEjecutar = new ArrayList<>();
	}

	public static ArrayList<Mensaje> getPeticionesEjecutar() {
		return peticionesEjecutar;
	}

	public static void setPeticionesEjecutar(ArrayList<Mensaje> peticionesEjecutar) {
		AgenteDeInversiones.peticionesEjecutar = peticionesEjecutar;
	}

	public ArrayList<Mensaje> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(ArrayList<Mensaje> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}

	// Anadir solicitud
	public void anadirSolicitudCompra(int operacionId, String nombreCliente, String nombreEmpresa,
			float cantidadMaxAInvertir) {
		Mensaje peticionCompra = new MensajeCompra(operacionId, nombreCliente, nombreEmpresa, cantidadMaxAInvertir);
		listaPeticiones.add(peticionCompra);
	}
	// Anadir solicitud Venta
	public void anadirSolicitudVenta(int operacionId, String nombreCliente, String nombreEmpresa,
			int numeroAcciones) {
		Mensaje peticionVenta = new MensajeCompra(operacionId, nombreCliente, nombreEmpresa, numeroAcciones);
		listaPeticionesVenta.add(peticionVenta);
	}

	// Imprimir operaciones pendientes
	public void imprimirOperacionPendientes() {
		for (Mensaje peticiones : listaPeticiones) {
			MensajeCompra mensajeCompra = (MensajeCompra) peticiones;
			System.out.println(mensajeCompra.mostrarMensajeRespuestaCompra());
		}		
		/*for (Mensaje peticiones : listaPeticionesVenta) {
			MensajeVenta mensajeVenta = (MensajeVenta) peticiones;
			System.out.println(mensajeVenta.mostrarMensajeRespuestaVenta());
		}*/
	}


	// Ejecutar operaciones pendientes
	public void ejecutarOperacionesPendientes(HashSet<Empresa> listaEmpresas) {
		String cadenaCompraRespuestaCodificada = null;
		MensajeCompra mensajeCompra = null;

		for (Mensaje peticiones : listaPeticiones) {
			mensajeCompra = (MensajeCompra) peticiones;
			String cadenaCompraCodificada;

			mensajeCompra.getOperacionId();
			mensajeCompra.getNombreCliente();
			mensajeCompra.getNombreEmpresa();
			mensajeCompra.getMaxInversion();

			cadenaCompraCodificada = mensajeCompra.mostrarMensajeRespuestaCompra();
			System.out.println("Codificacion cadena terminada");
			System.out.println(cadenaCompraCodificada);

			cadenaCompraRespuestaCodificada = bolsa1.intentaOperacion(cadenaCompraCodificada, listaEmpresas);
			System.out.println("El broker ha recibido la cadena compra codificada");

			String[] partes = cadenaCompraCodificada.split(Pattern.quote("|"));
			String operacionIdDecodificado = partes[0];
			String nombreClienteDecodificado = partes[1];
			String resultadoDecodificado = partes[2];

			Boolean esCorrecto = Boolean.parseBoolean(resultadoDecodificado);

			if (esCorrecto) {
				String numAccionesCompradas = partes[3];
				String valorAccion = partes[4];
				String dineroRestante = partes[5];
				System.out.println("Broker ha decodificado correctamente");
			}

			int operacionId = Integer.parseInt(operacionIdDecodificado);

			if (esCorrecto.equals(false)) {
				Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(operacionId, nombreClienteDecodificado,
						mensajeCompra.getNombreEmpresa(), mensajeCompra.getMaxInversion(), false);
				peticionesEjecutar.add(mensajeRespuestaCompra);
				System.out.println("Operacion compra almacenada");
			
			}
		}
	}
	// Ejecutar operaciones pendientes venta
		public void ejecutarOperacionesPendientesVenta(HashSet<Empresa> listaEmpresas) {
			String cadenaVentaRespuestaCodificada = null;

			for (Mensaje peticiones : listaPeticionesVenta) {
				MensajeVenta mensajeVenta = (MensajeVenta) peticiones;
				String cadenaVentaCodificada;

				mensajeVenta.getOperacionId();
				mensajeVenta.getNombreCliente();
				mensajeVenta.getNombreEmpresa();
				mensajeVenta.getNumAcciones();
				cadenaVentaCodificada = mensajeVenta.mostrarMensajeRespuestaVenta();
				System.out.println("Codificacion cadena terminada");
				System.out.println(cadenaVentaCodificada);

				cadenaVentaRespuestaCodificada = bolsa1.intentaOperacionVenta(cadenaVentaCodificada, listaEmpresas);
				System.out.println("El broker ha recibido la cadena venta codificada");

				// String operacionIdDecodificado = "";
				// String nombreClienteDecodificado = "";
				// String resultadoDecodificado = "";
				// String numAccionesCompradas = "";
				// String valorAccion = "";
				// String dineroRestante = "";

				String[] partes = cadenaVentaCodificada.split(Pattern.quote("|"));
				String operacionIdDecodificado = partes[0];
				String nombreClienteDecodificado = partes[1];
				String resultadoDecodificado = partes[2];

				Boolean esCorrecto = Boolean.parseBoolean(resultadoDecodificado);

				if (esCorrecto) {
					String valorAccion = partes[3];
					String dineroGanado = partes[4];
					System.out.println("Broker ha decodificado correctamente");
				}

				int operacionId = Integer.parseInt(operacionIdDecodificado);

				if (esCorrecto.equals(false)) {
					Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(operacionId, nombreClienteDecodificado,
							mensajeVenta.getNombreEmpresa(), mensajeVenta.getNumAcciones(), false);
					peticionesEjecutar.add(mensajeRespuestaCompra);
					System.out.println("Operacion compra almacenada");
				}
			}
		}
}
