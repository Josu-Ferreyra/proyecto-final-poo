package models.dao;

import models.ConexionDB;
import models.pojo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public boolean crearCliente(Cliente cliente) {
        String query = "INSERT INTO clientes (nombre, apellido, email, telefono, id_vendedor) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setString(1, cliente.getNombre());
            preparacionQuery.setString(2, cliente.getApellido());
            preparacionQuery.setString(3, cliente.getEmail());
            preparacionQuery.setString(4, cliente.getTelefono());
            preparacionQuery.setInt(5, cliente.getId_vendedor());

            int filasAfectadas = preparacionQuery.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear el cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> obtenerClientesPorVendedor(int idVendedor) {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes WHERE id_vendedor = ?";
        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setInt(1, idVendedor);
            ResultSet rs = preparacionQuery.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setId_vendedor(rs.getInt("id_vendedor"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }
}
