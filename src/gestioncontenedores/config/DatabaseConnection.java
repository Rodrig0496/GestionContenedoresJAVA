/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rc202
 */
public class DatabaseConnection {
    // Instancia única (Patrón Singleton)
    private static DatabaseConnection instance;
    private Connection connection;

    // AJUSTA ESTOS DATOS A TU SQL SERVER
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=contenedorDB;encrypt=true;trustServerCertificate=true;";
    private final String USER = "sa";
    private final String PASS = "123456"; // Tu contraseña real

    private DatabaseConnection() {
        try {
            // Cargar driver explícitamente (buena práctica en Ant/Web)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error conectando a la BD: " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        try {
            if (instance == null || instance.connection.isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            instance = new DatabaseConnection(); // Reintento forzoso
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
