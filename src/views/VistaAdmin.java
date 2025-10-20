package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaAdmin extends JFrame {
    // Paleta de Colores
    private final Color COLOR_PRINCIPAL = Color.decode("#00a878");
    private final Color COLOR_FONDO = Color.decode("#f4f4f4");

    // Componentes de la UI
    public JTextField txtNombreUsuario;
    public JPasswordField txtContrasena;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JButton btnCrearVendedor;
    public JTable tablaVendedores;
    public DefaultTableModel modeloTabla;

    public VistaAdmin() {
        // --- CONFIGURACIÓN DE LA VENTANA ---
        setTitle("Panel de Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // --- PANEL PRINCIPAL CON BORDERLAYOUT ---
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(COLOR_FONDO);

        // --- PANEL DEL FORMULARIO (IZQUIERDA) ---
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Crear Nuevo Vendedor"));
        panelFormulario.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos del formulario
        panelFormulario.add(new JLabel("Nombre de Usuario:"), gbc);
        gbc.gridx = 1;
        txtNombreUsuario = new JTextField(15);
        panelFormulario.add(txtNombreUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtContrasena = new JPasswordField(15);
        panelFormulario.add(txtContrasena, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        txtApellido = new JTextField(15);
        panelFormulario.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCrearVendedor = new JButton("Crear Vendedor");
        btnCrearVendedor.setBackground(COLOR_PRINCIPAL);
        btnCrearVendedor.setForeground(Color.WHITE);
        btnCrearVendedor.setFont(new Font("Arial", Font.BOLD, 14));
        panelFormulario.add(btnCrearVendedor, gbc);

        // --- PANEL DE LA TABLA (CENTRO) ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Vendedores Registrados"));
        
        String[] columnas = {"ID", "Usuario", "Nombre", "Apellido"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        tablaVendedores = new JTable(modeloTabla);
        
        JScrollPane scrollPane = new JScrollPane(tablaVendedores);
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        
        // --- AÑADIR PANELES AL PANEL PRINCIPAL ---
        panelPrincipal.add(panelFormulario, BorderLayout.WEST);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        this.add(panelPrincipal);
    }
}
