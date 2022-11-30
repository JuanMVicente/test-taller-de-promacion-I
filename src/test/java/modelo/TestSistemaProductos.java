package modelo;

import excepciones.*;
import modelos.Mesa;
import modelos.Pedido;
import modelos.Producto;
import modelos.Sistema;
import modelos.enums.ModoOperacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSistemaProductos {

    private Sistema sistema;
    private Producto prod;

    @Before
    public void setup(){
        try{
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException e) {
            Assert.fail("Error al inicializar el escenario");
        }
        sistema = Sistema.getInstancia();
        prod = new Producto("Plato", 150, 200, 30);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        prod = null;
    }

    private void escenario1(){
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    }

    private void escenario2(){
        sistema.setModoOperacion(ModoOperacion.OPERARIO);
    }

    private void agregarProducto(Producto prod){
        ModoOperacion aux = sistema.getModoOperacion();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
        try {
            sistema.agregarProducto(prod);
        } catch (ProductoExistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al preparar escenario");
        }
        sistema.setModoOperacion(aux);
    }

    private void agregarProductoEnComanda(Producto prod){
        try {
            sistema.agregarProducto(prod);
            Pedido ped = new Pedido(prod,5);
            Mesa mesa = new Mesa(3,3);
            sistema.agregarMesa(mesa);
            sistema.crearComanda(mesa);
            sistema.getComandas().get(mesa).agregarPedido(ped);
        } catch (ProductoExistenteException | OperacionNoAutorizadaException | MesaOcupadaException |
                 MesaRepetidaException | MesaInexistenteException e) {
            Assert.fail("Error al preparar escenario");
        }

    }

    @Test
    public void agregarProductoNuevoEscenario1(){
        escenario1();
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
    public void agregarProductoExistenteEscenario1(){
        escenario1();
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
    public void eliminarProductoExistenteEscenario1(){
        escenario1();
        agregarProducto(prod);
        try{
            sistema.eliminarProducto(prod);
            Assert.assertFalse("No se elimino correctamente el producto", sistema.getProductos().contains(prod));
        } catch (ProductoEnComandaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al agregar el producto");
        }catch (ProductoInexistenteException e) {
            Assert.fail("No se agrego correctamente el producto");
        }
    }

    @Test
    public void eliminarProductoNoExistenteEscenario1(){
        escenario1();
        try {
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio la excepcion ProductoInexistenteException");
        } catch (ProductoInexistenteException e) {

        } catch (ProductoEnComandaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error escenario mal configurado");
        }
    }

    @Test
    public void eliminarProductoEnComandaEscenario1(){
        escenario1();
        agregarProductoEnComanda(prod);
        try{
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio excepcion ProductoEnComanda");
        } catch (ProductoEnComandaException e) {

        } catch (ProductoInexistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Excepciones incorrectas emitidas");
        }
    }

    @Test
    public void agregarProductoExistenteEscenario2(){
        escenario2();
        try {
            sistema.agregarProducto(prod);
            sistema.agregarProducto(prod);
            Assert.fail("No se emitio ninguna excepcion");
        } catch (ProductoExistenteException | OperacionNoAutorizadaException e) {

        }
    }

    @Test
    public void eliminarProductoExistenteEscenario2(){
        escenario2();
        agregarProducto(prod);
        try{
            sistema.eliminarProducto(prod);
            Assert.fail("No emitio correctamente las excepciones");
        } catch (ProductoEnComandaException | ProductoInexistenteException e) {
            Assert.fail("Excepciones incorrectas emitidas");
        }catch (OperacionNoAutorizadaException e) {

        }
    }

    @Test
    public void eliminarProductoNoExistenteEscenario2(){
        escenario2();
        try {
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio las excepciones");
        } catch (ProductoInexistenteException | OperacionNoAutorizadaException e) {

        } catch (ProductoEnComandaException e) {
            Assert.fail("Error escenario mal configurado");
        }
    }

    @Test
    public void eliminarProductoEnComandaEscenario2(){
        escenario2();
        agregarProductoEnComanda(prod);
        try{
            sistema.eliminarProducto(prod);
            Assert.fail("No se emitio las excepciones correspondientes");
        } catch (ProductoEnComandaException | ProductoInexistenteException e) {

        } catch (OperacionNoAutorizadaException e) {
            Assert.fail("Error al crear el escenario");
        }
    }

}
