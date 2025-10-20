package controllers;

import models.dao.ClienteDAO;
import models.dao.ProductoDAO;
import models.pojo.Cliente;
import models.pojo.Producto;
import models.pojo.Usuario;
import views.VistaVendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVendedor implements ActionListener {
    private final VistaVendedor vista;
    private final Usuario vendedor; // El usuario que ha iniciado sesión
    private final ClienteDAO clienteDAO;
    private final ProductoDAO productoDAO;

    public ControladorVendedor(VistaVendedor vista, Usuario vendedor, ClienteDAO clienteDAO, ProductoDAO productoDAO) {
        this.vista = vista;
        this.vendedor = vendedor;
        this.clienteDAO = clienteDAO;
        this.productoDAO = productoDAO;

        // Registrar listeners
        this.vista.btnGuardarCliente.addActionListener(this);
        this.vista.btnGuardarProducto.addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        cargarClientes();
        cargarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarCliente) {
            guardarNuevoCliente();
        } else if (e.getSource() == vista.btnGuardarProducto) {
            guardarNuevoProducto();
        }
    }

    // --- LÓGICA DE CLIENTES ---
    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientesPorVendedor(vendedor.getId());
        vista.modeloTablaClientes.setRowCount(0); // Limpiar tabla
        for (Cliente cliente : clientes) {
            vista.modeloTablaClientes.addRow(new Object[]{
                cliente.getId(), cliente.getNombre(), cliente.getApellido(),
                cliente.getEmail(), cliente.getTelefono()
            });
        }
    }

    private void guardarNuevoCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre(vista.txtClienteNombre.getText());
        cliente.setApellido(vista.txtClienteApellido.getText());
        cliente.setEmail(vista.txtClienteEmail.getText());
        cliente.setTelefono(vista.txtClienteTelefono.getText());
        cliente.setId_vendedor(vendedor.getId()); // Asignamos el ID del vendedor logueado

        if (clienteDAO.crearCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado exitosamente.");
            cargarClientes(); // Recargar la tabla
            limpiarFormularioCliente();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el cliente.");
        }
    }

    private void limpiarFormularioCliente() {
        vista.txtClienteNombre.setText("");
        vista.txtClienteApellido.setText("");
        vista.txtClienteEmail.setText("");
        vista.txtClienteTelefono.setText("");
    }

    // --- LÓGICA DE PRODUCTOS ---
    private void cargarProductos() {
        List<Producto> productos = productoDAO.obtenerProductosPorVendedor(vendedor.getId());
        vista.modeloTablaProductos.setRowCount(0);
        for (Producto producto : productos) {
            vista.modeloTablaProductos.addRow(new Object[]{
                producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion()
            });
        }
    }

    private void guardarNuevoProducto() {
        Producto producto = new Producto();
        producto.setNombre(vista.txtProductoNombre.getText());
        producto.setDescripcion(vista.txtProductoDescripcion.getText());
        producto.setPrecio(Double.parseDouble(vista.txtProductoPrecio.getText()));
        producto.setId_vendedor(vendedor.getId());

        if (productoDAO.crearProducto(producto)) {
            JOptionPane.showMessageDialog(vista, "Producto guardado exitosamente.");
            cargarProductos();
            limpiarFormularioProducto();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el producto.");
        }
    }
    
    private void limpiarFormularioProducto() {
        vista.txtProductoNombre.setText("");
        vista.txtProductoPrecio.setText("");
        vista.txtProductoDescripcion.setText("");
    }
}
