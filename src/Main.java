import models.dao.UsuarioDAO;
import views.VistaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Para asegurar que la UI se ejecute en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            VistaLogin vista = new VistaLogin();
            UsuarioDAO modelo = new UsuarioDAO();
            ControladorLogin controlador = new ControladorLogin(vista, modelo);
            controlador.iniciar();
        });
    }
}
