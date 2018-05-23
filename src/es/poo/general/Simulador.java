package es.poo.general;

import java.util.HashSet;

import es.poo.banco.AgenteDeInversores;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;

public class Simulador {

	static HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
	static AgenteDeInversores broker = new AgenteDeInversores();
	static Cliente cliente1 = new Cliente("nombre", " dni", 150, null);
	static Banco banco = new Banco("Santander", bolsaCli, broker);

	public static void main(String[] args) {
		banco.añadirCliente(cliente1);
	}

	public void añadir(Banco b) {
		banco.añadirCliente(cliente1);
	}

	public static void iniciar() {
		InterfazDeUsuario.seleccion();
	}
}