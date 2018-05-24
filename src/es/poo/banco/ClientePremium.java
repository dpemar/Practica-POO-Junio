package es.poo.banco;

import java.util.ArrayList;

public class ClientePremium extends Cliente {
	AgenteDeInversiones gestor = new AgenteDeInversiones();

	public ClientePremium(String nombre, String dni, double saldo, ArrayList<PaqueteDeAcciones> paqueteDeAcciones,
			AgenteDeInversiones gestor){
		super(nombre, dni, saldo, paqueteDeAcciones);
		this.gestor = gestor;
	}	
}
