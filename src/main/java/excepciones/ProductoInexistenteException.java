package excepciones;

import modelos.Producto;

public class ProductoInexistenteException extends Exception {
    public ProductoInexistenteException(Producto producto) {
        super("El producto " + producto.getNombre() + " no existe");
    }
}
