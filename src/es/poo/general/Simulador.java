package es.poo.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;

import es.poo.banco.AgenteDeInversiones;
import es.poo.banco.Banco;
import es.poo.banco.Cliente;

public class Simulador {
	static HashSet<Cliente> bolsaCli = new HashSet<Cliente>();
	private static Cliente cli;
	private static Banco banco;
	static Cliente cliente4 = new Cliente("nombre4", " dn4", 150, null);

	public static void iniciar() {
		AgenteDeInversiones broker = new AgenteDeInversiones();
		Cliente cliente1 = new Cliente("nombre", " dni", 150, null);
		Cliente cliente2 = new Cliente("nombre2", " dn2", 150, null);
		Cliente cliente3 = new Cliente("nombre3", " dni3", 150, null);
		banco = new Banco("Santander", bolsaCli, broker);
		banco.anadirCliente(cliente1);
		banco.anadirCliente(cliente2);
		banco.anadirCliente(cliente3);
		banco.eliminarCliente(cliente3);
		InterfazDeUsuario.seleccion();
	}

	public static void mostrarClientes() {
		for (Cliente bolsa : bolsaCli) {
			bolsa.mostrarEstadoClientes();
		}
	}

	public static void clienteConDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su nombre");
		String nombre = sc.nextLine();

		System.out.println("Introduzca su dni");
		String dni = sc.nextLine();

		System.out.println("Introduzca su saldo");
		double saldo = sc.nextDouble();
		cli = new Cliente(nombre, dni, saldo, null);
		banco.anadirCliente(cli);
	}

	public static void eliminarCliNombre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del cliente que quieres borar");
		String nombre = sc.nextLine();
		banco.eliminarCliente(nombre);
	}
	public static void copiaSeguridad(){
		Cliente cliente= null;
		String string="prueba serializable";
		try{
		    FileOutputStream fs = new FileOutputStream("BolsaDeClientes.txt");//Creamos el archivo
		    ObjectOutputStream os = new ObjectOutputStream(fs);//Esta clase tiene el método writeObject() que necesitamos
		   for (Cliente cli : banco.bolsaClientes) {
		    	cliente=cli;
		    	os.writeObject(cliente);//El método writeObject() serializa el objeto y lo escribe en el archivo
		    }
		    os.close();//Hay que cerrar siempre el archivo
		  }catch(FileNotFoundException e){
		    e.printStackTrace();
		  }catch(IOException e){
		    e.printStackTrace();
		  }
	}
	public static void restaurarCopia(){
	banco.bolsaClientes.toArray(banco.array);
		
		try{
			  FileInputStream fis = new FileInputStream("BolsaDeClientes.txt");
			  ObjectInputStream ois = new ObjectInputStream(fis);
			  Object aux = ois.readObject();
			  while (aux!=null)
			  {
			      if (aux instanceof Cliente)
			          System.out.println(aux);  // Se escribe en pantalla el objeto
			      aux = ois.readObject();
			  }
			  ois.close();
			}catch(FileNotFoundException e){
			  e.printStackTrace();
			}catch(IOException e){
			  e.printStackTrace();
			}catch(ClassNotFoundException e){
			  e.printStackTrace();
			}
		//cli2.mostrarEstadoClientes();
		
	}
}