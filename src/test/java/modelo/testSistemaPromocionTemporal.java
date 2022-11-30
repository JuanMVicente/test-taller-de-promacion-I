package modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ProductoInexistenteException;
import excepciones.SistemaYaInicializadoException;
import modelos.enums.Dia;
import modelos.enums.FormaPago;
import modelos.enums.ModoOperacion;

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
    public void testAgregarPromocionProducto(){
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
  
    	try{
    		sistema.agregarPromocionTemporal(promo);
    		Assert.assertTrue("No se ha añadido a la colección", sistema.getPromocionesTemporales().contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}

    }

    @Test
    public void testEliminarPromocionTemporal1(){
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
  	  
    	try{
    		sistema.agregarPromocionTemporal(promo);
    		Assert.assertTrue("No se ha añadido a la colección", sistema.getPromocionesTemporales().contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}
    	
    	  
    	try{
    		sistema.eliminarPromocionTemporal(promo);
    		Assert.assertTrue("No se ha eliminado de la colección", !sistema.getPromocionesTemporales().contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}

    }
    @Test
    public void testEliminarPromocionTemporal2(){
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
  	  
    	PromocionTemporal promo1 = new PromocionTemporal("nombre", FormaPago.EFECTIVO, 10, true, dias);
    	  
    	try{
    		sistema.eliminarPromocionTemporal(promo1);
    		Assert.assertTrue("No se ha eliminado de la colección", !sistema.getPromocionesTemporales().contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}

    }
}
