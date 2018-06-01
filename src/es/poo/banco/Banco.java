package es.poo.banco;

import java.util.HashSet;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import es.poo.bolsa.Empresa;
import sun.security.ssl.ClientKeyExchangeService;

import java.io.*;

public class Banco {
	private String nombreBanco;
	private HashSet<Cliente> bolsaClientes = new HashSet<Cliente>();
	private HashSet<Cliente> copiaBolsaClientes = new HashSet<Cliente>();
	private Cliente[] array = new Cliente[bolsaClientes.size()];
	private AgenteDeInversiones broker;
	private GestorDeInversores gestor;

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

}
