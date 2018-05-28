package es.poo.bolsa;

import java.util.HashSet;

public class BolsaDeValores {

	private String nombreBolsa;
	private HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();

	public BolsaDeValores(String nombreBolsa, HashSet<Empresa> listaEmpresas) {
		super();
		this.nombreBolsa = nombreBolsa;
		this.listaEmpresas = listaEmpresas;
	}

	public String getNombreBolsa() {
		return nombreBolsa;
	}

	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}

	// Añadir empresa
	public void anadirEmpresa(Empresa empresa) {
		this.listaEmpresas.add(empresa);
	}

	// Eliminar empresa mediante Empresa
	public void eliminarEmpresa(Empresa empresa) {
		this.listaEmpresas.remove(empresa);
	}

	// Eliminar empresa mediante Nombre
	public void eliminarEmpresa(String nombreEmpresa) {
		Empresa encontrado = null;
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getNombreEmpresa().equals(nombreEmpresa)) {
				encontrado = empresa;
			}
		}
		this.listaEmpresas.remove(encontrado);
	}

	// Listado estado empresas
	public void listarEstadoEmpresas() {
		for (Empresa empresa : listaEmpresas) {
			empresa.mostrarEstadoEmpresa();
		}
	}

	// Actualizar valor acciones
	public void actualizarValorAcciones() {
		// listaEmpresas.forEach(empresa-> empresa.actualizarValorAcciones());
		for (Empresa empresa : listaEmpresas) {
			empresa.actualizarValorAcciones();
		}
	}

}
