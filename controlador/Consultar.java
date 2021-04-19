/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Enfermero;
import modelo.Paciente;
import modelo.Persona;

/**
 *
 * @author admin
 */
public class Consultar {

    public Conexion cn;
    public Connection connection;

    /**
     * Obtener todas las personas de una ciudad determinada
     * @param idCiudad Identificador de la ciudad
     * @return lista de personas
     */
    public List<Persona> consultarPersonasSegunIdCiudad(int idCiudad) {
        try {
            cn = new Conexion();
            connection = cn.conexion();
            Persona persona = null;
            List<Persona> personas = new ArrayList<>();
            String sql = "select * from personas where id_ciudad=" + idCiudad + ";";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    persona = new Persona(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4).charAt(0), rs.getInt(5));
                    personas.add(persona);
                }
                
                return personas;
            } catch (Exception e) {
                System.err.println("Error de consulta" + e.getMessage());
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Obtener todas las personas que son vacunables
     * @return Lista de personas vacunables
     */
    public List<Enfermero> rellenarVacunables() {
        try {
            cn = new Conexion();
            connection = cn.conexion();
            Enfermero enfermero = null;
            List<Enfermero> vacunables = new ArrayList<>();
            String sql = "select * from personas";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    if (rs.getString(4).charAt(0) == 'E') {
                        enfermero = new Enfermero(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4).charAt(0), rs.getInt(5));
                        vacunables.add(enfermero);
                    }
                }
                
                return vacunables;
            } catch (Exception e) {
                System.err.println("Error de consulta" + e.getMessage());
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Obtener todas las personas que son infectables
     * @return Lista de personas infectables
     */
    public List<Paciente> rellenarInfectables() {
        try {
            cn = new Conexion();
            connection = cn.conexion();
            Paciente paciente = null;
            List<Paciente> infectables = new ArrayList<>();
            String sql = "select * from personas";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    if (rs.getString(4).charAt(0) == 'P') {
                        paciente = new Paciente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4).charAt(0), rs.getInt(5));
                        infectables.add(paciente);
                    }
                }
                
                return infectables;
            } catch (Exception e) {
                System.err.println("Error de consulta" + e.getMessage());
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
