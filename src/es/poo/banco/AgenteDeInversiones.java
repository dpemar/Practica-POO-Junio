package es.poo.banco;

import java.util.ArrayList;

import es.poo.bolsa.BolsaDeValores;
import es.poo.mensajes.Mensaje;

public class AgenteDeInversiones extends Persona {

	private ArrayList<Mensaje> listaPeticiones = new ArrayList<Mensaje>();

	public AgenteDeInversiones(String nombre, String dni) {
		super(nombre, dni);
	}

	public ArrayList<Mensaje> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(ArrayList<Mensaje> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}

}
