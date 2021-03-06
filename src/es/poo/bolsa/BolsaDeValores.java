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

import com.sun.tools.javac.code.Source;

import es.poo.banco.AgenteDeInversiones;

public class BolsaDeValores {
	private String nombreBolsa;
	private HashSet<Empresa> listaEmpresas;
	private HashSet<Empresa> copiaListaEmpresas;
	private ArrayList<String> operacionesOperaciones = new ArrayList<String>();

	public BolsaDeValores(String nombreBolsa, HashSet<Empresa> listaEmpresas) {
		super();
		this.nombreBolsa = nombreBolsa;
		this.listaEmpresas = listaEmpresas;
	}

	public BolsaDeValores() {
	}

	public String getNombreBolsa() {
		return nombreBolsa;
	}

	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}

	public HashSet<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(HashSet<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
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
		boolean encontrado = false;
		Empresa EmpresaEncontrada = null;
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getNombreEmpresa().equals(nombreEmpresa)) {
				EmpresaEncontrada = empresa;
				encontrado = true;
			}
		}
		if (encontrado) {
			this.listaEmpresas.remove(EmpresaEncontrada);
			System.out.println("Empresa " + nombreEmpresa + " eliminada correctamente\n");

		} else {
			System.out.println("Empresa no encontrada");
		}
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
	public String intentaOperacionCompra(String cadenaCodificada) {
		boolean esRealizada;
		int numAccionesCompradas = 0;
		float valorAccion = 0;
		float dineroRestante = 0;

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
		if (empresaEncontrado == null
				|| (empresaEncontrado.getValorAccionActual() > Float.parseFloat(cantidadMaxAInvertir))) {
			System.out.println("Empresa que desea invertir no existe o el valor de accion es insuficiente");
			System.out.println(operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|"
					+ nombreEmpresaDecodificado);
		} else {
			valorAccion = empresaEncontrado.getValorAccionActual();

			float cantidadMaximaAInvertir = Float.parseFloat(cantidadMaxAInvertir);
			float resto;

			resto = cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual()
					- Math.round(cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual());

			numAccionesCompradas = (int) Math.round(cantidadMaximaAInvertir / empresaEncontrado.getValorAccionActual());

			dineroRestante = resto * empresaEncontrado.getValorAccionActual();
			System.out.println();
			System.out.println("Compra terminada");
			System.out.println();
			empresaEncontrado.setValorAccionPrevio(empresaEncontrado.getValorAccionActual());
			empresaEncontrado.setValorAccionActual(empresaEncontrado.getValorAccionActual()
					+ (numAccionesCompradas * empresaEncontrado.getValorAccionActual()));

			for (Empresa empresa : listaEmpresas) {
				if (empresa.equals(empresaEncontrado)) {
					listaEmpresas.remove(empresa);
					listaEmpresas.add(empresaEncontrado);
					break;
				}
			}
			System.out.println("Bolsa ha terminado la operacion compra de acciones");
			System.out.println("--------------");
			System.out.println("Bolsa envia cadena de texto de respuesta compra al broker");
			System.out.println();

		}
		return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + nombreEmpresaDecodificado + "|"
				+ "true" + "|" + numAccionesCompradas + "|" + valorAccion + "|" + dineroRestante + "|";
	}

	// Intentar operacion desde el broker
	public String intentaOperacionVenta(String cadenaCodificada) {

		boolean esRealizada;
		float valorAccion;
		int dineroGanado;

		String[] partes = cadenaCodificada.split(Pattern.quote("|"));
		String operacionIdDecodificado = partes[0];
		String nombreClienteDecodificado = partes[1];
		String nombreEmpresaDecodificado = partes[2];
		String cantidadAcciones = partes[3];

		System.out.println("Bolsa ha terminado de decodificar");
		System.out.println("---------------------------------");
		System.out.println("Bolsa realiza la operacion de venta de acciones");

		Empresa empresaEncontrado = null;
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
				empresaEncontrado = empresa;
			}
		}
		if (empresaEncontrado == null) {
			System.out.println("Las acciones de la Empresa que desea vender no existe");
			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|";
		} else {
			valorAccion = empresaEncontrado.getValorAccionActual();
			int numAcciones = Integer.parseInt(cantidadAcciones);
			dineroGanado = (int) (numAcciones * valorAccion);

			System.out.println();
			System.out.println("Venta terminada");
			System.out.println();

			empresaEncontrado.setValorAccionPrevio(empresaEncontrado.getValorAccionActual());
			empresaEncontrado.setValorAccionActual(empresaEncontrado.getValorAccionActual()
					- (numAcciones * empresaEncontrado.getValorAccionActual()));

			for (Empresa empresa : listaEmpresas) {
				if (empresa.equals(empresaEncontrado)) {
					listaEmpresas.remove(empresa);
					listaEmpresas.add(empresaEncontrado);
					break;
				}
			}
			System.out.println("Bolsa ha terminado la operacion venta acciones");
			System.out.println("--------------");
			System.out.println("Bolsa envia cadena de texto de respuesta venta al broker");
			System.out.println();

			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + nombreEmpresaDecodificado + "|"
					+ numAcciones + "|" + "true" + "|" + "|" + valorAccion + "|" + dineroGanado + "|";

		}
	}

	public String intentaOperacionActualizacion(String cadenaCodificada) {

		boolean esRealizada;

		String[] partes = cadenaCodificada.split(Pattern.quote("|"));
		String operacionIdDecodificado = partes[0];
		String nombreClienteDecodificado = partes[1];
		String nombreEmpresaDecodificado = partes[2];
		String fechaDecodificado = partes[3];

		System.out.println("Bolsa ha terminado de decodificar");
		System.out.println("---------------------------------");
		System.out.println("Bolsa realiza la operacion de actualizacion de acciones");

		Empresa empresaEncontrado = null;
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getNombreEmpresa().equals(nombreEmpresaDecodificado)) {
				empresaEncontrado = empresa;
			}
		}
		if (empresaEncontrado == null) {
			System.out.println("Las acciones de la Empresa que desea actualizar no existe");
			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|";
		} else {

			System.out.println();
			System.out.println("Actualizacion terminada");
			System.out.println();

			System.out.println("Bolsa ha terminado la operacion actualizar acciones");
			System.out.println("--------------");
			System.out.println("Bolsa envia cadena de texto de respuesta actualizacion al broker");
			System.out.println();

			return operacionIdDecodificado + "|" + nombreClienteDecodificado + "|" + nombreEmpresaDecodificado + "|"
					+ fechaDecodificado;

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

	// Eliminar todas las operaciones
	public void eliminarTodasOperaciones() {
		this.operacionesOperaciones.removeAll(operacionesOperaciones);
	}

}
