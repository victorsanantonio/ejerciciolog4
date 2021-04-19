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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import modelo.Enfermero;
import modelo.Paciente;
import modelo.Persona;

/**
 *
 * @author admin
 */
public class VistaInicial {
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
        /*System.out.println("----EJECUTAR SIMULACIÓN DE DÍA----");
        simularDia();
        System.out.println("---------------------------------------");*/
        System.out.println("----EJECUTAR MOVER----");
        List<Paciente> pacientes = infectar();
        System.out.println("---------------------------------------");
        System.out.println("----PRUEBA DE INSERCIÓN DE DATOS----");
        insertar = new Insertar();
        insertar.start();
        System.out.println("---------------------------------------");
        
        System.out.println("----PRUEBA DE LLAMADA A FUNCIÓN----");
        LlamadasAFunciones llamadasAFunciones = new LlamadasAFunciones();
        llamadasAFunciones.start();
        System.out.println("---------------------------------------");
    }

    /**
     * Método de simulación de infecciones en un día por desplazamientos. Este
     * método ejecuta los movimientos de cada una de las personas infectables.
     * En caso de que se haya infectado en alguno de los lugares, no se
     * ejecutarán más métodos.
     */
    private static void simularDia() {
        vacunarColeccionInfectados();
        vacunarInfectables();
    }

    /**
     * Obtener el último enfermero de la colección
     */
    private static Enfermero obtenerEnfermero(){
        Enfermero vacunable = new Enfermero();
        List<Enfermero> vacunables = vacunable.obtenerVacunables();
        return vacunables.get(vacunables.size()-1);
    }
    
    /**
     * Vacunar a todas las personas infectables que hayan sido infectadas según una colección de infectados
     */
    private static void vacunarColeccionInfectados(){
        //Recuperar la lista de las personas infectadas
        //Collection<Infectable> infectados = infectar();
        //Recuperar el enfermero para vacunar infectados
        Enfermero enfermero = obtenerEnfermero();
        //enfermero.vacunar(infectados);
    }
    
    /**
     * Vacunar a todas las personas infectables que hayan sido infectadas según una persona infectable
     */
    private static void vacunarInfectables(){
        infectar();
        Paciente paciente = new Paciente();
        Infectable infectable = paciente;
        //Recuperar el enfermero para vacunar infectados
        Enfermero enfermero = obtenerEnfermero();
        List<Paciente> pacientes = infectable.obtenerInfectables();
        for(int i=0; i<pacientes.size(); i+=5){
            System.out.println(pacientes.get(i).getInfectado());
            enfermero.vacunar(pacientes.get(i+=2));
            System.out.println(pacientes.get(i).getInfectado());
        }
    }
    
    /**
     * Aplicar probabilidad de infección a todas las personas infectables que se desplacen
     * @return Devolver todas las personas infectadas
     */
    private static List<Paciente> infectar() {
        Paciente infectable = new Paciente();
        List<Paciente> infectables = infectable.obtenerInfectables();
        List<Paciente> infectados = new ArrayList<>();
        for (int i = 0; i < infectables.size(); i++) {

            //Desplazar hasta el supermercado
            infectables.get(i).visitarSuper();
            //¿Se ha infectado?
            if (infectables.get(i).getInfectado() == 1) {
                //SÍ --> Mostrar mensaje
                System.out.println(infectables.get(i).getNombre() + " Infectado al visitar el SUPERMERCADO: " + infectables.get(i).getInfectado());
                infectados.add(infectables.get(i));
            }
            //NO-->

            //Desplazar hasta el trabajo
            infectables.get(i).visitarTrabajo();
            //¿Se ha infectado?
            if (infectables.get(i).getInfectado() == 1) {
                //SÍ --> Mostrar mensaje
                System.out.println(infectables.get(i).getNombre() + " Infectado al visitar el TRABAJO: " + infectables.get(i).getInfectado());
                infectados.add(infectables.get(i));
            }
            //NO-->

            //Coger el transporte público
            infectables.get(i).cogerTransporte();
            //¿Se ha infectado?
            if (infectables.get(i).getInfectado() == 1) {
                //SÍ --> Mostrar mensaje
                System.out.println(infectables.get(i).getNombre() + " Infectado al coger el TRANSPORTE PÚBLICO: " + infectables.get(i).getInfectado());
                infectados.add(infectables.get(i));
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
     * Lee el entero de manera segura sin que se produzca la excepción de
     * formato numérico
     *
     * @return
     */
    private static int leerInt() {
        try {
            String enteroCad = sc.nextLine();
            int entero = Integer.parseInt(enteroCad);
            return entero;
        } catch (NumberFormatException nfe) {
            System.err.println("Error de formato numérico. Debes de introducir un número entero");
        }
        return -1;
    }
}
