package es.poo.banco;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable {

	float saldo;
	ArrayList<PaqueteDeAcciones> listaPaqueteDeAcciones = new ArrayList<PaqueteDeAcciones>();

	public Cliente(String nombre, String dni, float saldo, ArrayList<PaqueteDeAcciones> listaPaqueteDeAcciones) {
		super(nombre, dni);
		this.saldo = saldo;
		this.listaPaqueteDeAcciones = listaPaqueteDeAcciones;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getDni() {
		return dni;
	}

	@Override
	public void setDni(String dni) {
		this.dni = dni;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public ArrayList<PaqueteDeAcciones> getPaqueteDeAcciones() {
		return listaPaqueteDeAcciones;
	}

	public void setPaqueteDeAcciones(ArrayList<PaqueteDeAcciones> listaPaqueteDeAcciones) {
		this.listaPaqueteDeAcciones = listaPaqueteDeAcciones;
	}
	public PaqueteDeAcciones encontrar (String nombreEmpresa){
		PaqueteDeAcciones clienteAccionEncontrado= null;
		for (PaqueteDeAcciones cliente : listaPaqueteDeAcciones ) {
			if (cliente.nombreEmpresa==nombreEmpresa) 
				clienteAccionEncontrado = cliente;
			}
		return clienteAccionEncontrado;
		
	}

	public void mostrarEstadoClientes() {
		PaqueteDeAcciones clienteAccionEncontrado= null;
		System.out.println("Nombre cliente: " + nombre);
		System.out.println("Dni: " + dni);
		System.out.println("Saldo actual: " + saldo);
		for (PaqueteDeAcciones cliente : listaPaqueteDeAcciones ) {
				clienteAccionEncontrado = cliente;
				System.out.println("Acciones: " + clienteAccionEncontrado.mostrarAcciones());
		}
		
	}


}
