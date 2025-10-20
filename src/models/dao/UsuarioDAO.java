package models.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import models.ConexionDB;
import models.pojo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                String hashContrasenaDB = rs.getString("password");
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
        String query = "INSERT INTO usuarios (nombre_usuario, password, nombre, apellido, id_rol) VALUES (?, ?, ?, ?, 2)";
        
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
}
