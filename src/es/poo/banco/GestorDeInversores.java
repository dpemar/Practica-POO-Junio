package es.poo.banco;

import es.poo.bolsa.BolsaDeValores;
import es.poo.bolsa.Empresa;

public class GestorDeInversores {
	BolsaDeValores bolsa;
	Empresa empresa;
	public void recomendacion(){
		empresa.mostrarEstadoEmpresa(bolsa.buscarMejorValor());
		
	}
}
