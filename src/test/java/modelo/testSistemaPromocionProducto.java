package modelo;

import excepciones.SistemaYaInicializadoException;
import modelos.Producto;
import modelos.PromocionProducto;
import modelos.Sistema;
import modelos.enums.Dia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testSistemaPromocionProducto {

    private PromocionProducto promo;
    private Sistema sistema;

    @Before
    public void setup(){
        try{
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException e) {
            Assert.fail("Error al inicializar el escenario");
        }
        sistema = Sistema.getInstancia();
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        promo = new PromocionProducto(true, false, 0, 0, dias);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        promo = null;
    }

    @Test
    public void testAgregarPromocionProducto(){
        Assert.fail();

    }

    @Test
    public void testAgregarPromocionProductoInexistente(){
        Assert.fail();

    }

    @Test
    public void testEliminarPromocionProducto(){
        Assert.fail();

    }

    @Test
    public void testEliminarPromocionProductoInexistente(){
        Assert.fail();

    }
}
