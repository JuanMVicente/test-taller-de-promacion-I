package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class Login extends JFrame implements IVista {

    private JPanel contentPane;
    private JPanel principal;
    private JPanel titulo;
    private JTextField tituloText;
    private JTextField textField;
    private JPasswordField passwordField;
    private JRadioButton mostrarContraseniaBoton;
    private JButton iniciarSesionButton;


    private char defaultChar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 1000);
        setSize(600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel titulo = new JPanel();
        contentPane.add(titulo, BorderLayout.NORTH);
        titulo.setLayout(new BorderLayout(0, 0));

        JPanel tituloTextPan = new JPanel();
        titulo.add(tituloTextPan, BorderLayout.CENTER);

        tituloText = new JTextField();
        tituloText.setBorder(null);
        tituloText.setHorizontalAlignment(SwingConstants.CENTER);
        tituloText.setFont(new Font("Dialog", Font.BOLD, 32));
        tituloText.setText("Iniciar Sesión");
        tituloText.setEditable(false);
        tituloTextPan.add(tituloText);
        tituloText.setColumns(10);

        JPanel principal = new JPanel();
        contentPane.add(principal, BorderLayout.CENTER);
        principal.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel usuario = new JPanel();
        principal.add(usuario);

        JPanel usuarioLabelPan = new JPanel();

        JLabel usuarioLabel = new JLabel("Nombre de Usuario");
        usuarioLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        usuarioLabelPan.add(usuarioLabel);

        JPanel usuarioTextPan = new JPanel();

        textField = new JTextField();
        textField.setColumns(10);
        usuarioTextPan.add(textField);
        GroupLayout gl_usuario = new GroupLayout(usuario);
        gl_usuario.setHorizontalGroup(
                gl_usuario.createParallelGroup(Alignment.LEADING)
                        .addComponent(usuarioLabelPan, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
                        .addComponent(usuarioTextPan, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
        );
        gl_usuario.setVerticalGroup(
                gl_usuario.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_usuario.createSequentialGroup()
                                .addComponent(usuarioLabelPan, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addComponent(usuarioTextPan, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
        );
        usuario.setLayout(gl_usuario);

        JPanel contrasenia = new JPanel();
        principal.add(contrasenia);
        contrasenia.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel contraseniaLabelPan = new JPanel();
        contrasenia.add(contraseniaLabelPan);

        JLabel contraseniaLabel = new JLabel("Contraseña");
        contraseniaLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        contraseniaLabelPan.add(contraseniaLabel);

        JPanel contraseniaTextPan = new JPanel();
        contrasenia.add(contraseniaTextPan);

        passwordField = new JPasswordField();
        this.defaultChar = this.passwordField.getEchoChar();
        passwordField.setColumns(10);
        contraseniaTextPan.add(passwordField);

        mostrarContraseniaBoton = new JRadioButton("");
        mostrarContraseniaBoton.setActionCommand("mostrarContrasenia");
        contraseniaTextPan.add(mostrarContraseniaBoton);

        JPanel botones = new JPanel();
        contentPane.add(botones, BorderLayout.SOUTH);

        JButton iniciarSesionButton = new JButton("Iniciar Sesion");
        iniciarSesionButton.setActionCommand("iniciarSesion");
        botones.add(iniciarSesionButton);

        setVisible(true);
    }


    public void intercambiarContrasenia() {
        boolean estado = mostrarContraseniaBoton.isSelected();

        if (estado) {
            this.passwordField.setEchoChar((char) 0);
        } else {
            this.passwordField.setEchoChar(this.defaultChar);
        }
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        iniciarSesionButton.addActionListener(actionListener);
        mostrarContraseniaBoton.addActionListener(actionListener);
    }

    @Override
    public void setWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    public String getUsuario() {
        return textField.getText();
    }

    public String getContrasenia() {
        return new String(passwordField.getPassword());
    }
}
