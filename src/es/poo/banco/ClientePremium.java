package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {
	AgenteDeInversores gestor = new AgenteDeInversores();

	public ClientePremium(String nombre, String dni, double saldo, ArrayList<PaqueteDeAcciones> paqueteDeAcciones,
			 AgenteDeInversores gestor){
		super(nombre, dni, saldo, paqueteDeAcciones);
		this.gestor = gestor;
	}	
}
