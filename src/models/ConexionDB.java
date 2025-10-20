package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Datos de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/tracker_pedidos";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection conexion = null;
    private ConexionDB() { }

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("¡Conexión a la base de datos exitosa!");
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
                throw new RuntimeException("No se pudo establecer la conexión a la BD", e);
            }
        }

        return conexion;
    }
}