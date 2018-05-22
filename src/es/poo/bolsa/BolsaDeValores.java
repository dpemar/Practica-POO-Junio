package es.poo.bolsa;

import java.util.HashSet;

public class BolsaDeValores {

	private String nombreBolsa;
	private HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();

	// Añadir empresa
	public void añadirEmpresa(Empresa empresa) {
		this.listaEmpresas.add(empresa);
	}

	// Eliminar empresa
	public void eliminarEmpresa(Empresa empresa) {
		this.listaEmpresas.remove(empresa);
	}

	// Listado estado empresas
	public void listarEstadoEmpresas() {
		for (Empresa empresa : listaEmpresas) {
			System.out.println("Valor actual accion: " + empresa.getValorAccionActual());
			System.out.println("Incremento: " + empresa.getIncremento());
		}
	}
}
