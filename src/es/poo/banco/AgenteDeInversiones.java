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
		MensajeCompra peticionCompra = new MensajeCompra(operacionId, nombreCliente, nombreEmpresa, cantidadMaxAInvertir);
		listaPeticiones.add(peticionCompra);
	}
	// Anadir solicitud Venta
	public void anadirSolicitudVenta(int operacionId, String nombreCliente, String nombreEmpresa,
			int numeroAcciones) {
		Mensaje peticionVenta = new MensajeVenta(operacionId, nombreCliente, nombreEmpresa, numeroAcciones);
		listaPeticiones.add(peticionVenta);
	}

	// Imprimir operaciones pendientes
	public void imprimirOperacionPendientes() {
		for (Mensaje peticiones : listaPeticiones) {
			MensajeCompra mensajeCompra = null;
			MensajeVenta mensajeVenta;
			if(peticiones instanceof MensajeCompra){
				mensajeCompra = (MensajeCompra) peticiones;
				System.out.println(mensajeCompra.mostrarMensajeRespuestaCompra());
			}else{
				mensajeVenta = (MensajeVenta) peticiones;
				System.out.println(mensajeVenta.mostrarMensajeRespuestaVenta());
			}
			
		}		

	}


	// Ejecutar operaciones pendientes
	public void ejecutarOperacionesPendientes(HashSet<Cliente> listaClientes,HashSet<Empresa> listaEmpresas) {
		String cadenaCompraRespuestaCodificada = null;
		MensajeCompra mensajeCompra = null;
		MensajeVenta mensajeVenta = null;

		for (Mensaje peticiones : listaPeticiones) {
			if(peticiones instanceof MensajeCompra){
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

			String[] partes = cadenaCompraRespuestaCodificada.split(Pattern.quote("|"));
			String operacionIdDecodificado = partes[0];
			String nombreClienteDecodificado = partes[1];
			String nombreEmpresaDecodificado = partes[2];
			String resultadoDecodificado = partes[3];

			Boolean esCorrecto = Boolean.parseBoolean(resultadoDecodificado);

			//if (esCorrecto) {
				String numAccionesCompradas = partes[4];
				String valorAccion = partes[5];
				String dineroRestante = partes[6];
				System.out.println("Broker ha decodificado correctamente");
				
				System.out.println(cadenaCompraRespuestaCodificada);
				
				int numAcciones = Integer.parseInt(numAccionesCompradas);
				float dineroG= Float.parseFloat(dineroRestante);
				Cliente clienteEncontrado=null;
				PaqueteDeAcciones paquete=null;
				for (Cliente cliente : listaClientes) {
					if (cliente.getNombre().equals(nombreClienteDecodificado)) {
						clienteEncontrado = cliente;
					}
				}
				for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
					if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
						paquete = cliente;
					}
				}
				
				//paquete=clienteEncontrado.encontrar(mensajeVenta.getNombreEmpresa());
				paquete.setNumeroTitulos(paquete.getNumeroTitulos() + numAcciones);
				clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() - dineroG);
				listaClientes.remove(clienteEncontrado);
				listaClientes.add(clienteEncontrado);
			//}

			int operacionId = Integer.parseInt(operacionIdDecodificado);

			/*if (esCorrecto.equals(false)) {
				Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(operacionId, nombreClienteDecodificado,
						mensajeCompra.getNombreEmpresa(), mensajeCompra.getMaxInversion(), false);
				peticionesEjecutar.add(mensajeRespuestaCompra);
				System.out.println("Operacion compra almacenada");
			
			}*/
			}else{
				mensajeVenta = (MensajeVenta) peticiones;
				String cadenaVentaCodificada;

				mensajeVenta.getOperacionId();
				mensajeVenta.getNombreCliente();
				mensajeVenta.getNombreEmpresa();
				mensajeVenta.getNumAcciones();

				cadenaVentaCodificada = mensajeVenta.mostrarMensajeRespuestaVenta();
				System.out.println("Codificacion cadena terminada");
				System.out.println(cadenaVentaCodificada);

				cadenaCompraRespuestaCodificada = bolsa1.intentaOperacionVenta(cadenaVentaCodificada, listaEmpresas);
				System.out.println("El broker ha recibido la cadena compra codificada");

				String[] partes = cadenaCompraRespuestaCodificada.split(Pattern.quote("|"));
				String operacionIdDecodificado = partes[0];
				String nombreClienteDecodificado = partes[1];
				String nombreEmpresaDecodificado = partes[2];
				String numAccionesDecodificado=partes[3];
				String booDecodificado = partes[4];

				Boolean esCorrecto = Boolean.parseBoolean(booDecodificado);

				//if (esCorrecto) {
				
					String valorAccion = partes[5];
					String dineroGanado = partes[6];
					System.out.println("Broker ha decodificado correctamente");
					System.out.println(cadenaCompraRespuestaCodificada);
					int numAcciones = Integer.parseInt(numAccionesDecodificado);
					float dineroG= Float.parseFloat(dineroGanado);
					Cliente clienteEncontrado=null;
					PaqueteDeAcciones paquete=null;
					for (Cliente cliente : listaClientes) {
						if (cliente.getNombre().equals(nombreClienteDecodificado)) {
							clienteEncontrado = cliente;
						}
					}
					for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
						if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
							paquete = cliente;
						}
					}
					
					//paquete=clienteEncontrado.encontrar(mensajeVenta.getNombreEmpresa());
					System.out.println(paquete.mostrarAcciones());
					paquete.setNumeroTitulos(paquete.getNumeroTitulos() - numAcciones);
					clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + dineroG);
					listaClientes.remove(clienteEncontrado);
					listaClientes.add(clienteEncontrado);
					
				//}
				
				int operacionId = Integer.parseInt(operacionIdDecodificado);

				/*if (esCorrecto.equals(false)) {
					Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(operacionId, nombreClienteDecodificado,
							mensajeVenta.getNombreEmpresa(), mensajeVenta.getNumAcciones(), false);
					peticionesEjecutar.add(mensajeRespuestaCompra);
					System.out.println("Operacion compra almacenada");
				
				}*/
				
				
			}
		}
	}
	// Ejecutar operaciones pendientes venta
		public void ejecutarOperacionesPendientesVenta(HashSet<Cliente> listaClientes,HashSet<Empresa> listaEmpresas) {
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
