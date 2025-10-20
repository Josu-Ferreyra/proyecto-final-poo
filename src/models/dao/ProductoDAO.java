package models.dao;

import models.ConexionDB;
import models.pojo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public boolean crearProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, id_vendedor) VALUES (?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setString(1, producto.getNombre());
            preparacionQuery.setString(2, producto.getDescripcion());
            preparacionQuery.setDouble(3, producto.getPrecio());
            preparacionQuery.setInt(4, producto.getId_vendedor());

            int filasAfectadas = preparacionQuery.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> obtenerProductosPorVendedor(int idVendedor) {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE id_vendedor = ?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement preparacionQuery = conexion.prepareStatement(query);

            preparacionQuery.setInt(1, idVendedor);
            ResultSet rs = preparacionQuery.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setId_vendedor(rs.getInt("id_vendedor"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
}
