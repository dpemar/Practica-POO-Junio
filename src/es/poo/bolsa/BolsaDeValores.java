package es.poo.bolsa;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


//import es.poo.banco.Empresa;

public class BolsaDeValores {

	private String nombreBolsa;
	private static HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	private HashSet<Empresa> copiaListaEmpresas;
	private static Empresa aux = null;

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

			objectOut.writeObject(listaEmpresas);

			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Restaurar copia de seguridad
	@SuppressWarnings("unchecked")
	public void restaurarCopiaSeguridadBolsa(String path) {

		FileInputStream fileIn;
		ObjectInputStream objectIn;

		try {
			fileIn = new FileInputStream(path);
			objectIn = new ObjectInputStream(fileIn);
			listaEmpresas.removeAll(listaEmpresas);

			copiaListaEmpresas = (HashSet<Empresa>) objectIn.readObject();

			for (Empresa empresa : copiaListaEmpresas) {
				listaEmpresas.add(empresa);
				empresa.mostrarEstadoEmpresa();
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
	public Empresa buscarMejorValor(){
		ArrayList<Empresa> arrayList = new ArrayList<Empresa>(listaEmpresas);
		Empresa encontrado = null;
		for(int i=0; i < arrayList.size();i++){
			
			if(arrayList.get(i).getValorAccionActual()< arrayList.get(i+1).getValorAccionActual()){
				encontrado=arrayList.get(i+1);
			}else{
				encontrado=arrayList.get(i);
			}
		}
		return encontrado;	
	}

}
