package es.poo.examenDiciembre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Principal {

	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

	public static void main(String[] args) {

		try {

			Juguete j1 = new Juguete("Juguete1", 1, 6, 40);
			Juguete j2 = new Juguete("Juguete2", 1, 8, 20);
			JugueteElectronico j3 = new JugueteElectronico("JElectronico1", 1, 6, 40, 10);
			JugueteEducativo j4 = new JugueteEducativo("JEducativo", 3, 8, 30, 20, -5);
			JugueteEducativo j5 = new JugueteEducativo("JEducativo", 5, 10, 50, 25, -10);
			Cliente cliente1 = new Cliente("Daniel");
			Cliente cliente2 = new Cliente("Luisca");
			Jugueteria jugueteria = new Jugueteria();

			cliente1.addJuguete(j1);
			cliente1.addJuguete(j2);
			cliente1.addJuguete(j3);
			cliente2.addJuguete(j4);

			System.out.println("----- PRECIOS -----");
			System.out.println("El precio del juguete " + j1.getNombre() + " es: " + j1.getPrecio());
			System.out.println("El precio del juguete " + j3.getNombre() + " es: " + j3.getPrecio());
			System.out.println("El precio del juguete " + j4.getNombre() + " es: " + j4.getPrecio());

			System.out.println("----- EQUALS -----");
			System.out.println("¿Se llaman igual el juguete " + j4.getNombre() + " y " + j5.getNombre() + " ?");
			if (j4.equals(j5)) {
				System.out.println("Ambos juguetes se llaman igual");
			} else {
				System.out.println("NO se llaman igual");
			}

			System.out.println("----- CLIENTE -----");
			System.out.println("----- CALCULAR GASTO -----");
			System.out.println("El gasto del cliente: " + cliente1.getNombre() + " es: " + cliente1.calcularGasto());
			System.out.println("El gasto del cliente: " + cliente2.getNombre() + " es: " + cliente2.calcularGasto());

			System.out.println("----- LISTA JUGUETES -----");
			System.out.println("Lista juguetes (ordenados por precioBase) cliente: " + cliente1.getNombre());
			cliente1.getListaJuguetes()
					.forEach(juguete -> System.out.println(juguete.getNombre() + " " + juguete.getPrecio() + "€"));

			System.out.println("Lista juguetes (ordenados por precioBase) cliente : " + cliente2.getNombre());
			cliente2.getListaJuguetes()
					.forEach(juguete -> System.out.println(juguete.getNombre() + " " + juguete.getPrecio() + "€"));


			jugueteria.addCliente(cliente1);
			jugueteria.addCliente(cliente2);

			System.out.println("----- JUGUETERIA -----");
			System.out.println("----- LISTA JUGUETES POR CLIENTE -----");
			System.out.println("Lista del cliente: " + cliente1.getNombre());
			jugueteria.listarJuguetesPorCliente("Daniel");
			
			//comprobar el 6.i
//			cliente1.addJuguete(j5);
//			cliente1.addJuguete(j4);
//			jugueteria.addCliente(cliente1);
//			System.out.println("Nueva lista del cliente: " + cliente1.getNombre());
//			jugueteria.listarJuguetesPorCliente("Daniel");
			
			System.out.println("----- LISTA CLIENTES -----");
			jugueteria.listarClientes();

		} catch (EinvalidPropertyException e) {
			System.out.println(e.getMessage());
		} catch (EinvalidClienteException e) {
			System.out.println(e.getMessage());
		}
	}

}
