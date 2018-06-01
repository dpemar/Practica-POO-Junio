package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {

	private GestorDeInversores gestor;

	public ClientePremium(String nombre, String dni, double saldo, ArrayList<PaqueteDeAcciones> listaPaqueteDeAcciones,
			GestorDeInversores gestor) {
		super(nombre, dni, saldo, listaPaqueteDeAcciones);
		this.gestor = gestor;
	}

	public GestorDeInversores getGestor() {
		return gestor;
	}

	public void setGestor(GestorDeInversores gestor) {
		this.gestor = gestor;
	}

}
