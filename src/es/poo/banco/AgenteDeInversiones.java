package es.poo.banco;

import es.poo.general.Escaner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.regex.Pattern;

import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;
import es.poo.mensajes.Mensaje;
import es.poo.mensajes.MensajeActualizacion;
import es.poo.mensajes.MensajeCompra;
import es.poo.mensajes.MensajeRespuestaCompra;
import es.poo.mensajes.MensajeVenta;

public class AgenteDeInversiones extends Persona {

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
		MensajeCompra peticionCompra = new MensajeCompra(operacionId, nombreCliente, nombreEmpresa,
				cantidadMaxAInvertir);
		listaPeticiones.add(peticionCompra);
	}

	// Anadir solicitud Venta
	public void anadirSolicitudVenta(int operacionId, String nombreCliente, String nombreEmpresa, int numeroAcciones) {
		Mensaje peticionVenta = new MensajeVenta(operacionId, nombreCliente, nombreEmpresa, numeroAcciones);
		listaPeticiones.add(peticionVenta);
	}

	// Anadir solicitud Actualizacion
	public void anadirSolicitudActualizacion(int operacionId, String nombreCliente, String nombreEmpresa, Calendar fecha) {
		Mensaje peticionActualizacion = new MensajeActualizacion(operacionId, nombreCliente, nombreEmpresa, fecha);
		listaPeticiones.add(peticionActualizacion);
	}

	// Imprimir operaciones pendientes
	public void imprimirOperacionPendientes() {
		for (Mensaje peticiones : listaPeticiones) {
			Mensaje mensaje = null;
			mensaje = peticiones;
			System.out.println(mensaje.codificarMensaje());
		}
	}

	// Ejecutar operaciones pendientes
	public String ejecutarOperacionesPendientes(BolsaDeValores bolsa1) {
		String cadenaRespuestaCodificada = null;
		Mensaje mensaje = null;
		int inver;
		boolean resultado;
		for (Mensaje peticiones : listaPeticiones) {
			mensaje = peticiones;
			String cadenaCodificada;

			cadenaCodificada = mensaje.codificarMensaje();
			System.out.println("Codificacion cadena terminada");
			System.out.println(cadenaCodificada);

			String[] par = cadenaCodificada.split(Pattern.quote("|"));
			String parte3 = par[3];
			try {
				inver = Integer.parseInt(parte3);
				resultado = true;

			} catch (NumberFormatException excepcion) {
				resultado = false;
			}

			if (resultado == false) {
				cadenaRespuestaCodificada = bolsa1.intentaOperacionCompra(cadenaCodificada);
				System.out.println("El broker ha recibido la cadena compra codificada");
				System.out.println(cadenaRespuestaCodificada);
				System.out.println();

			} else {
				cadenaRespuestaCodificada = bolsa1.intentaOperacionVenta(cadenaCodificada);
				System.out.println("El broker ha recibido la cadena Venta codificada");
				System.out.println(cadenaRespuestaCodificada);
				System.out.println();
			}
		}

		listaPeticiones.clear();

		return cadenaRespuestaCodificada;
	}

}
