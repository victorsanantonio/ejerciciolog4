/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import modelo.Paciente;

/**
 *
 * @author admin
 */
public interface Infectable {
    /**
     * Método usado para obtener todas las personas infectables
     * @return 
     */
    public List<Paciente> obtenerInfectables();
    public void infectar(boolean infectado);
}
