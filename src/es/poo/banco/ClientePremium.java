package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {
	GestorDeInversores gestor = new GestorDeInversores();

	public ClientePremium(String nombre, String dni, String saldo, ArrayList<PaqueteDeAcciones> paqueteDeAcciones,
			GestorDeInversores gestor) {
		super(nombre, dni, saldo, paqueteDeAcciones);
		this.gestor = gestor;
	}	
}
