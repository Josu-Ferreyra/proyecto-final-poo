package views;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    // Paleta de Colores
    private final Color COLOR_PRINCIPAL = Color.decode("#00a878");
    private final Color COLOR_CLARO = Color.decode("#d8f1a0");
    private final Color COLOR_TEXTO = Color.decode("#333333");

    // Componentes de la UI
    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    public JButton btnIngresar;

    public VistaLogin() {
        // --- CONFIGURACIÓN BÁSICA DE LA VENTANA ---
        setTitle("Tracker de Pedidos - Inicio de Sesión");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setResizable(false);
        
        // --- PANEL PRINCIPAL Y LAYOUT ---
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(COLOR_CLARO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(COLOR_PRINCIPAL);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.weighty = 0.5; // Espacio vertical
        panelPrincipal.add(lblTitulo, gbc);
        
        // --- ETIQUETA Y CAMPO DE TEXTO: USUARIO ---
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(COLOR_TEXTO);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        panelPrincipal.add(lblUsuario, gbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(txtUsuario, gbc);

        // --- ETIQUETA Y CAMPO DE TEXTO: CONTRASEÑA ---
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        lblContrasena.setForeground(COLOR_TEXTO);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(txtContrasena, gbc);

        // --- BOTÓN DE INGRESAR ---
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 18));
        btnIngresar.setBackground(COLOR_PRINCIPAL);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0.5; // Espacio vertical
        panelPrincipal.add(btnIngresar, gbc);

        // Añadir el panel principal al frame
        this.add(panelPrincipal);
    }
}
