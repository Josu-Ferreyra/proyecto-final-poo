package controllers;

import models.dao.UsuarioDAO;
import models.pojo.Usuario;
import views.VistaAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorAdmin implements ActionListener {
    private final VistaAdmin vista;
    private final UsuarioDAO modelo;

    public ControladorAdmin(VistaAdmin vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        // Registrar el listener para el botón
        this.vista.btnCrearVendedor.addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
        cargarVendedores();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCrearVendedor) {
            crearNuevoVendedor();
        }
    }

    private void cargarVendedores() {
        List<Usuario> vendedores = modelo.obtenerTodosLosVendedores();
        // Limpiar tabla antes de cargar
        vista.modeloTabla.setRowCount(0);

        for (Usuario vendedor : vendedores) {
            Object[] fila = {
                    vendedor.getId(),
                    vendedor.getNombre_usuario(),
                    vendedor.getNombre(),
                    vendedor.getApellido()
            };
            vista.modeloTabla.addRow(fila);
        }
    }

    private void crearNuevoVendedor() {
        String nombreUsuario = vista.txtNombreUsuario.getText();
        String contrasena = new String(vista.txtContrasena.getPassword());
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoVendedor = new Usuario();
        nuevoVendedor.setNombre_usuario(nombreUsuario);
        nuevoVendedor.setContrasena(contrasena);
        nuevoVendedor.setNombre(nombre);
        nuevoVendedor.setApellido(apellido);

        if (modelo.crearVendedor(nuevoVendedor)) {
            JOptionPane.showMessageDialog(vista, "Vendedor creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cargarVendedores(); // Recargar la tabla para mostrar el nuevo vendedor
        } else {
            JOptionPane.showMessageDialog(vista, "Error al crear el vendedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        vista.txtNombreUsuario.setText("");
        vista.txtContrasena.setText("");
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
    }
}
