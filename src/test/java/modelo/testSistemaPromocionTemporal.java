package modelo;

import excepciones.SistemaYaInicializadoException;
import modelos.PromocionProducto;
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

public class testSistemaPromocionTemporal {
    private PromocionTemporal promo;
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
        promo = new PromocionTemporal("nombre", FormaPago.EFECTIVO, 10, true, dias);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        promo = null;
    }

    @Test
    public void testAgregarPromocionTemporal(){

    }

    @Test
    public void testEliminarPromocionTemporal(){

    }
}
