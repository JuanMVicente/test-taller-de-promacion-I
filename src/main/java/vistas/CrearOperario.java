package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class CrearOperario extends JFrame implements IVista {

    private JPanel contentPane;
    private JPanel titulo;
    private JTextField crearOperarioText;
    private JPanel principal;
    private JPanel nombre;
    private JLabel nombreLabel;
    private JTextField nombreText;
    private JPanel nombreLabelPanel;
    private JPanel nombreTextPanel;
    private JPanel apellido;
    private JPanel apellidoLabelPanel;
    private JLabel apellidoLabel;
    private JPanel apellidoTextPanel;
    private JTextField apellidoText;
    private JPanel usuario;
    private JPanel usuarioLabelPanel;
    private JLabel usuarioLabel;
    private JPanel usuarioTextPanel;
    private JTextField usuarioText;
    private JPanel contrasenia;
    private JPanel contraseniaLabelPan;
    private JLabel contraseniaLabel;
    private JPanel contraseniaTextPan;
    private JPasswordField passwordField;
    private JRadioButton mostrarContraseniaBoton;
    private JPanel botones;
    private JButton crearOperarioButton;
    private JButton volverButton;
    private JPanel crearOperarioPanel;
    private JPanel volverPanel;

    private char defaultChar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrearOperario frame = new CrearOperario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CrearOperario() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 1000);
        setSize(600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        titulo = new JPanel();
        contentPane.add(titulo, BorderLayout.NORTH);
        titulo.setLayout(new BorderLayout(0, 0));

        crearOperarioText = new JTextField();
        crearOperarioText.setText("Crear Operario");
        crearOperarioText.setHorizontalAlignment(SwingConstants.CENTER);
        crearOperarioText.setFont(new Font("Dialog", Font.BOLD, 32));
        crearOperarioText.setEditable(false);
        crearOperarioText.setColumns(10);
        crearOperarioText.setBorder(null);
        titulo.add(crearOperarioText, BorderLayout.NORTH);

        principal = new JPanel();
        contentPane.add(principal, BorderLayout.CENTER);
        principal.setLayout(new GridLayout(4, 3, 0, 0));

        nombre = new JPanel();
        principal.add(nombre);
        nombre.setLayout(new GridLayout(2, 1, 0, 0));

        nombreLabelPanel = new JPanel();
        nombre.add(nombreLabelPanel);

        nombreLabel = new JLabel("Nombre");
        nombreLabelPanel.add(nombreLabel);
        nombreLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);

        nombreTextPanel = new JPanel();
        nombre.add(nombreTextPanel);

        nombreText = new JTextField();
        nombreTextPanel.add(nombreText);
        nombreText.setColumns(10);

        apellido = new JPanel();
        principal.add(apellido);
        apellido.setLayout(new GridLayout(2, 1, 0, 0));

        apellidoLabelPanel = new JPanel();
        apellido.add(apellidoLabelPanel);

        apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        apellidoLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        apellidoLabelPanel.add(apellidoLabel);

        apellidoTextPanel = new JPanel();
        apellido.add(apellidoTextPanel);

        apellidoText = new JTextField();
        apellidoText.setColumns(10);
        apellidoTextPanel.add(apellidoText);

        usuario = new JPanel();
        principal.add(usuario);
        usuario.setLayout(new GridLayout(2, 1, 0, 0));

        usuarioLabelPanel = new JPanel();
        usuario.add(usuarioLabelPanel);

        usuarioLabel = new JLabel("Nombre de Usuario");
        usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);
        usuarioLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        usuarioLabelPanel.add(usuarioLabel);

        usuarioTextPanel = new JPanel();
        usuario.add(usuarioTextPanel);

        usuarioText = new JTextField();
        usuarioText.setColumns(10);
        usuarioTextPanel.add(usuarioText);

        contrasenia = new JPanel();
        principal.add(contrasenia);
        contrasenia.setLayout(new GridLayout(2, 1, 0, 0));

        contraseniaLabelPan = new JPanel();
        contrasenia.add(contraseniaLabelPan);

        contraseniaLabel = new JLabel("Contraseña");
        contraseniaLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        contraseniaLabelPan.add(contraseniaLabel);

        contraseniaTextPan = new JPanel();
        contrasenia.add(contraseniaTextPan);

        passwordField = new JPasswordField();
        this.defaultChar = this.passwordField.getEchoChar();
        passwordField.setColumns(10);
        contraseniaTextPan.add(passwordField);

        mostrarContraseniaBoton = new JRadioButton("");
        mostrarContraseniaBoton.setActionCommand("mostrarContrasenia");
        contraseniaTextPan.add(mostrarContraseniaBoton);

        botones = new JPanel();
        contentPane.add(botones, BorderLayout.SOUTH);

        botones.setLayout(new GridLayout(2, 1, 0, 0));

        crearOperarioPanel = new JPanel();
        botones.add(crearOperarioPanel);

        crearOperarioButton = new JButton("Crear Operario");
        crearOperarioPanel.add(crearOperarioButton);
        crearOperarioButton.setActionCommand("crearOperario");

        volverPanel = new JPanel();
        botones.add(volverPanel);

        volverButton = new JButton("Volver");
        volverButton.setActionCommand("volver");
        volverPanel.add(volverButton);


        setVisible(true);
    }


    @Override
    public void setActionListener(ActionListener actionListener) {
        crearOperarioButton.addActionListener(actionListener);
        mostrarContraseniaBoton.addActionListener(actionListener);
    }

    @Override
    public void setWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    public void intercambiarContrasenia() {
        boolean estado = mostrarContraseniaBoton.isSelected();

        if (estado) {
            this.passwordField.setEchoChar((char) 0);
        } else {
            this.passwordField.setEchoChar(this.defaultChar);
        }
    }

    public String getNombre() {
        return nombreText.getText();
    }

    public String getApellido() {
        return apellidoText.getText();
    }

    public String getUsuario() {
        return usuarioText.getText();
    }

    public String getContrasenia() {
        return String.valueOf(passwordField.getPassword());
    }
}

