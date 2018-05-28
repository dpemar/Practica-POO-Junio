package es.poo.banco;

import java.util.ArrayList;

public class Cliente extends Persona {
	
	double saldo;
	ArrayList<PaqueteDeAcciones> paqueteDeAcciones = new ArrayList<PaqueteDeAcciones>();

	public Cliente(String nombre, String dni, double saldo, ArrayList<PaqueteDeAcciones> paqueteDeAcciones2) {
		super(nombre, dni);
		this.saldo = saldo;
		this.paqueteDeAcciones = paqueteDeAcciones;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}

	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}

	@Override
	public String getDni() {
		// TODO Auto-generated method stub
		return super.getDni();
	}

	@Override
	public void setDni(String dni) {
		// TODO Auto-generated method stub
		super.setDni(dni);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<PaqueteDeAcciones> getPaqueteDeAcciones() {
		return paqueteDeAcciones;
	}

	public void setPaqueteDeAcciones(ArrayList<PaqueteDeAcciones> paqueteDeAcciones) {
		this.paqueteDeAcciones = paqueteDeAcciones;
	}

	public void mostrarEstadoClientes() {
		System.out.println("Nombre cliente: " + nombre);
		System.out.println("Dni: " + dni);
		System.out.println("Saldo actual: " + saldo);
	}

}
