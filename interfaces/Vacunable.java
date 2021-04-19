/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Collection;
import java.util.List;
import modelo.Enfermero;

/**
 *
 * @author admin
 */
public interface Vacunable {
    /**
     * Método usado para obtener todas las personas vacunables
     * @return 
     */
    public List<Enfermero> obtenerVacunables();
    public void vacunar(Infectable infectado);
    public void vacunar(Collection<Infectable> coleccion);
}
