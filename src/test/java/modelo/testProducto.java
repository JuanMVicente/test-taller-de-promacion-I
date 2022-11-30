package modelo;

import modelos.Producto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testProducto {

    private Producto prod;

    @Before
    public void setup(){
        prod = new Producto("Nombre", 100, 200, 50);
    }

    @After
    public void teardown(){
        prod = null;
    }

    @Test
    public void testConstructor(){
        String nombre = "NombreProd";
        double precioCosto = 150;
        double precioVenta = 200;
        int stock = 25;
        Producto nuevo = new Producto(nombre,precioCosto, precioVenta, stock);
        Assert.assertEquals("No se asigno correctamente el nombre", nombre, nuevo.getNombre());
        Assert.assertEquals("No se asigno correctamente el precio de costo", precioCosto, nuevo.getPrecioCosto(), 0.5);
        Assert.assertEquals("No se asigno correctamente el precio de venta", precioVenta, nuevo.getPrecioVenta(), 0.5);
        Assert.assertEquals("No se asigno correctamente el stock", stock, nuevo.getStock());
    }

    @Test
    public void testAumentaStock(){
        int cantidad = 30;
        int oldStock = prod.getStock();
        prod.incrementarStock(cantidad);
        Assert.assertEquals("No se incremento correctamente el stock", oldStock + cantidad, prod.getStock());
    }

    @Test
    public void testReduceStock(){
        int cantidad = 10;
        int oldStock = prod.getStock();
        prod.decrementarStock(cantidad);
        Assert.assertEquals("No se decremento correctamente el stock", oldStock - cantidad, prod.getStock());

    }

    @Test
    public void testDefinePrecioCosto(){
        double costo = 175;
        prod.cambiarPrecioCosto(costo);
        Assert.assertEquals("No se asigno correctamente el precio costo", costo, prod.getPrecioCosto(), 0.5);
    }

    @Test
    public void testDefinePrecioVenta(){
        double venta = 250;
        prod.cambiarPrecioVenta(venta);
        Assert.assertEquals("No se asigno correctamente el precio costo", venta, prod.getPrecioVenta(), 0.5);
    }
}
