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
	private BolsaDeValores bolsa;
	private HashSet<Cliente> copiaBolsaClientes = new HashSet<Cliente>();
	private HashSet<Empresa> bolsaEmpresasAActualizar = new HashSet<Empresa>();
	private ArrayList<Mensaje> listaPeticiones = new ArrayList<Mensaje>();
	private AgenteDeInversiones broker = new AgenteDeInversiones("broker", "50505050R", listaPeticiones);
	private AgenteDeInversiones gestor;
	private int operacionId = 0;

	public Banco() {
		super();
	}

	public Banco(String nombreBanco, HashSet<Cliente> bolsaClientes, AgenteDeInversiones broker) {
		super();
		this.nombreBanco = nombreBanco;
		this.bolsaClientes = bolsaClientes;
	}

	public Banco(String nombreBanco, HashSet<Cliente> bolsaClientes, BolsaDeValores bolsa, AgenteDeInversiones broker) {
		super();
		this.nombreBanco = nombreBanco;
		this.bolsaClientes = bolsaClientes;
		this.bolsa = bolsa;
		this.gestor = new AgenteDeInversiones("gestor1", "5050505050D", listaPeticiones, bolsa);
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

	// Anadir cliente
	public void anadirCliente(Cliente cliente) {
		this.bolsaClientes.add(cliente);
	}

	// Eliminar cliente mediante cliente
	public void eliminarCliente(Cliente cliente) {
		this.bolsaClientes.remove(cliente);
	}

	// Eliminar cliente mediante nombre
	public void eliminarCliente(String cliente) {
		boolean encontrado = false;
		Cliente ClienteEncontrado = null;
		for (Cliente client : bolsaClientes) {
			if (client.getNombre().equals(cliente)) {
				ClienteEncontrado = client;
				encontrado = true;
			}
		}
		if (encontrado) {
			this.bolsaClientes.remove(ClienteEncontrado);
			System.out.println("Cliente " + ClienteEncontrado.getNombre() + " eliminado correctamente");
		} else {
			System.out.println("Cliente no encontrado");
		}
	}

	public boolean equals(String cliente, String client) {
		if (cliente.equals(client)) {
			return true;
		} else {
			return false;
		}
	}

	// Mostrar clientes
	public void mostrarClientes() {
		for (Cliente bolsa : bolsaClientes) {
			bolsa.mostrarEstadoClientes();
		}
	}

	// Hacer cliente Premium
	public void hacerClientePremium(String dniCliente) {
		Cliente clienteEncontrado = null;
		boolean encontrado = false;

		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;
				encontrado = true;
				if (clienteEncontrado.isPremium) {
					System.out.println("El cliente ya es premium");
				}
			}
		}
		if (encontrado) {
			Cliente clientePremium = new ClientePremium(clienteEncontrado, this.broker.getNombre());
			clientePremium.setPremium(true);
			bolsaClientes.remove(clienteEncontrado);
			bolsaClientes.add(clientePremium);
			System.out.println("Cliente con dni: " + dniCliente + " ahora es cliente Premium y su gestor es: "
					+ this.broker.getNombre());
		} else {
			System.out.println("Cliente con dni: " + dniCliente + " no existe");
		}
	}

	// Recomendacion de inversion
	public void recomendacionInversion(String dniCliente) {
		Cliente clienteEncontrado = null;
		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(dniCliente)) {
				clienteEncontrado = cliente;
			}
		}
		if (clienteEncontrado == null) {
			System.out.println("Cliente no encontrado");
		}
		if (!clienteEncontrado.isPremium()) {
			System.out.println("Cliente no premium");
		} else {
			String nombreGestor = gestor.consultaDeInversiones();
			if (nombreGestor != "1") {
				System.out.println("Actualizacion finalizada");
				System.out.println("Nombre de la empresa recomendada: " + gestor.consultaDeInversiones());
			}
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
		boolean resultado2;
		Integer inver;
		float inver2;
		Cliente clienteEncontrado = null;
		PaqueteDeAcciones paquete = null;
		if (broker.getListaPeticiones().size() == 0) {
			System.out.println("No hay operaciones para ejecutar");
		} else {
			cadenaFinal = broker.ejecutarOperacionesPendientes(bolsa1);

			String[] partes = cadenaFinal.split(Pattern.quote("|"));
			String operacionIdDecodificado = partes[0];
			String nombreClienteDecodificado = partes[1];
			String nombreEmpresaDecodificado = partes[2];
			String resultadoDecodificado = partes[3];
			try {
				inver2 = Float.parseFloat(resultadoDecodificado);
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
			} else if (resultado == false) {

				try {
					inver = Integer.parseInt(resultadoDecodificado);
					resultado2 = true;
				} catch (Exception e) {
					resultado2 = false;
				}

				if (resultado2 == true) {
					// COMPRA
					System.out.println("Es una compra");
					String numAccionesCompradas = partes[4];
					String valorAccion = partes[5];
					String dineroRestante = partes[6];
					System.out.println("Broker ha decodificado correctamente");

					boolean encontrado = false;
					for (Cliente cliente : bolsaClientes) {
						if (encontrado) {
							break;
						}
						if (cliente.getNombre().equals(nombreClienteDecodificado)) {
							clienteEncontrado = cliente;
							encontrado = true;
							System.out
									.println("Se ha encontrado el cliente procedemos a la actualizacion de sus datos");
						}
					}
					if (!encontrado) {
						System.out.println("No se ha encontrado el cliente");
					}

					for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
						if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
							paquete = cliente;
						}
					}

					int numAcciones = Integer.parseInt(numAccionesCompradas);
					float dineroG = Float.parseFloat(dineroRestante);
					paquete.setNumeroTitulos(paquete.getNumeroTitulos() + numAcciones);
					clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + dineroG);
					bolsaClientes.remove(clienteEncontrado);
					bolsaClientes.add(clienteEncontrado);
				} else {
					// ACTUALIZACION
					System.out.println("Es una actualizacion");
					System.out.println("Broker ha decodificado correctamente");

					boolean encontrado = false;
					for (Cliente cliente : bolsaClientes) {
						if (encontrado) {
							break;
						}
						if (cliente.getNombre().equals(nombreClienteDecodificado)) {
							clienteEncontrado = cliente;
							encontrado = true;
							System.out
									.println("Se ha encontrado el cliente procedemos a la actualizacion de sus datos");
						}
					}
					if (!encontrado) {
						System.out.println("No se ha encontrado el cliente");
					}

					for (PaqueteDeAcciones cliente : clienteEncontrado.listaPaqueteDeAcciones) {
						if (cliente.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
							paquete = cliente;
						}
					}

					paquete.setNumeroTitulos(paquete.getNumeroTitulos());
					clienteEncontrado.setSaldo(clienteEncontrado.getSaldo());
					bolsaClientes.remove(clienteEncontrado);
					bolsaClientes.add(clienteEncontrado);
				}
			}
		}

	}

	// Actualizacion de valores
	public void actualizacionDeValores(String nombreEmpresa) {
		Cliente clienteAux = new Cliente("nombre", "dni", 1);
		boolean encontrado = false;

		for (Cliente cliente : bolsaClientes) {
			if (cliente.getDni().equals(clienteAux.getDni())) {
				encontrado = true;
				clienteAux = cliente;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("Cliente con dni " + clienteAux.getDni() + " no pertenece al banco");
		}
		if (clienteAux.getPaqueteDeAcciones().size() == 0) {
			System.out.println("Cliente con dni " + clienteAux.getDni() + " no tiene paquetes de acciones");
		} else {
			for (PaqueteDeAcciones paquetes : clienteAux.getPaqueteDeAcciones()) {
				PaqueteDeAcciones paquetesAcciones = (PaqueteDeAcciones) paquetes;
				Empresa empresa = new Empresa(paquetesAcciones.getNombreEmpresa(), paquetesAcciones.getValorActual());

				System.out.println(paquetesAcciones.getNombreEmpresa());

				empresa.setNombreEmpresa(paquetesAcciones.getNombreEmpresa());
				// System.out.println(empresa.getNombreEmpresa());

				bolsaEmpresasAActualizar.add(empresa);
				// System.out.println(bolsaEmpresasAActualizar.toString());

			}
			System.out.println("Banco esta almacenando la peticion..");
			operacionId = operacionId + 1;
			System.out.println("------------------------------------");
			System.out.println("Datos peticion");
			System.out.println("OperacionId: " + operacionId);
			System.out.println("Nombre: " + clienteAux.getNombre());
			System.out.println("DNI: " + clienteAux.dni);
			System.out.println("Paquetes a actualizar: ");
			System.out.println(bolsaEmpresasAActualizar.toString());
			broker.anadirSolicitudActualizacion(operacionId, clienteAux.getNombre(), nombreEmpresa);

			System.out.println("Almacenada con exito la peticion");
		}
	}
}
