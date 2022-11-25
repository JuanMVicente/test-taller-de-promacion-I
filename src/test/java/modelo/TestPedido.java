package modelo;

import excepciones.MesaInexistenteException;
import modelos.Pedido;
import modelos.Producto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPedido {

    Pedido pedido;
    Producto prod;

    @Before
    public void setUp(){
        prod = new Producto("nombre",20,30,30);
    }

    @After
    public void tearDown(){
        prod = null;
    }

    @Test
    public void testConstructor1(){

        try{
            pedido = new Pedido(prod, 31);
            Assert.fail("No tiro excepcion");
        }catch (Exception e){
            Assert.assertThrows(MesaInexistenteException.class, )
        }

    }

    @Test
    public void testConstructor2(){

        try{
            pedido = new Pedido(prod, 31);
            Assert.fail("No tiro excepcion");
        }catch (Exception e){
            Assert.assertThrows(MesaInexistenteException.class, )
        }

    }

    private void cargaSistema(){

    }
}
