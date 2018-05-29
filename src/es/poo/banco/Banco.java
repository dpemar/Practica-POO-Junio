package es.poo.banco;

import java.util.HashSet;
import java.util.Iterator;

import es.poo.bolsa.Empresa;

import java.io.*;

public class Banco {
	private String nombreB;
	public HashSet<Cliente> bolsaClientes = new HashSet<Cliente>();
	private HashSet<Cliente> copiabolsaClientes;
	public Cliente[] array = new Cliente[bolsaClientes.size()];
	private AgenteDeInversiones broker = new AgenteDeInversiones();

	public Banco() {
		super();
	}

	public Banco(String nombreB, HashSet<Cliente> bolsaClientes, AgenteDeInversiones broker) {
		super();
		this.nombreB = nombreB;
		this.bolsaClientes = bolsaClientes;
		this.broker = broker;
	}

	public String getNombreB() {
		return nombreB;
	}

	public void setNombreB(String nombreB) {
		this.nombreB = nombreB;
	}

	public HashSet<Cliente> getBolsaClientes() {
		return bolsaClientes;
	}

	public void setBolsaClientes(HashSet<Cliente> bolsaClientes) {
		this.bolsaClientes = bolsaClientes;
	}

	public AgenteDeInversiones getBroker() {
		return broker;
	}

	public void setBroker(AgenteDeInversiones broker) {
		this.broker = broker;
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
		public void restaurarCopiaSeguridadClientes(String path) {

			FileInputStream fileIn;
			ObjectInputStream objectIn;

			try {
				fileIn = new FileInputStream(path);
				objectIn = new ObjectInputStream(fileIn);
				bolsaClientes.removeAll(bolsaClientes);

				copiabolsaClientes = (HashSet<Cliente>) objectIn.readObject();

				for (Cliente cliente : bolsaClientes) {
					bolsaClientes.add(cliente);
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
	

}
