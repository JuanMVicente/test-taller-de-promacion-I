package modelo;

import excepciones.*;
import modelos.Mesa;
import modelos.Mozo;
import modelos.Sistema;
import modelos.Sueldo;
import modelos.enums.ModoOperacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class testSistemaMesas {

    private Sistema sistema;
    private Mesa mesa;

    @Before
    public void setup(){
        try {
            Sistema.inicializarSistema("nombre-local");
        } catch (SistemaYaInicializadoException e) {
            Assert.fail("Error al inicializar escenario");
        }
        sistema = Sistema.getInstancia();
        mesa = new Mesa(5,4);
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        mesa = null;
    }

    private void escenario1(){
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    }

    private void escenario2(){
        sistema.setModoOperacion(ModoOperacion.OPERARIO);
    }

    private void agregarMesa(Mesa mesa){
        ModoOperacion aux = sistema.getModoOperacion();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
        try {
            sistema.agregarMesa(mesa);
        } catch (MesaRepetidaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al crear escenario");
        }
        sistema.setModoOperacion(aux);
    }

    @Test
    public void testAgregaMesaEscenario1(){
        escenario1();
        try{
            sistema.agregarMesa(mesa);
            Assert.assertTrue("No se agrego correctamente la mesa", sistema.getMesas().contains(mesa));
        } catch (MesaRepetidaException | OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion inesperada");
        }
    }

    @Test
    public void testAgregaMesaExistenteEscenario1(){
        escenario1();
        agregarMesa(mesa);
        try{
            sistema.agregarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion inesperada");
        } catch (MesaRepetidaException e){

        }
    }

    @Test
    public void testAgregaMesaEscenario2(){
        escenario2();
        try{
            sistema.agregarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (MesaRepetidaException e) {
            Assert.fail("Se emitio una excepcion inesperada");
        } catch (OperacionNoAutorizadaException e){

        }
    }

    @Test
    public void testAgregaMesaExistenteEscenario2(){
        escenario2();
        agregarMesa(mesa);
        try{
            sistema.agregarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException | MesaRepetidaException e) {

        }
    }

    @Test
    public void testEliminarMesaEscenario1(){
        escenario1();
        agregarMesa(mesa);
        try{
            sistema.eliminarMesa(mesa);
            Assert.assertFalse("No se elimino correctamente la mesa", sistema.getMesas().contains(mesa));
        } catch (OperacionNoAutorizadaException | MesaInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }

    @Test
    public void testEliminarMesaInexistenteEscenario1(){
        escenario1();
        try{
            sistema.eliminarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (MesaInexistenteException e){

        }
    }

    @Test
    public void testEliminarMesaEscenario2(){
        escenario2();
        agregarMesa(mesa);
        try{
            sistema.eliminarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (MesaInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (OperacionNoAutorizadaException e){

        }
    }

    @Test
    public void testEliminarMesaInexistenteEscenario2(){
        escenario1();
        try{
            sistema.eliminarMesa(mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException | MesaInexistenteException e) {

        }
    }
}
