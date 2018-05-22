package es.poo.examenDiciembre;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

//5
public class Cliente {

	private Set<Juguete> listaJuguetes = new TreeSet<Juguete>();
	private String nombre;

	public Cliente() {

	}

	public Cliente(String nombre) {
		this.nombre = nombre;
	}

	public Set<Juguete> getListaJuguetes() {
		return listaJuguetes;
	}

	public void setListaJuguetes(Set<Juguete> listaJuguetes) {
		this.listaJuguetes = listaJuguetes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// i
	public void addJuguete(Juguete j) {
		this.listaJuguetes.add(j);
	}

	// ii Probar a hacerlo con Iterator
	public int calcularGasto() {
		int gastoTotal = 0;
		for (Juguete juguete : listaJuguetes) {
			gastoTotal = gastoTotal + juguete.getPrecio();
		}
		return gastoTotal;
	}

	// iii
	public void listarJuguetes() {
		for (Juguete juguete : listaJuguetes) {
			System.out.println("Nombre: " + juguete.nombre + " Precio Base: " + juguete.precioBase);
		}
	}

}
