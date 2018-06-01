package es.poo.banco;

import es.poo.general.Escaner;

//import java.util.ArrayList;

//import es.poo.bolsa.BolsaDeValores;
//import es.poo.mensajes.Mensaje;
import es.poo.mensajes.MensajeCompra;

public class AgenteDeInversiones{

	/*private ArrayList<Mensaje> listaPeticiones = new ArrayList<Mensaje>();

	public AgenteDeInversiones(String nombre, String dni) {
		super(nombre, dni);
	}

	public ArrayList<Mensaje> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(ArrayList<Mensaje> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}*/

	public String CamposSolicitudCompra(){
		int id =(int) (Math.random()*(9999-1)+1);
		String nombre;
		String empresa;
		int inversion;
		Escaner escaner = new Escaner();

		System.out.println("Introduzca su nombre");
		nombre = escaner.leerString();

		System.out.println("Introduzca La empresa");
		empresa = escaner.leerString();

		System.out.println("Introduzca la inversion1");
		inversion = escaner.leerEntero();
		
		MensajeCompra mensaje = new MensajeCompra(id, nombre, empresa, inversion);
		
		return mensaje.mostrarMensajeRespuestaCompra();
		}
	

}
