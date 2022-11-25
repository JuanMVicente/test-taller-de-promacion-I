package excepciones;

import modelos.Comanda;
import modelos.Producto;

public class ProductoEnComandaException extends Exception {
    private Producto producto;
    private Comanda comanda;

    public ProductoEnComandaException(Producto producto, Comanda comanda) {
        super("El producto está asociado a una comanda");
        this.producto = producto;
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public Comanda getComanda() {
        return comanda;
    }
}
