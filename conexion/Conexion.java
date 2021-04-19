package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Conexion {

    Connection connection = null;

    /**
     * Creación de la conexión para la base de datos
     *
     * @return
     */
    public Connection conexion() {
        try {
            //Declaración del driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Creación o instanciación de la conexión
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/prueba_serbatic", "root", "");
            System.out.println("Éxito al conectar");
        } catch (SQLException e) {
            System.err.println("Error de conexión " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
