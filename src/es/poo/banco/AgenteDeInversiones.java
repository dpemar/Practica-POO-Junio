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

	// private HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	// private BolsaDeValores bolsa1 = new BolsaDeValores("bolsa1", listaEmpresas);
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
	public void anadirSolicitudActualizacion(int operacionId, String nombreCliente, String dniCliente, HashSet<Empresa> bolsaEmpresasAActualizar) {
		Calendar fecha = Calendar.getInstance();
		Mensaje peticionActualizacion = new MensajeActualizacion(operacionId, nombreCliente, dniCliente, bolsaEmpresasAActualizar, fecha);
		listaPeticiones.add(peticionActualizacion);
	}
	
//	public void añadePeticionActualizacionALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, HashSet<Empresa> empresasQueSeQuierenActualizar) {
//        Calendar fecha = Calendar.getInstance();
//        Mensaje peticionActualizacion = new MensajeActualizacion(idOperacion, Utilidades.formatoFecha(fecha),nombreCliente,dniCliente, TipoOperacion.ACTUALIZACION, empresasQueSeQuierenActualizar);
//        operacionesPendientes.add(peticionActualizacion);
//    }

	// Imprimir operaciones pendientes
	public void imprimirOperacionPendientes() {
		for (Mensaje peticiones : listaPeticiones) {
			Mensaje mensaje = null;
			mensaje = peticiones;
			System.out.println(mensaje.codificarMensaje());
		}
	}

	// Ejecutar operaciones pendientes
	public String ejecutarOperacionesPendientes(
			BolsaDeValores bolsa1/* HashSet<Cliente> listaClientes,HashSet<Empresa> listaEmpresas */) {
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
			System.out.println(resultado);

			if (resultado == false) {
				cadenaRespuestaCodificada = bolsa1.intentaOperacion(cadenaCodificada);
				System.out.println("El broker ha recibido la cadena compra codificada");
				System.out.println(cadenaRespuestaCodificada);

			} else {
				cadenaRespuestaCodificada = bolsa1.intentaOperacionVenta(cadenaCodificada);
				System.out.println("El broker ha recibido la cadena Venta codificada");
				System.out.println(cadenaRespuestaCodificada);
			}
		}
		/*
		 * for (Mensaje peticiones : listaPeticiones) { mensaje = peticiones;
		 * listaPeticiones.remove(mensaje); }
		 */
		return cadenaRespuestaCodificada;
	}

}
