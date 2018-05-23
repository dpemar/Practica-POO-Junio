package es.poo.general;

import java.util.HashSet;

import es.poo.banco.AgenteDeInversores;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;

public class Simulador {
	HashSet <Cliente> bolsaCli = new HashSet <Cliente>();
	AgenteDeInversores broker = new AgenteDeInversores();
	Cliente cliente1 = new Cliente("nombre"," dni", 150, null) ;
	Banco banco1 = new Banco("Santander", bolsaCli, broker);
public static void iniciar(){
	InterfazDeUsuario.seleccion();
}
}