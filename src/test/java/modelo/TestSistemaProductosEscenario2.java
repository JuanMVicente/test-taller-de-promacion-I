package modelo;

import excepciones.*;
import modelos.Mesa;
import modelos.Pedido;
import modelos.Producto;
import modelos.Sistema;
import modelos.enums.ModoOperacion;
import org.junit.*;

public class TestSistemaProductosEscenario2 {
    private Sistema sistema;
    private Producto prod;

    @BeforeClass
    public void setup(){
        try {
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException ignored) {
        }
        sistema = Sistema.getInstancia();
        sistema.setModoOperacion(ModoOperacion.OPERARIO);
        prod = new Producto("Plato", 150, 200, 30);
    }

    @AfterClass
    public void tearDown(){
        sistema = null;
        prod = null;
    }

    @Test
    public void agregarProductoNuevo(){
        try {
            sistema.agregarProducto(prod);
            Assert.fail("No se emitio OperacionNoAutorizadaException");
        } catch (ProductoExistenteException e) {
            Assert.fail("Hubo un erro al agregar el producto");
        } catch (OperacionNoAutorizadaException e) {

        }
    }

    @Test
    public void agregarProductoExistente(){
        try {
            sistema.agregarProducto(prod);
            sistema.agregarProducto(prod);
            Assert.fail("No se emitio ninguna excepcion");
        } catch (ProductoExistenteException | OperacionNoAutorizadaException e) {

        }
    }


    @Test
    public void eliminarProductoExistente(){
        try{
            sistema.agregarProducto(prod);
            sistema.eliminarProducto(prod);
            Assert.fail("No emitio correctamente las excepciones");
        } catch (ProductoExistenteException | ProductoEnComandaException | ProductoInexistenteException e) {
            Assert.fail("Error al agregar el producto");
        }catch (OperacionNoAutorizadaException e) {

        }
    }

    @Test
    public void eliminarProductoNoExistente(){
        try {
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio las excepciones");
        } catch (ProductoInexistenteException | OperacionNoAutorizadaException e) {

        } catch (ProductoEnComandaException e) {
            Assert.fail("Error escenario mal configurado");
        }
    }

    @Test
    public void eliminarProductoEnComanda(){
        try{
            sistema.agregarProducto(prod);
            Pedido ped = new Pedido(prod,5);
            Mesa mesa = new Mesa(3,3);
            sistema.agregarMesa(mesa);
            sistema.crearComanda(mesa);
            sistema.getComandas().get(mesa).agregarPedido(ped);
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio las excepciones correspondientes");
        } catch (ProductoEnComandaException | ProductoInexistenteException e) {

        } catch (ProductoExistenteException | MesaOcupadaException |
                 MesaRepetidaException | MesaInexistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al crear el escenario");
        }
    }
}
