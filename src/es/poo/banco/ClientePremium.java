package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {

	private String gestor;

	public ClientePremium(String nombre, String dni, float saldo, ArrayList<PaqueteDeAcciones> listaPaqueteDeAcciones,
			String gestor) {
		super(nombre, dni, saldo, listaPaqueteDeAcciones);
		this.gestor = gestor;
	}

	public ClientePremium(Cliente cliente, String gestor) {
        super(cliente.nombre, cliente.dni, cliente.saldo, true, cliente.getPaqueteDeAcciones());
        this.gestor = gestor;
    }
	
	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

}
