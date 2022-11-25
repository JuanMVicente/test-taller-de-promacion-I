package excepciones;

import modelos.Pedido;

public class StockInsuficienteException extends Exception {
    private Pedido pedido;

    public StockInsuficienteException(Pedido pedido) {
        super("No hay stock suficiente");
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
