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
     * Creaci贸n de la conexi贸n para la base de datos
     *
     * @return
     */
    public Connection conexion() {
        try {
            //Declaraci贸n del driver JDBC
            Class.forName("com.mysql.cj.Driver");
            //Creaci贸n o instanciaci贸n de la conexi贸n
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/prueba_serbatic", "root", "");
            System.out.println("脡xito al conectar");
        } catch (SQLException e) {
            System.err.println("Error de conexi贸n " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error("Error de conexi髇");
        }
        return connection;
    }
}
