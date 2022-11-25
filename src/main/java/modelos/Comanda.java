package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comanda {
    private Date fecha;
    private List<Pedido> pedidos;

    public Comanda() {
        fecha = new Date();
        pedidos = new ArrayList<Pedido>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}
