package es.poo.bolsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

import es.poo.banco.AgenteDeInversiones;

public class BolsaDeValores {
	private String nombreBolsa;
	private HashSet<Empresa> listaEmpresas = new HashSet<Empresa>();
	private HashSet<Empresa> copiaListaEmpresas;
	private ArrayList<String> operacionesOperaciones = new ArrayList<String>();

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

	// Intentar operacion desde el broker
	public String intentaOperacion(String cadenaCodificada, HashSet<Empresa> listaEmpresas) {

		boolean esRealizada;
		int numAccionesCompradas;
		float valorAccion;
		float dineroRestante;

		String[] partes = cadenaCodificada.split(Pattern.quote("|"));
		String operacionIdDecodificado = partes[0];
		String nombreClienteDecodificado = partes[1];
		String nombreEmpresaDecodificado = partes[2];
		String cantidadMaxAInvertir = partes[3];

		System.out.println("Bolsa ha terminado de decodificar");
		System.out.println("---------------------------------");
		System.out.println("Bolsa realiza la operacion de compra de acciones...");

		Empresa empresaEncontrado = null;
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
				empresaEncontrado = empresa;
			}
		}
		if (empresaEncontrado == null || (empresaEncontrado.getValorAccionActual() > Float.parseFloat(cantidadMaxAInvertir))) {
			System.out.println("Empresa que desea invertir no existe o el valor de accion es insuficiente");
			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|";
		} else {
			valorAccion = empresaEncontrado.getValorAccionActual();

			float cantidadMaximaAInvertir = Float.parseFloat(cantidadMaxAInvertir);
			float resto;

			resto = cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual() - Math.round(cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual());

			numAccionesCompradas = (int) Math.round(cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual());

			dineroRestante = resto * empresaEncontrado.getValorAccionActual();
			System.out.println("Compra terminada");

			empresaEncontrado.setValorAccionPrevio(empresaEncontrado.getValorAccionActual());
			empresaEncontrado.setValorAccionActual(empresaEncontrado.getValorAccionActual() + (numAccionesCompradas * empresaEncontrado.getValorAccionActual()));

			for (Empresa empresa : listaEmpresas) {
				if (empresa.equals(empresaEncontrado)) {
					listaEmpresas.remove(empresa);
					listaEmpresas.add(empresaEncontrado);
				}
			}
			System.out.println("Bolsa ha terminado la operacion compra de acciones");
			System.out.println("--------------");
			System.out.println("Bolsa envia cadena de texto de respuesta compra al broker");

			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + "true" + "|" + numAccionesCompradas
					+ "|" + valorAccion + "|" + dineroRestante + "|";
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

	public Empresa buscarMejorValor() {
		ArrayList<Empresa> arrayList = new ArrayList<Empresa>(listaEmpresas);
		Empresa encontrado = null;
		for (int i = 0; i < arrayList.size(); i++) {

			if (arrayList.get(i).getValorAccionActual() < arrayList.get(i + 1).getValorAccionActual()) {
				encontrado = arrayList.get(i + 1);
			} else {
				encontrado = arrayList.get(i);
			}
		}
		return encontrado;
	}

	// Eliminar empresa mediante Empresa
	public void eliminarTodasOperaciones() {
		this.operacionesOperaciones.removeAll(operacionesOperaciones);
	}

}
