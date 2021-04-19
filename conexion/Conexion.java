package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.LogManager;

import vista.VistaInicial;

/**
 *
 * @author admin
 */
public class Conexion {

	static org.apache.log4j.Logger logger = LogManager.getLogger(Conexion.class);
    Connection connection = null;

    /**
     * Creación de la conexión para la base de datos
     *
     * @return
     */
    public Connection conexion() {
        try {
            //Declaración del driver JDBC
            Class.forName("com.mysql.cj.Driver");
            //Creación o instanciación de la conexión
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/prueba_serbatic", "root", "");
            System.out.println("Éxito al conectar");
        } catch (SQLException e) {
            System.err.println("Error de conexión " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error("Error de conexi�n");
        }
        return connection;
    }
}
