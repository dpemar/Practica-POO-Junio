package es.poo.general;

import java.util.HashSet;

import es.poo.banco.AgenteDeInversores;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;

public class Simulador {
	public static void iniciar() {
		HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
		AgenteDeInversores broker = new AgenteDeInversores();
		Cliente cliente1 = new Cliente("nombre", " dni", 150, null);
		Cliente cliente2 = new Cliente("nombre2", " dn2", 150, null);
		Cliente cliente3 = new Cliente("nombre3", " dni3", 150, null);
		Banco banco = new Banco("Santander", bolsaCli, broker);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		
	
		InterfazDeUsuario.seleccion();
		
	}
}