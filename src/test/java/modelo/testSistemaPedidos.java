package modelo;

import excepciones.SistemaYaInicializadoException;
import modelos.Mesa;
import modelos.Pedido;
import modelos.PromocionTemporal;
import modelos.Sistema;
import modelos.enums.Dia;
import modelos.enums.FormaPago;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testSistemaPedidos {

    private Pedido pedido;
    private Mesa mesa;
    private Sistema sistema;

    @Before
    public void setup(){
        try{
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException e) {
            Assert.fail("Error al inicializar el escenario");
        }
        sistema = Sistema.getInstancia();
        mesa = new Mesa(3,4);
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
        Assert.fail();
    }

    @Test
    public void testAgregarPedidoComandaInexistente(){
        Assert.fail();

    }

    @Test
    public void testAgregarPedidoStockInsuficiente(){
        Assert.fail();

    }
}
