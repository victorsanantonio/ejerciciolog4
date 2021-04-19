/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Consultar;
import interfaces.Infectable;
import interfaces.Vacunable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author admin
 */
public class Enfermero extends Persona implements Vacunable {

    Consultar consultar;

    public Enfermero() {
    }

    public Enfermero(int id, int id_ciudad, String nombre, char tipo, int infectado) {
        super(id, id_ciudad, nombre, tipo, infectado);
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s;
    }

    @Override
    public List<Enfermero> obtenerVacunables() {
        consultar = new Consultar();
        return consultar.rellenarVacunables();
    }

    @Override
    public void vacunar(Infectable infectado) {
        List<Paciente> infectados = infectado.obtenerInfectables();
        for (int i = 0; i < infectados.size(); i++) {
            infectados.get(i).setInfectado(0);
        }
    }

    @Override
    public void vacunar(Collection<Infectable> coleccion) {
        List<Paciente> infectados = new ArrayList<>();
        for (Infectable i : coleccion) {
            infectados = i.obtenerInfectables();
        }
    }
}
