import models.dao.UsuarioDAO;
import models.pojo.Usuario;
import views.VistaLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {
    private final VistaLogin vista;
    private final UsuarioDAO modelo;

    public ControladorLogin(VistaLogin vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnIngresar.addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreUsuario = vista.txtUsuario.getText();
        String contrasena = new String(vista.txtContrasena.getPassword()); // getPassword() devuelve un char[]

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = modelo.validarLogin(nombreUsuario, contrasena);

        if (usuario != null) {
            vista.dispose(); // Cierra la ventana de login
            JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario.getNombre() + "!");

            if (usuario.getId_rol() == 1) { // 1 = Admin
                System.out.println("Iniciando vista de Administrador...");
            } else { // 2 = Vendedor
                System.out.println("Iniciando vista de Vendedor...");
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
}
