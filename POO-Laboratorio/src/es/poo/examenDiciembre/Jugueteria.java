package es.poo.examenDiciembre;

import java.util.ArrayList;
import java.util.List;

//6
public class Jugueteria {

	private List<Cliente> listaClientes = new ArrayList<Cliente>();

	public Jugueteria() {

	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	// i
	public void addCliente(Cliente c) {
		if (!listaClientes.contains(c)) {
			this.listaClientes.add(c);
		} else {
			this.listaClientes.remove(c);
			this.listaClientes.add(c);
		}
	}

	// ii
	public void listarJuguetesPorCliente(String nombreCliente) throws EinvalidClienteException {
		for (Cliente cliente : listaClientes) {
			if ( !nombreCliente.equals(cliente.getNombre())) {
				throw new EinvalidClienteException("El cliente introducido no existe");
			} else {
				cliente.listarJuguetes();
				break; //ver como se puede hacer sin el break
			}
		}
	}

	// iii
	public void listarClientes() {
		for (Cliente cliente : listaClientes) {
			System.out.println("Nombre: " + cliente.getNombre());
		}
	}

}
