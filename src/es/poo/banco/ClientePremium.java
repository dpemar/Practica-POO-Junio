package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {

	private String gestor;

	public ClientePremium(String nombre, String dni, float saldo) {
		super(nombre, dni, saldo);
	}

	public ClientePremium(String nombre, String dni, float saldo, String gestor) {
		super(nombre, dni, saldo);
		this.gestor = gestor;
	}

	public ClientePremium(Cliente cliente, String gestor) {
		super(cliente.getNombre(), cliente.getDni(), cliente.getSaldo());
		cliente.getPaqueteDeAcciones();
		this.gestor = gestor;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

}
