package modelo;

import excepciones.*;
import modelos.*;
import modelos.enums.ModoOperacion;
import org.junit.*;

public class TestSistemaProductosEscenario1 {

    private Sistema sistema;
    private Producto prod;

    @BeforeClass
    public void setup(){
        try {
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException ignored) {
        }
        sistema = Sistema.getInstancia();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
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
            Assert.assertTrue("No se agrego correctamente el producto", sistema.getProductos().contains(prod));
        } catch (ProductoExistenteException e) {
            Assert.fail("Hubo un erro al agregar el producto");
        } catch (OperacionNoAutorizadaException e){
            Assert.fail("Error escenario mal configurado");
        }
    }

    @Test
    public void agregarProductoExistente(){
        try {
            sistema.agregarProducto(prod);
            sistema.agregarProducto(prod);
            Assert.fail("No se emitio excepcion de producto existente");
        } catch (ProductoExistenteException e) {

        } catch (OperacionNoAutorizadaException e){
            Assert.fail("Error escenario mal configurado");
        }
    }

    @Test
    public void eliminarProductoExistente(){
        try{
            sistema.agregarProducto(prod);
            sistema.eliminarProducto(prod);
            Assert.assertFalse("No se elimino correctamente el producto", sistema.getProductos().contains(prod));
        } catch (ProductoExistenteException | ProductoEnComandaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al agregar el producto");
        }catch (ProductoInexistenteException e) {
            Assert.fail("No se agrego correctamente el producto");
        }
    }

    @Test
    public void eliminarProductoNoExistente(){
        try {
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio la excepcion ProductoInexistenteException");
        } catch (ProductoInexistenteException e) {

        } catch (ProductoEnComandaException | OperacionNoAutorizadaException e) {
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
            Assert.fail("No se emitio excepcion ProductoEnComanda");
        } catch (ProductoEnComandaException e) {

        } catch (ProductoExistenteException | MesaOcupadaException |
                 MesaRepetidaException | MesaInexistenteException | OperacionNoAutorizadaException | ProductoInexistenteException e) {
            Assert.fail("Error al crear el escenario");
        }
    }
}
