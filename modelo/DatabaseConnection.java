package modelo;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://192.168.56.103:3306/nba";
    private static final String USER = "perepi";
    private static final String PASSWORD = "pastanaga";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error conectando a la base de datos", e);
        }
    }
}
