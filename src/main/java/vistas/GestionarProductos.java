package vistas;

import modelos.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;

public class GestionarProductos extends JFrame implements IVista {

    private JPanel contentPane;
    private JTextField tituloText;
    private JPanel titulo;
    private JPanel principal;
    private JList productosList;
    private JPanel productosPanel;
    private JButton eliminarProductoButton;
    private JPanel eliminarProductoPanel;
    private JPanel crearProductoPanel;
    private JButton crearProductoButton;
    private JPanel volverPanel;
    private JButton volverButton;


    public GestionarProductos() {
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
        tituloText.setText("Gestionar Productos");
        titulo.add(tituloText);
        tituloText.setColumns(12);

        principal = new JPanel();
        contentPane.add(principal, BorderLayout.CENTER);
        principal.setLayout(new GridLayout(4, 1, 0, 0));

        productosPanel = new JPanel();
        principal.add(productosPanel);

        productosList = new JList();
        productosPanel.add(productosList);

        eliminarProductoPanel = new JPanel();
        principal.add(eliminarProductoPanel);

        eliminarProductoButton = new JButton("Eliminar Producto Seleccionado");
        eliminarProductoButton.setActionCommand("eliminarProducto");
        eliminarProductoPanel.add(eliminarProductoButton);

        crearProductoPanel = new JPanel();
        principal.add(crearProductoPanel);

        crearProductoButton = new JButton("Crear Producto");
        crearProductoButton.setActionCommand("crearProducto");
        crearProductoPanel.add(crearProductoButton);

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
                    GestionarProductos frame = new GestionarProductos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Producto getProductoSeleccionado() {
        return (Producto) productosList.getSelectedValue();
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        eliminarProductoButton.addActionListener(actionListener);
        crearProductoButton.addActionListener(actionListener);
        volverButton.addActionListener(actionListener);
    }

    @Override
    public void setWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    public void setListaOperarios(List<Producto> productos) {
        DefaultListModel<Producto> listModel = new DefaultListModel<>();
        this.productosList = new JList<>(listModel);
        this.productosList.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        this.productosPanel.add(productosList, BorderLayout.CENTER);

        for (Producto producto : productos) {
            listModel.addElement(producto);
        }
    }

}
