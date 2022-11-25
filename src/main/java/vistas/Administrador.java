package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class Administrador extends JFrame implements IVista {

    private JPanel contentPane;
    private JTextField tituloText;
    private JPanel titulo;
    private JPanel principal;
    private JButton gestionarOperariosButton;
    private JPanel gestionarOperariosPanel;
    private JPanel gestionarMozosPanel;
    private JButton gestionarMozosButton;
    private JPanel gestionarMesasPanel;
    private JButton gestionarMesasButton;
    private JPanel gestionarProductosPanel;
    private JButton gestionarProductosButton;
    private JPanel gestionarNegocioPanel;
    private JButton gestionarNegocioButton;
    private JPanel cerrarSesionPanel;
    private JButton cerrarSesionButton;


    public Administrador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 1000);
        setSize(600, 400);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        titulo = new JPanel();
        contentPane.add(titulo, BorderLayout.NORTH);

        tituloText = new JTextField();
        tituloText.setEditable(false);
        tituloText.setBackground(SystemColor.control);
        tituloText.setBorder(null);
        tituloText.setHorizontalAlignment(SwingConstants.CENTER);
        tituloText.setFont(new Font("Dialog", Font.BOLD, 32));
        tituloText.setText("Administrador");
        titulo.add(tituloText);
        tituloText.setColumns(10);

        principal = new JPanel();
        contentPane.add(principal, BorderLayout.CENTER);
        principal.setLayout(new GridLayout(6, 1, 0, 0));

        gestionarOperariosPanel = new JPanel();
        principal.add(gestionarOperariosPanel);

        gestionarOperariosButton = new JButton("Gestionar Operarios");
        gestionarOperariosPanel.add(gestionarOperariosButton);
        gestionarOperariosButton.setActionCommand("gestionarOperarios");

        gestionarMozosPanel = new JPanel();
        principal.add(gestionarMozosPanel);

        gestionarMozosButton = new JButton("Gestionar Mozos");
        gestionarMozosButton.setActionCommand("gestionarMozos");
        gestionarMozosPanel.add(gestionarMozosButton);

        gestionarMesasPanel = new JPanel();
        principal.add(gestionarMesasPanel);

        gestionarMesasButton = new JButton("Gestionar Mesas");
        gestionarMesasButton.setActionCommand("gestionarMesas");
        gestionarMesasPanel.add(gestionarMesasButton);

        gestionarProductosPanel = new JPanel();
        principal.add(gestionarProductosPanel);

        gestionarProductosButton = new JButton("Gestionar Productos");
        gestionarProductosButton.setActionCommand("gestionarProductos");
        gestionarProductosPanel.add(gestionarProductosButton);

        gestionarNegocioPanel = new JPanel();
        principal.add(gestionarNegocioPanel);

        gestionarNegocioButton = new JButton("Gestionar Negocio");
        gestionarNegocioButton.setActionCommand("gestionarNegocio");
        gestionarNegocioPanel.add(gestionarNegocioButton);

        cerrarSesionPanel = new JPanel();
        principal.add(cerrarSesionPanel);

        cerrarSesionButton = new JButton("Cerrar Sesion");
        cerrarSesionButton.setActionCommand("cerrarSesion");
        cerrarSesionPanel.add(cerrarSesionButton);

        setVisible(true);
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        gestionarOperariosButton.addActionListener(actionListener);
        gestionarMozosButton.addActionListener(actionListener);
        gestionarMesasButton.addActionListener(actionListener);
        gestionarProductosButton.addActionListener(actionListener);
    }

    @Override
    public void setWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }
}