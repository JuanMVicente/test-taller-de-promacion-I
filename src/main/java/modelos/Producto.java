package modelos;

/**
 * Clase que representa a un producto del sistema. <br>
 * <b>Inv:</b> <br>
 * nombre != null <br>
 * nombre != "" <br>
 * precioCosto >= 0 <br>
 * precioVenta >= 0 <br>
 * precioVenta > precioCosto <br>
 * stock >= 0 <br>
 */
public class Producto {
    private int id;
    private String nombre;
    private double precioCosto;
    private double precioVenta;
    private int stock;

    private static int ultimoId = 0;

    /**
     * Constructor de la clase Producto. <br>
     * <b>Pre:</b> <br>
     * nombre != null <br>
     * nombre != "" <br>
     * precioCosto >= 0 <br>
     * precioVenta >= 0 <br>
     * precioVenta > precioCosto <br>
     * stock >= 0 <br>
     * <b>Post:</b> <br>
     * Se crea un nuevo producto con los datos ingresados y un id unico autogenerado. <br>
     *
     * @param nombre      Nombre del producto.
     * @param precioCosto Precio de costo del producto.
     * @param precioVenta Precio de venta del producto.
     * @param stock       Stock del producto.
     */
    public Producto(String nombre, double precioCosto, double precioVenta, int stock) {
        assert nombre != null : "El nombre del producto no puede ser nulo.";
        assert nombre != "" : "El nombre del producto no puede ser vacio.";
        assert precioCosto >= 0 : "El precio de costo del producto no puede ser negativo.";
        assert precioVenta >= 0 : "El precio de venta del producto no puede ser negativo.";
        assert precioVenta > precioCosto : "El precio de venta del producto no puede ser menor o igual al precio de costo.";
        assert stock >= 0 : "El stock del producto no puede ser negativo.";

        this.id = ultimoId;
        ultimoId++;
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stock = stock;

        assert this.id == ultimoId - 1 : "El id del producto no es el esperado.";
        assert this.nombre.equals(nombre) : "El nombre del producto no es el esperado.";
        assert this.precioCosto == precioCosto : "El precio de costo del producto no es el esperado.";
        assert this.precioVenta == precioVenta : "El precio de venta del producto no es el esperado.";
        assert this.stock == stock : "El stock del producto no es el esperado.";
        verificarInvariantes();
    }

    /**
     * Incrementa el stock en una cantidad dada. <br>
     * <b>Pre:</b> <br>
     * cantidad > 0 <br>
     * <b>Post:</b> <br>
     * Se incrementa el stock en la cantidad dada. <br>
     * @param cantidad Cantidad a incrementar.
     */
    public void incrementarStock(int cantidad) {
        assert cantidad > 0 : "La cantidad a incrementar debe ser mayor a 0";

        int stockInicial = this.stock;
        this.stock += cantidad;

        assert this.stock == stockInicial + cantidad : "El stock no se incremento correctamente";
        verificarInvariantes();
    }

    /**
     * Decrementa el stock en una cantidad dada. <br>
     * <b>Pre:</b> <br>
     * cantidad > 0 <br>
     * <b>Post:</b> <br>
     * Se decrementa el stock en la cantidad dada. <br>
     * @param cantidad Cantidad a decrementar.
     */
    public void decrementarStock(int cantidad) {
        assert cantidad > 0 : "La cantidad a decrementar debe ser mayor a 0";

        int stockInicial = this.stock;
        this.stock -= cantidad;

        assert this.stock == stockInicial - cantidad : "El stock no se decremento correctamente";
        verificarInvariantes();
    }

    /**
     * Cambia el precio de venta del producto. <br>
     * <b>Pre:</b> <br>
     * precioVenta > 0 <br>
     * precioVenta > precioCosto <br>
     * <b>Post:</b> <br>
     * Se cambia el precio de venta del producto. <br>
     * @param precioVenta Nuevo precio de venta.
     */
    public void cambiarPrecioVenta(double precioVenta) {
        assert precioVenta > 0 : "El precio de venta debe ser mayor a 0";
        assert precioVenta > this.precioCosto : "El precio de venta debe ser mayor al precio de costo";

        this.precioVenta = precioVenta;

        assert this.precioVenta == precioVenta : "El precio de venta no se cambio correctamente";
        verificarInvariantes();
    }

    public double getPrecioVenta() {
        return this.precioVenta;
    }

    /**
     * Cambia el precio de costo del producto. <br>
     * <b>Pre:</b> <br>
     * precioCosto > 0 <br>
     * precioVenta > precioCosto <br>
     * <b>Post:</b> <br>
     * Se cambia el precio de costo del producto. <br>
     * @param precioCosto Nuevo precio de costo.
     */
    public void cambiarPrecioCosto(double precioCosto) {
        assert precioCosto > 0 : "El precio de costo debe ser mayor a 0";
        assert precioCosto < this.precioVenta : "El precio de costo debe ser menor al precio de venta";

        this.precioCosto = precioCosto;

        assert this.precioCosto == precioCosto : "El precio de costo no se cambio correctamente";
        verificarInvariantes();
    }

    public double getPrecioCosto() {
        return this.precioCosto;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    private void verificarInvariantes() {
        assert this.nombre != null : "El nombre del producto no puede ser nulo.";
        assert this.nombre != "" : "El nombre del producto no puede ser vacio.";
        assert this.precioCosto >= 0 : "El precio de costo del producto no puede ser negativo.";
        assert this.precioVenta >= 0 : "El precio de venta del producto no puede ser negativo.";
        assert this.precioVenta > this.precioCosto : "El precio de venta del producto no puede ser menor o igual al precio de costo.";
        assert this.stock >= 0 : "El stock del producto no puede ser negativo.";
    }
}
