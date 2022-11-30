package modelo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ProductoInexistenteException;
import excepciones.SistemaYaInicializadoException;

public class testSistemaPedidos {

    private Pedido pedido;
    private Mesa mesa;
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
        mesa = new Mesa(3,4);
        prod = new Producto("prod1",100,200,30);
        pedido = new Pedido(prod,5);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        pedido = null;
        mesa = null;
    }

    @Test
    public void testAgregarPedido(){
    	sistema.crearComanda(mesa);
    	try{
    		
    		sistema.agregarPedido(mesa,pedido);
    		Assert.assertTrue("No se ha añadido a la colección", sistema.getComandas().containsValue(pedido));
    	}catch(ComandaInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}catch(StockInsuficienteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    	}
    }

    @Test
    public void testAgregarPedidoComandaInexistente(){
    	try{
    		
    		sistema.agregarPedido(mesa,pedido);
    		Assert.fail("No se emitio la excepción correspondiente");
    	}catch(ComandaInexistenteException e) {

    		
    	}catch(StockInsuficienteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    	}

    }

    @Test
    public void testAgregarPedidoStockInsuficiente(){
    	sistema.crearComanda(mesa);
    	Pedido pedido1 = new Pedido(prod,100);
    	try{
    		
    		sistema.agregarPedido(mesa,pedido1);
    		Assert.fail("No se emitio la excepción correspondiente");
    	}catch(ComandaInexistenteException e) {

    		Assert.fail("Se emitio una excepción no correspondida");
    	}catch(StockInsuficienteException e) {
    		
    	}

    }
}
