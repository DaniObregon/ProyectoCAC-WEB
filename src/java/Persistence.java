
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistence {

    private Connection connection;
    private ResultSet resulset;
    private PreparedStatement preparedStatement;

    private ResultSetMetaData resultSetMetaData;

    public String servidor, baseDeDatos, usuario, clave, ejecutar;

    public Connection conectarse() {

        servidor = "localhost:3306/";
        baseDeDatos = "cacproyecto01?autoReconnect=true&useSSL=false";
        usuario = "root";
        clave = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = (Connection) DriverManager.getConnection("jdbc:mysql://"
                    + servidor + baseDeDatos, usuario, clave);
        } catch (Exception e) {
            System.out.println("Catch del metodo conectarse");
        }
        return connection;
    }

    public ResultSet consultaSql(String busqueda) {
        try {
            preparedStatement = conectarse().prepareStatement(busqueda);
            resulset = preparedStatement.executeQuery();
            resultSetMetaData = resulset.getMetaData();
        } catch (Exception e) {
            System.out.println("LALALALA Metodo consultaSql");
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, e);
        }
        return resulset;
    }    
}
