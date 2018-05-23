package es.poo.banco;

import java.util.HashSet;

public class Banco {
	private String nombreB;
	private HashSet <Cliente> bolsaClientes = new HashSet <Cliente>();
	private AgenteDeInversores broker = new AgenteDeInversores();
	public Banco() {
		super();
	}
	public Banco(String nombreB, HashSet<Cliente> bolsaClientes, AgenteDeInversores broker) {
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
	public AgenteDeInversores getBroker() {
		return broker;
	}
	public void setBroker(AgenteDeInversores broker) {
		this.broker = broker;
	}
	
	public void añadirCliente(Cliente cliente){
		this.bolsaClientes.add(cliente);
	}
	public void eliminarCliente(Cliente cliente){
		this.bolsaClientes.remove(cliente);
	}
	public void mostrarClientes(){
		System.out.println(this.bolsaClientes);
	}
	
}
