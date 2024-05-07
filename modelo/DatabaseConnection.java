package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nombreDeTuBaseDeDatos"; // Ajusta el puerto si es necesario y el nombre de la base de datos
    private static final String USER = "tuUsuario";  // Reemplaza con tu usuario
    private static final String PASSWORD = "tuContraseña";  // Reemplaza con tu contraseña
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Esto es el driver de JDBC para MySQL

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER); // Asegura que el driver JDBC está cargado.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error conectando a la base de datos", e);
        }
    }
}
