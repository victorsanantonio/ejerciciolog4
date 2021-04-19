/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Consultar;
import controlador.Insertar;
import controlador.LlamadasAFunciones;
import interfaces.Infectable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import modelo.Enfermero;
import modelo.Paciente;
import modelo.Persona;

/**
 *
 * @author admin
 */
public class VistaInicial {
	private static Logger logger = LogManager.getLogger(VistaInicial.class);
	static Insertar insertar;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		mostrarPersonasSegunIdCiudad();
		System.out.println("----PRUEBA PARA MOSTRAR INFECTABLES----");
		mostrarInfectables();
		System.out.println("---------------------------------------");
		System.out.println("----PRUEBA PARA MOSTRAR VACUNABLES----");
		mostrarVacunables();
		System.out.println("---------------------------------------");
		System.out.println("----PRUEBA PARA MOVEABLE----");
		Paciente paciente = new Paciente();
		paciente.visitarSuper();
		System.out.println(paciente.getInfectado());
		paciente.visitarTrabajo();
		System.out.println(paciente.getInfectado());
		paciente.cogerTransporte();
		System.out.println(paciente.getInfectado());
		System.out.println("---------------------------------------");
		/*
		 * System.out.println("----EJECUTAR SIMULACIÃ“N DE DÃ�A----"); simularDia();
		 * System.out.println("---------------------------------------");
		 */
		System.out.println("----EJECUTAR MOVER----");
		List<Paciente> pacientes = infectar();
		System.out.println("---------------------------------------");
		System.out.println("----PRUEBA DE INSERCIÃ“N DE DATOS----");
		insertar = new Insertar();
		insertar.start();
		System.out.println("---------------------------------------");

		System.out.println("----PRUEBA DE LLAMADA A FUNCIÃ“N----");
		LlamadasAFunciones llamadasAFunciones = new LlamadasAFunciones();
		llamadasAFunciones.start();
		System.out.println("---------------------------------------");

		// BasicConfigurator.configure();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		System.out.println("Hola");
		logger.info("Este es el fichero de configuración: " + url);
		logger.warn("Esto es un aviso");
		// logger2.debug("Esto es un debug");
	}

	/**
	 * MÃ©todo de simulaciÃ³n de infecciones en un dÃ­a por desplazamientos. Este
	 * mÃ©todo ejecuta los movimientos de cada una de las personas infectables. En
	 * caso de que se haya infectado en alguno de los lugares, no se ejecutarÃ¡n
	 * mÃ¡s mÃ©todos.
	 */
	private static void simularDia() {
		vacunarColeccionInfectados();
		vacunarInfectables();
	}

	/**
	 * Obtener el Ãºltimo enfermero de la colecciÃ³n
	 */
	private static Enfermero obtenerEnfermero() {
		Enfermero vacunable = new Enfermero();
		List<Enfermero> vacunables = vacunable.obtenerVacunables();
		return vacunables.get(vacunables.size() - 1);
	}

	/**
	 * Vacunar a todas las personas infectables que hayan sido infectadas segÃºn una
	 * colecciÃ³n de infectados
	 */
	private static void vacunarColeccionInfectados() {
		// Recuperar la lista de las personas infectadas
		// Collection<Infectable> infectados = infectar();
		// Recuperar el enfermero para vacunar infectados
		Enfermero enfermero = obtenerEnfermero();
		// enfermero.vacunar(infectados);
	}

	/**
	 * Vacunar a todas las personas infectables que hayan sido infectadas segÃºn una
	 * persona infectable
	 */
	private static void vacunarInfectables() {
		infectar();
		Paciente paciente = new Paciente();
		Infectable infectable = paciente;
		// Recuperar el enfermero para vacunar infectados
		Enfermero enfermero = obtenerEnfermero();
		List<Paciente> pacientes = infectable.obtenerInfectables();
		for (int i = 0; i < pacientes.size(); i += 5) {
			System.out.println(pacientes.get(i).getInfectado());
			enfermero.vacunar(pacientes.get(i += 2));
			System.out.println(pacientes.get(i).getInfectado());
			logger.info("Listado de pacientes");
		}
	}

	/**
	 * Aplicar probabilidad de infecciÃ³n a todas las personas infectables que se
	 * desplacen
	 * 
	 * @return Devolver todas las personas infectadas
	 */
	private static List<Paciente> infectar() {
		Paciente infectable = new Paciente();
		List<Paciente> infectables = infectable.obtenerInfectables();
		List<Paciente> infectados = new ArrayList<>();
		for (int i = 0; i < infectables.size(); i++) {

			// Desplazar hasta el supermercado
			infectables.get(i).visitarSuper();
			// Â¿Se ha infectado?
			if (infectables.get(i).getInfectado() == 1) {
				// SÃ� --> Mostrar mensaje
				System.out.println(infectables.get(i).getNombre() + " Infectado al visitar el SUPERMERCADO: "
						+ infectables.get(i).getInfectado());
				infectados.add(infectables.get(i));
				logger.info("Paciente añadido");
			}
			// NO-->

			// Desplazar hasta el trabajo
			infectables.get(i).visitarTrabajo();
			// Â¿Se ha infectado?
			if (infectables.get(i).getInfectado() == 1) {
				// SÃ� --> Mostrar mensaje
				System.out.println(infectables.get(i).getNombre() + " Infectado al visitar el TRABAJO: "
						+ infectables.get(i).getInfectado());
				infectados.add(infectables.get(i));
				logger.info("Paciente añadido");
			}
			// NO-->

			// Coger el transporte pÃºblico
			infectables.get(i).cogerTransporte();
			// Â¿Se ha infectado?
			if (infectables.get(i).getInfectado() == 1) {
				// SÃ� --> Mostrar mensaje
				System.out.println(infectables.get(i).getNombre() + " Infectado al coger el TRANSPORTE PÃšBLICO: "
						+ infectables.get(i).getInfectado());
				infectados.add(infectables.get(i));
				logger.info("Paciente añadido");
			}
		}
		return infectados;
	}

	private static void mostrarInfectables() {
		Paciente paciente = new Paciente();
		List<Paciente> pacientes = paciente.obtenerInfectables();
		for (Paciente p : pacientes) {
			System.out.println(p);
		}
	}

	private static void mostrarVacunables() {
		Enfermero enfermero = new Enfermero();
		List<Enfermero> enfermeros = enfermero.obtenerVacunables();
		for (Enfermero e : enfermeros) {
			System.out.println(e);
		}
	}

	private static void mostrarPersonasSegunIdCiudad() {
		Consultar consultar = new Consultar();
		int idCiudad = solicitarCiudad();
		System.out.println(idCiudad);
		List<Persona> personas = consultar.consultarPersonasSegunIdCiudad(idCiudad);

		for (Persona p : personas) {
			System.out.println(p);
		}
	}

	/**
	 * Solicita una ciudad por teclado
	 */
	private static int solicitarCiudad() {
		System.out.print("Introduce el identificador de una ciudad:\t");
		int idCiudad = leerInt();
		return idCiudad;
	}

	/**
	 * Lee el entero de manera segura sin que se produzca la excepciÃ³n de formato
	 * numÃ©rico
	 *
	 * @return
	 */
	private static int leerInt() {
		try {
			String enteroCad = sc.nextLine();
			int entero = Integer.parseInt(enteroCad);
			logger.info("Entero leído con éxito");
			return entero;
		} catch (NumberFormatException nfe) {
			System.err.println("Error de formato numÃ©rico. Debes de introducir un nÃºmero entero");
			logger.fatal("Error fatal de formato numérico");
		}
		return -1;
	}
}
