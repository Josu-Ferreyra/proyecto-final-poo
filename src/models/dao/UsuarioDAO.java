package models.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import models.ConexionDB;
import models.pojo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    /**
     * Valida las credenciales de un usuario.
     * @param nombreUsuario El nombre de usuario a verificar.
     * @param contrasena La contraseña en texto plano a verificar.
     * @return Un objeto Usuario si las credenciales son correctas, de lo contrario null.
     */
    public Usuario validarLogin(String nombreUsuario, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        Usuario usuario = null;

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setString(1, nombreUsuario);
            ResultSet rs = preparacionQuery.executeQuery();

            if (rs.next()) {
                String hashContrasenaDB = rs.getString("contrasena");
                BCrypt.Result resultado = BCrypt.verifyer().verify(contrasena.toCharArray(), hashContrasenaDB);

                if (resultado.verified) {
                    usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        null,
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id_rol")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }

        return usuario;
    }

    /**
     * Crea un nuevo usuario con el rol de Vendedor.
     * @param usuario El objeto Usuario con los datos a insertar.
     * @return true si la creación fue exitosa, de lo contrario false.
     */
    public boolean crearVendedor(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre_usuario, contrasena, nombre, apellido, id_rol) VALUES (?, ?, ?, ?, 2)";
        
        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setString(1, usuario.getNombre_usuario());
            String hashContrasena = BCrypt.withDefaults().hashToString(12, usuario.getContrasena().toCharArray());
            preparacionQuery.setString(2, hashContrasena);
            preparacionQuery.setString(3, usuario.getNombre());
            preparacionQuery.setString(4, usuario.getApellido());

            int filasAfectadas = preparacionQuery.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear vendedor: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene una lista de todos los usuarios con el rol de Vendedor.
     * No se incluye la contraseña por seguridad.
     * @return Una lista de objetos Usuario.
     */
    public List<Usuario> obtenerTodosLosVendedores() {
        List<Usuario> vendedores = new ArrayList<>();

        String query = "SELECT id, nombre_usuario, nombre, apellido, id_rol FROM usuarios WHERE id_rol = 2";

        try {
            Connection conexion = ConexionDB.getConexion();
            Statement preparacionQuery = conexion.createStatement();
            ResultSet rs = preparacionQuery.executeQuery(query);

            while (rs.next()) {
                Usuario vendedor = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    null,
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("id_rol")
                );
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de vendedores: " + e.getMessage());
        }

        return vendedores;
    }
}
