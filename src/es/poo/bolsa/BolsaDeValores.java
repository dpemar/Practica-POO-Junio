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
			empresa.mostrarEstadoEmpresa();
		}
	}

	// Actualizar valor acciones
	public void actualizarValorAcciones() {
		for (Empresa empresa : listaEmpresas) {
			empresa.actualizarValorAcciones();
		}
	}

	public String getNombreBolsa() {
		return nombreBolsa;
	}

	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}

}
