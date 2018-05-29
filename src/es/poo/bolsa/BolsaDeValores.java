package es.poo.bolsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		for (Empresa empresa : listaEmpresas) {
			empresa.actualizarValorAcciones();
		}
	}

	// Realizar copia de seguridad
	public void copiaSeguridadBolsa(String path) {

		FileOutputStream fileOut;
		ObjectOutputStream objectOut;

		try {
			fileOut = new FileOutputStream(path);
			objectOut = new ObjectOutputStream(fileOut);

			for (Empresa empresa : listaEmpresas) {
				if (objectOut != null) {
					objectOut.writeObject(empresa);
				}
			}
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Restaurar copia de seguridad
	public void restaurarCopiaSeguridadBolsa(String path) {

		FileInputStream fileIn;
		ObjectInputStream objectIn;

		try {
			fileIn = new FileInputStream(path);
			objectIn = new ObjectInputStream(fileIn);

			for (Empresa empresa : listaEmpresas) {
				if (objectIn != null) {
					objectIn.readObject().toString();
				}
			}
			objectIn.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
