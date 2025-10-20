package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaVendedor extends JFrame {
    // Paleta de Colores
    private final Color COLOR_PRINCIPAL = Color.decode("#00a878");

    // Componentes Comunes
    private JTabbedPane tabbedPane;

    // Pestaña Clientes
    public JTextField txtClienteNombre, txtClienteApellido, txtClienteEmail, txtClienteTelefono;
    public JButton btnGuardarCliente;
    public JTable tablaClientes;
    public DefaultTableModel modeloTablaClientes;

    // Pestaña Productos
    public JTextField txtProductoNombre, txtProductoPrecio;
    public JTextArea txtProductoDescripcion;
    public JButton btnGuardarProducto;
    public JTable tablaProductos;
    public DefaultTableModel modeloTablaProductos;

    public VistaVendedor(String nombreVendedor) {
        setTitle("Panel de Vendedor - ¡Hola, " + nombreVendedor + "!");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Crear y añadir las pestañas
        tabbedPane.addTab("Gestionar Pedidos", crearPanelPedidos());
        tabbedPane.addTab("Gestionar Clientes", crearPanelClientes());
        tabbedPane.addTab("Gestionar Productos", crearPanelProductos());
        
        this.add(tabbedPane);
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario de creación
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; txtClienteNombre = new JTextField(20); formPanel.add(txtClienteNombre, gbc);
        gbc.gridy = 1; gbc.gridx = 0; formPanel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; txtClienteApellido = new JTextField(20); formPanel.add(txtClienteApellido, gbc);
        gbc.gridy = 2; gbc.gridx = 0; formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; txtClienteEmail = new JTextField(20); formPanel.add(txtClienteEmail, gbc);
        gbc.gridy = 3; gbc.gridx = 0; formPanel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1; txtClienteTelefono = new JTextField(20); formPanel.add(txtClienteTelefono, gbc);
        
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnGuardarCliente = new JButton("Guardar Cliente");
        btnGuardarCliente.setBackground(COLOR_PRINCIPAL);
        btnGuardarCliente.setForeground(Color.WHITE);
        formPanel.add(btnGuardarCliente, gbc);
        
        panel.add(formPanel, BorderLayout.WEST);

        // Tabla de clientes
        String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Teléfono"};
        modeloTablaClientes = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaClientes = new JTable(modeloTablaClientes);
        panel.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario de creación
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Producto"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; txtProductoNombre = new JTextField(20); formPanel.add(txtProductoNombre, gbc);
        gbc.gridy = 1; gbc.gridx = 0; formPanel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; txtProductoPrecio = new JTextField(20); formPanel.add(txtProductoPrecio, gbc);
        gbc.gridy = 2; gbc.gridx = 0; formPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; txtProductoDescripcion = new JTextArea(4, 20);
        formPanel.add(new JScrollPane(txtProductoDescripcion), gbc);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnGuardarProducto = new JButton("Guardar Producto");
        btnGuardarProducto.setBackground(COLOR_PRINCIPAL);
        btnGuardarProducto.setForeground(Color.WHITE);
        formPanel.add(btnGuardarProducto, gbc);
        
        panel.add(formPanel, BorderLayout.WEST);
        
        // Tabla de productos
        String[] columnas = {"ID", "Nombre", "Precio", "Descripción"};
        modeloTablaProductos = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaProductos = new JTable(modeloTablaProductos);
        panel.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelPedidos() {
        // Por ahora, un panel simple. Lo desarrollaremos en el siguiente paso.
        JPanel panel = new JPanel();
        panel.add(new JLabel("El módulo de gestión de pedidos se implementará aquí."));
        return panel;
    }
}
