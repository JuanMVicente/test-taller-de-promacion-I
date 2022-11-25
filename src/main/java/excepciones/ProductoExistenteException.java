package excepciones;

import modelos.Producto;

public class ProductoExistenteException extends Exception {
    private Producto producto;

    public ProductoExistenteException(Producto producto) {
        super("El producto ya existe.");
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }
}
