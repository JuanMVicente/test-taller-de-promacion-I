package vistas;

import modelos.Operario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;

public class GestionarOperarios extends JFrame implements IVista {

    private JPanel contentPane;
    private JTextField tituloText;
    private JPanel titulo;
    private JPanel principal;
    private JList operariosList;
    private JPanel operariosPanel;
    private JButton eliminarOperarioButton;
    private JPanel eliminarOperarioPanel;
    private JPanel crearOperarioPanel;
    private JButton crearOperarioButton;
    private JPanel volverPanel;
    private JButton volverButton;


    public GestionarOperarios() {
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
        tituloText.setText("Gestionar Operarios");
        titulo.add(tituloText);
        tituloText.setColumns(12);

        principal = new JPanel();
        contentPane.add(principal, BorderLayout.CENTER);
        principal.setLayout(new GridLayout(4, 1, 0, 0));

        operariosPanel = new JPanel();
        principal.add(operariosPanel);

        operariosList = new JList();
        operariosPanel.add(operariosList);

        eliminarOperarioPanel = new JPanel();
        principal.add(eliminarOperarioPanel);

        eliminarOperarioButton = new JButton("Eliminar Operario Seleccionado");
        eliminarOperarioButton.setActionCommand("eliminarOperario");
        eliminarOperarioPanel.add(eliminarOperarioButton);

        crearOperarioPanel = new JPanel();
        principal.add(crearOperarioPanel);

        crearOperarioButton = new JButton("Crear Operario");
        crearOperarioButton.setActionCommand("crearOperario");
        crearOperarioPanel.add(crearOperarioButton);

        volverPanel = new JPanel();
        principal.add(volverPanel);

        volverButton = new JButton("Volver");
        volverButton.setActionCommand("volver");
        volverPanel.add(volverButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarOperarios frame = new GestionarOperarios();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Operario getOperarioSeleccionado() {
        return (Operario) operariosList.getSelectedValue();
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        eliminarOperarioButton.addActionListener(actionListener);
        crearOperarioButton.addActionListener(actionListener);
        volverButton.addActionListener(actionListener);
    }

    @Override
    public void setWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    public void setListaOperarios(List<Operario> operarios) {
        DefaultListModel<Operario> listModel = new DefaultListModel<>();
        this.operariosList = new JList<>(listModel);
        this.operariosList.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        this.operariosPanel.add(operariosList, BorderLayout.CENTER);

        for (Operario operario : operarios) {
            listModel.addElement(operario);
        }
    }

}