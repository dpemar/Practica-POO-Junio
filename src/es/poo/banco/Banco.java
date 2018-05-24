package es.poo.banco;

import java.util.HashSet;
import java.util.Iterator;

public class Banco {
	private String nombreB;
	private HashSet<Cliente> bolsaClientes = new HashSet<Cliente>();
	private AgenteDeInversiones broker = new AgenteDeInversiones();

	public Banco() {
		super();
	}

	public Banco(String nombreB, HashSet<Cliente> bolsaClientes,AgenteDeInversiones broker) {
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
		Iterator<Cliente> iterator = bolsaClientes.iterator();
		while (iterator.hasNext()){
		Cliente item = iterator.next();
		if(item.nombre.equals(cliente)){
			bolsaClientes.remove(item);
			System.out.println("el cliente fue eliminado");
		}
	}
		/*for (Cliente cli : bolsaClientes){
			if(cli.getNombre().equals(cliente)){
				this.bolsaClientes.remove(cli);
				System.out.println("el cliente fue eliminado");
			}
		}*/
	}
	
	public boolean equals(String cliente,String client){
		if (cliente.equals(client)) { 
			return true;
		}else {
			return false;
		}
		
	}
	public void mostrarClientes() {
		for (Cliente bolsa : bolsaClientes) {
			bolsa.mostrarEstadoClientes();
		}
	}
	

}
