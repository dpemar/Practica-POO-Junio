package es.poo.banco;

import java.io.Serializable;

public class Persona implements Serializable{
	String nombre;
	String dni;
	
	public Persona(String nombre, String dni){
		super();
		this.nombre = nombre;
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	/*public void mostrarEstadoClientes() {
		// TODO Auto-generated method stub
		
	}*/
}
