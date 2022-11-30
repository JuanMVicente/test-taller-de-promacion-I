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
import modelos.enums.ModoOperacion;

public class testSistemaPromocionProducto {

    private PromocionProducto promo;
    private Sistema sistema;
    private Producto prod1;
    private Producto prod2;

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
        prod1 = new Producto("Producto1",100,200,50);
        sistema.agregarProducto(prod1);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        promo = null;
        prod1 = null;
    }

    @Test
    public void testAgregarPromocionProducto(){
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
  
    	try{
    		sistema.agregarPromocionProducto(prod1, promo);
    		Assert.assertTrue("No se ha añadido a la colección", sistema.getPromocionesProducto().get(prod1).contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}

    }

    @Test
    public void testAgregarPromocionProductoInexistente(){
    	
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    	  
    	try{
    		sistema.agregarPromocionProducto(prod2, promo);
    		Assert.fail("Se agregó un producto que no corresponde");
    	}catch(ProductoInexistenteException e) {
    		
    		
    	}

    }

    @Test
    public void testEliminarPromocionProducto(){
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    	  
    	try{
    		sistema.agregarPromocionProducto(prod1, promo);
    		Assert.assertTrue("No se ha añadido a la colección", sistema.getPromocionesProducto().get(prod1).contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}
    	
    	  
    	try{
    		sistema.eliminarPromocionProducto(prod1);
    		Assert.assertTrue("No se ha eliminado de la colección", !sistema.getPromocionesProducto().get(prod1).contains(promo));
    	}catch(ProductoInexistenteException e) {
    		Assert.fail("Se emitio una excepción no correspondida");
    		
    	}
    	
    	

    }

    @Test
    public void testEliminarPromocionProductoInexistente(){
    	
    	sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    	  
    	try{
    		sistema.eliminarPromocionProducto(prod1);
    		Assert.fail("No se emitio una excepción no correspondida");
    	}catch(ProductoInexistenteException e) {

    		
    	}

    }
}
