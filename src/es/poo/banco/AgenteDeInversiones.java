package es.poo.banco;

import es.poo.general.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.regex.Pattern;

import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;
import es.poo.mensajes.Mensaje;
import es.poo.mensajes.MensajeActualizacion;
import es.poo.mensajes.MensajeCompra;
import es.poo.mensajes.MensajeVenta;

public class AgenteDeInversiones extends Persona {

	private BolsaDeValores bolsa;
	private static ArrayList<Mensaje> listaPeticiones;
	private static ArrayList<Mensaje> listaPeticionesVenta;
	private static ArrayList<Mensaje> peticionesEjecutar;

	public AgenteDeInversiones(String nombre, String dni, ArrayList<Mensaje> listaPeticiones) {
		super(nombre, dni);
		this.listaPeticiones = listaPeticiones;
		this.peticionesEjecutar = new ArrayList<>();
	}

	public AgenteDeInversiones(String nombre, String dni, ArrayList<Mensaje> listaPeticiones, BolsaDeValores bolsa) {
		super(nombre, dni);
		this.listaPeticiones = listaPeticiones;
		this.peticionesEjecutar = new ArrayList<>();
		this.bolsa = bolsa;
	}

	public AgenteDeInversiones(String nombre, String dni) {
		super(nombre, dni);
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
	public void anadirSolicitudActualizacion(int operacionId, String nombreCliente, String nombreEmpresa) {
		Calendar fecha = Calendar.getInstance();
		Mensaje peticionActualizacion = new MensajeActualizacion(operacionId, nombreCliente, nombreEmpresa,
				Utilidades.darFormatoFecha(fecha));
		listaPeticiones.add(peticionActualizacion);
	}

	// Imprimir operaciones pendientes
	public void imprimirOperacionPendientes() {
		if (listaPeticiones.size() == 0) {
			System.out.println("No hay operaciones pendientes");
		} else {
			for (Mensaje peticiones : listaPeticiones) {
				Mensaje mensaje = null;
				mensaje = peticiones;
				System.out.println(mensaje.codificarMensaje());
			}
		}
	}

	// Ejecutar operaciones pendientes
	public String ejecutarOperacionesPendientes(BolsaDeValores bolsa1) {
		String cadenaRespuestaCodificada = null;
		Mensaje mensaje = null;
		int inver;
		float inver2;
		boolean resultado;
		boolean resultado2;
		for (Mensaje peticiones : listaPeticiones) {
			mensaje = peticiones;
			String cadenaCodificada;

			cadenaCodificada = mensaje.codificarMensaje();
			System.out.println("Codificacion cadena terminada");
			System.out.println(cadenaCodificada);

			String[] par = cadenaCodificada.split(Pattern.quote("|"));
			String parte3 = par[3];
			try {

				inver2 = Float.parseFloat(parte3);
				resultado = true;

			} catch (NumberFormatException excepcion) {
				resultado = false;
			}

			if (resultado == true) {
				cadenaRespuestaCodificada = bolsa1.intentaOperacionVenta(cadenaCodificada);
				System.out.println("El broker ha recibido la cadena Venta codificada");
				System.out.println(cadenaRespuestaCodificada);
				System.out.println();
			} else if (resultado == false) {

				try {
					inver = Integer.parseInt(parte3);
					resultado2 = true;
				} catch (Exception e) {
					resultado2 = false;
				}
				if (resultado2 == true) {
					cadenaRespuestaCodificada = bolsa1.intentaOperacionCompra(cadenaCodificada);
					System.out.println("El broker ha recibido la cadena compra codificada");
					System.out.println(cadenaRespuestaCodificada);
					System.out.println();
				} else {
					cadenaRespuestaCodificada = bolsa1.intentaOperacionActualizacion(cadenaCodificada);
					System.out.println("El broker ha recibido la cadena de actualizacion codificada");
					System.out.println(cadenaRespuestaCodificada);
					System.out.println();
				}
			}
		}

		listaPeticiones.clear();

		return cadenaRespuestaCodificada;
	}

	// Mejor inversion
	public String consultaDeInversiones() {

		if (bolsa.getListaEmpresas().size() == 0) {
			System.out.println("No hay empresas en la bolsa");
			return "1";
		} else {
			Empresa empresa1 = null;
			double variacionMaxima = -100;
			Iterator iterador = bolsa.getListaEmpresas().iterator();
			while (iterador.hasNext()) {
				Empresa empresa = (Empresa) iterador.next();

				if (empresa.calculaVariacion() > variacionMaxima) {
					variacionMaxima = empresa.calculaVariacion();
					empresa1 = empresa;
				}
			}
			return empresa1.getNombreEmpresa();
		}
	}

}
