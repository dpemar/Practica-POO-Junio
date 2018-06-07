package es.poo.banco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;
import es.poo.mensajes.Mensaje;

import java.io.*;

public class Banco {
	private String nombreBanco;
	private HashSet<Cliente> bolsaClientes;
	private HashSet<Cliente> copiaBolsaClientes = new HashSet<Cliente>();
	private HashSet<Empresa> bolsaEmpresasAActualizar = new HashSet<Empresa>();
	private ArrayList<Mensaje> listaPeticiones = new ArrayList<Mensaje>();
	private AgenteDeInversiones broker = new AgenteDeInversiones(nombreBanco, nombreBanco, listaPeticiones);
	private GestorDeInversores gestor;
	private int operacionId = 0;

	public Banco() {
		super();
	}

	public Banco(String nombreBanco, HashSet<Cliente> bolsaClientes, AgenteDeInversiones broker) {
		super();
		this.nombreBanco = nombreBanco;
		this.bolsaClientes = bolsaClientes;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public HashSet<Cliente> getBolsaClientes() {
		return bolsaClientes;
	}

	public void setBolsaClientes(HashSet<Cliente> bolsaClientes) {
		this.bolsaClientes = bolsaClientes;
	}

	public void anadirCliente(Cliente cliente) {
		this.bolsaClientes.add(cliente);
	}

	public void eliminarCliente(Cliente cliente) {
		this.bolsaClientes.remove(cliente);
	}

	public void eliminarCliente(String cliente) {
		Cliente encontrado = null;
		for (Cliente client : bolsaClientes) {
			if (client.getNombre().equals(cliente)) {
				encontrado = client;
			}
		}
		this.bolsaClientes.remove(encontrado);
		System.out.println("el cliente fue eliminado");
	}

	public boolean equals(String cliente, String client) {
		if (cliente.equals(client)) {
			return true;
		} else {
			return false;
		}
	}

	public void mostrarClientes() {
		for (Cliente bolsa : bolsaClientes) {
			bolsa.mostrarEstadoClientes();
		}
	}

	// Hacer cliente Premium
	public void hacerClientePremium(String dniCliente) {
		Cliente clienteEncontrado = null;

		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;
			}
		}
		if (clienteEncontrado != null) {
			ClientePremium clientePremium = new ClientePremium(clienteEncontrado.getNombre(),
					clienteEncontrado.getDni(), clienteEncontrado.getSaldo(), clienteEncontrado.getPaqueteDeAcciones(),
					gestor);

			bolsaClientes.remove(clienteEncontrado);
			bolsaClientes.add(clientePremium);
		}
	}

	// Solicitud de Compra
	public void realizarSolicitudCompra(String dniCliente, String nombreEmpresa, float cantidadMaxAInvertir) {
		Cliente clienteEncontrado = null;
		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;
			}
		}

		operacionId = operacionId + 1;
		broker.anadirSolicitudCompra(operacionId, clienteEncontrado.getNombre(), nombreEmpresa, cantidadMaxAInvertir);

	}

	// Solicitud de venta
	public void realizarSolicitudVenta(String dniCliente, String nombreEmpresa, int numAcciones) {
		Cliente clienteEncontrado = null;
		PaqueteDeAcciones clienteAccionEncontrado = null;
		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;

			}
		}
		operacionId = operacionId + 1;
		broker.anadirSolicitudVenta(operacionId, clienteEncontrado.getNombre(), nombreEmpresa, numAcciones);
	}

	// Realizar solicitud actualizacion
	public void realizarSolicitudActualizacion(String dniCliente) {
		Cliente clienteEncontrado = null;
		PaqueteDeAcciones clienteAccionEncontrado = null;
		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;
			}
		}
		operacionId = operacionId + 1;
		broker.anadirSolicitudActualizacion(operacionId, clienteEncontrado.getNombre(), dniCliente,
				bolsaEmpresasAActualizar);
	}

	// Realizar copia de seguridad
	public void copiaSeguridadClientes(String path) {

		FileOutputStream fileOut;
		ObjectOutputStream objectOut;

		try {
			fileOut = new FileOutputStream(path);
			objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(bolsaClientes);

			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Restaurar copia de seguridad
	@SuppressWarnings("unchecked")
	public void restaurarCopiaSeguridadClientes(String path) {

		FileInputStream fileIn;
		ObjectInputStream objectIn;
		Cliente nuevo = null;

		try {
			fileIn = new FileInputStream(path);
			objectIn = new ObjectInputStream(fileIn);
			bolsaClientes.removeAll(bolsaClientes);

			copiaBolsaClientes = (HashSet<Cliente>) objectIn.readObject();

			for (Cliente cliente : copiaBolsaClientes) {
				nuevo = cliente;
				bolsaClientes.add(nuevo);
				mostrarClientes();
			}

			objectIn.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void ejecutardorDeOperacionesPendientes(BolsaDeValores bolsa1) {
		String cadenaFinal = null;
		boolean resultado;
		Integer inver;
		Cliente clienteEncontrado = null;
		PaqueteDeAcciones paquete = null;
		cadenaFinal = broker.ejecutarOperacionesPendientes(bolsa1);

		String[] partes = cadenaFinal.split(Pattern.quote("|"));
		String operacionIdDecodificado = partes[0];
		String nombreClienteDecodificado = partes[1];
		String nombreEmpresaDecodificado = partes[2];
		String resultadoDecodificado = partes[3];
		try {
			inver = Integer.parseInt(resultadoDecodificado);
			resultado = true;
		} catch (NumberFormatException excepcion2) {
			resultado = false;
		}
		if (resultado == true) {
			System.out.println("Es una venta");
			String valorAccion = partes[5];
			String dineroGanado = partes[6];
			System.out.println("Broker ha decodificado correctamente");
			int numAcciones = Integer.parseInt(resultadoDecodificado);
			float dineroG = Float.parseFloat(dineroGanado);
			for (Cliente cliente : bolsaClientes) {
				if (cliente.getNombre().equals(nombreClienteDecodificado)) {
					clienteEncontrado = cliente;
				}
			}
			for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
				if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
					paquete = cliente;
				}
			}
			paquete.setNumeroTitulos(paquete.getNumeroTitulos() - numAcciones);
			clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + dineroG);
			bolsaClientes.remove(clienteEncontrado);
			bolsaClientes.add(clienteEncontrado);
		} else {// compra
			System.out.println("Es una compra ");
			String numAccionesCompradas = partes[4];
			String valorAccion = partes[5];
			String dineroRestante = partes[6];
			System.out.println("Broker ha decodificado correctamente");
			for (Cliente cliente : bolsaClientes) {
				if (cliente.getNombre().equals(nombreClienteDecodificado)) {
					clienteEncontrado = cliente;
					System.out.println("se ha encontrado el cliente procedemos a la actualizacion de sus datos");
				} else {
					System.out.println("No se ha encontrado el cliente ");
				}
			}
			for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
				if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
					paquete = cliente;
				}
			}
			int numAcciones = Integer.parseInt(numAccionesCompradas);
			float dineroG = Float.parseFloat(dineroRestante);
			// paquete=clienteEncontrado.encontrar(mensajeVenta.getNombreEmpresa());
			paquete.setNumeroTitulos(paquete.getNumeroTitulos() + numAcciones);
			clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + dineroG);
			bolsaClientes.remove(clienteEncontrado);
			bolsaClientes.add(clienteEncontrado);
		}
	}

	// Actualizacion de valores
	public void actualizacionDeValores(String dniCliente) {
		Cliente clienteAux = new Cliente("nuevo", dniCliente, 1);

		if (!bolsaClientes.contains(clienteAux)) {
			System.out.println("Cliente con dni " + clienteAux.getDni() + " no pertenece al banco");
		} else {
			for (Cliente cliente : bolsaClientes) {
				if (cliente.equals(clienteAux)) {
					clienteAux = cliente;
					break;
				}
			}
		}
		if (clienteAux.getPaqueteDeAcciones().size() == 0) {
			System.out.println("Cliente con dni " + clienteAux.getDni() + " no tiene paquetes de acciones");
		} else {
			for (PaqueteDeAcciones paquetes : clienteAux.getPaqueteDeAcciones()) {
				PaqueteDeAcciones paquetesAcciones = (PaqueteDeAcciones) paquetes;
				Empresa empresa = new Empresa(paquetesAcciones.getNombreEmpresa(), paquetesAcciones.getValorActual());

				System.out.println(paquetesAcciones.getNombreEmpresa());
				empresa.setNombreEmpresa(paquetesAcciones.getNombreEmpresa());

				System.out.println(empresa.getNombreEmpresa());
				bolsaEmpresasAActualizar.add(empresa);
				System.out.println(bolsaEmpresasAActualizar.toString());

			}
			System.out.println("Banco esta almacenando la peticion..");
			operacionId = operacionId + 1;
			System.out.println("------------------------------------");
			System.out.println("Datos peticion");
			System.out.println("OperacionId: ");
			System.out.println("Nombre: ");
			System.out.println("DNI");
			System.out.println("Paquetes a actualizar: ");
			broker.anadirSolicitudActualizacion(operacionId, clienteAux.getNombre(), dniCliente,
					bolsaEmpresasAActualizar);
			System.out.println("Almacenada con exito la peticion");
		}
	}

}
