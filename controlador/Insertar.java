/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Paciente;

/**
 *
 * @author admin
 */
public class Insertar extends Thread{

    public Conexion cn;
    public Connection connection;

    public void run(List<Paciente> pacientes){
        insertarPersona(pacientes);
    }
    
    /**
     * Insertar una lista de pacientes en la base de datos
     * @param pacientes 
     */
    public void insertarPersona(List<Paciente> pacientes) {
        try {
            cn = new Conexion();
            connection = cn.conexion();
            try {
                for (int i = 0; i < pacientes.size(); i++) {

                    String sql = "insert into personas_informe(id_ciudad, Nombre, Tipo, Infectado) values (?,?,?,?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, pacientes.get(i).getId_ciudad());
                    preparedStatement.setString(2, pacientes.get(i).getNombre());
                    preparedStatement.setString(3, Character.toString(pacientes.get(i).getTipo()));
                    preparedStatement.setInt(4, pacientes.get(i).getInfectado());

                    preparedStatement.execute();

                }
                System.out.println("Éxito al guardar");
            } catch (Exception e) {
                System.out.println("Error de guardado");
                System.err.println(e.getMessage());
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
