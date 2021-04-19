/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Consultar;
import interfaces.Infectable;
import interfaces.Moveable;
import java.util.List;

/**
 *
 * @author admin
 */
public class Paciente extends Persona implements Infectable, Moveable {

    private final int CONTAGIO_SUPERMERCADO = 10;
    private final int CONTAGIO_TRABAJO = 12;
    private final int CONTAGIO_TRANSPORTE_PUBLICO = 16;
    
    Consultar consultar;

    public Paciente() {
    }

    public Paciente(int id, int id_ciudad, String nombre, char tipo, int infectado) {
        super(id, id_ciudad, nombre, tipo, infectado);
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s;
    }

    @Override
    public List<Paciente> obtenerInfectables() {
        consultar = new Consultar();
        return consultar.rellenarInfectables();
    }

    @Override
    public void infectar(boolean infectado) {
        if(infectado){
            super.setInfectado(1);
        }
        else{
            super.setInfectado(0);
        }
    }

    @Override
    public void visitarSuper() {
        int numero = (int) (Math.random() * 100) + 1;
        if (numero > 0 && numero <= CONTAGIO_SUPERMERCADO) {
            infectar(true);
        }
        else{
            infectar(false);
        }
    }

    @Override
    public void visitarTrabajo() {
        int numero = (int) (Math.random() * 100) + 1;
        if (numero > 0 && numero <= CONTAGIO_TRABAJO) {
            infectar(true);
        }
        else{
            infectar(false);
        }
    }

    @Override
    public void cogerTransporte() {
        int numero = (int) (Math.random() * 100) + 1;
        if (numero > 0 && numero <= CONTAGIO_TRANSPORTE_PUBLICO) {
            infectar(true);
        }
        else{
            infectar(false);
        }
    }
}
