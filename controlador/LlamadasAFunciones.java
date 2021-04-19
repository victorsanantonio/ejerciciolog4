/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author admin
 */
public class LlamadasAFunciones extends Thread {

    public Conexion cn;
    public Connection connection;
    public Statement statement;

    /**
     * Ejecución de función de la base de datos
     */
    public void run() {
        cn = new Conexion();
        connection = cn.conexion();
        try {
            statement = connection.createStatement();

            if (connection != null) {
                //** Operaciones
                //procedimiento almacenado sin parametros
                CallableStatement stmt = connection.prepareCall("{?= call get_num_pacientes()}");
                stmt.registerOutParameter(1, Types.INTEGER);
                
                stmt.execute();
                
                Integer resultado = stmt.getInt(1);
                System.out.println(resultado+" pacientes");

                connection.close();
            } else {
                System.out.println("Conexión no realizada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
