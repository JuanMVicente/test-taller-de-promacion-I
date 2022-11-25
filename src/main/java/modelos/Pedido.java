package modelos;

import java.util.Date;

/**
 * Clase que representa un pedido dentro de una comanda. <br>
 * <b>Inv:</b> <br>
 * producto != null <br>
 * cantidad > 0 <br>
 * fecha != null <br>
 */
public class Pedido {
    private Producto producto;
    private int cantidad;
    private Date fecha;

    /**
     * Crea un pedido con un producto y su cantidad. <br>
     * <b>Pre:</b> <br>
     * producto != null <br>
     * cantidad > 0 <br>
     * <b>Post:</b> <br>
     * Se crea un nuevo pedido con el producto y la cantidad ingresados. <br>
     * La fecha del pedido es la actual. <br>
     *
     * @param producto Producto del pedido.
     * @param cantidad Cantidad de unidades del producto pedidas.
     */
    public Pedido(Producto producto, int cantidad) {
        assert producto != null : "El producto no puede ser nulo";
        assert cantidad > 0 : "La cantidad debe ser mayor a 0";

        this.producto = producto;
        this.cantidad = cantidad;
        fecha = new Date();

        assert this.producto == producto : "El producto no se asignó correctamente";
        assert this.cantidad == cantidad : "La cantidad no se asignó correctamente";
        // TODO validar la fecha.
        verificarInvariantes();
    }

    private void verificarInvariantes() {
        assert producto != null : "El producto no puede ser nulo";
        assert cantidad > 0 : "La cantidad debe ser mayor a 0";
        assert fecha != null : "La fecha no puede ser nula";
    }
}
