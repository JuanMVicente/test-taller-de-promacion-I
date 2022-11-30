package modelo;

import excepciones.*;
import modelos.Mozo;
import modelos.Sistema;
import modelos.Sueldo;
import modelos.enums.Estado;
import modelos.enums.ModoOperacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class testSistemaMozos {

    Sistema sistema;
    Mozo mozo;

    @Before
    public void setup(){
        try {
            Sistema.inicializarSistema("nombre-local");
        } catch (SistemaYaInicializadoException e) {
            Assert.fail("Error al inicializar escenario");
        }
        sistema = Sistema.getInstancia();
        Date fecha = new GregorianCalendar(2000,6,10).getTime();
        mozo = new Mozo("Carlos", "Perez", fecha, 1, new Sueldo(100));
    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        mozo = null;
    }

    private void escenario1(){
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    }

    private void escenario2(){
        sistema.setModoOperacion(ModoOperacion.OPERARIO);
    }

    private void escenario3(){
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
        Date fecha = new GregorianCalendar(2000,6,10).getTime();
        for(int i = 0 ; i < 6 ; i++)
            agregarMozo(new Mozo("Carlos", "Perez", fecha, 1, new Sueldo(100)));
    }

    private void escenario4(){
        sistema.setModoOperacion(ModoOperacion.OPERARIO);
        Date fecha = new GregorianCalendar(2000,6,10).getTime();
        for(int i = 0 ; i < 6 ; i++)
            agregarMozo(new Mozo("Carlos", "Perez", fecha, 1, new Sueldo(100)));
    }

    private void agregarMozo(Mozo mozo){
        ModoOperacion aux = sistema.getModoOperacion();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
        try {
            sistema.agregarMozo(mozo);
        } catch (MozoExistenteException | MaximaCantidadMozosException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al crear escenario");
        }
        sistema.setModoOperacion(aux);
    }

    @Test
    public void testAgregarMozoNuevoEscenario1(){
        escenario1();
        try {
            sistema.agregarMozo(mozo);
            Assert.assertTrue("No se agrego correctamente el mozo", sistema.getMozos().contains(mozo));
        } catch (MozoExistenteException | OperacionNoAutorizadaException | MaximaCantidadMozosException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }

    @Test
    public void testAgregarMozoExistenteEscenario1(){
        escenario1();
        agregarMozo(mozo);
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException e) {

        } catch (MaximaCantidadMozosException | OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }

    @Test
    public void testAgregarMozoNuevoEscenario2(){
        escenario2();
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | MaximaCantidadMozosException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (OperacionNoAutorizadaException e){

        }
    }

    @Test
    public void testAgregarMozoExistenteEscenario2(){
        escenario2();
        agregarMozo(mozo);
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | OperacionNoAutorizadaException e) {

        } catch (MaximaCantidadMozosException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }

    @Test
    public void testAgregarMozoNuevoEscenario3(){
        escenario3();
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (MaximaCantidadMozosException e){

        }
    }

    @Test
    public void testAgregarMozoExistenteEscenario3(){
        escenario3();
        agregarMozo(mozo);
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | MaximaCantidadMozosException e) {

        } catch ( OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }


    @Test
    public void testAgregarMozoNuevoEscenario4(){
        escenario4();
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (MaximaCantidadMozosException e){

        }
    }

    @Test
    public void testAgregarMozoExistenteEscenario4(){
        escenario4();
        agregarMozo(mozo);
        try {
            sistema.agregarMozo(mozo);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoExistenteException | OperacionNoAutorizadaException | MaximaCantidadMozosException e) {

        }
    }

    @Test
    public void testEliminarMozoEscenario1(){
        escenario1();
        agregarMozo(mozo);
        try {
            sistema.eliminarMozo(mozo);
            Assert.assertFalse("No se elimino correctamente el mozo de la coleccion", sistema.getMozos().contains(mozo));
        } catch (MozoInexistenteException | OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion incorrecta");
        }
    }

    @Test
    public void testEliminarMozoInexistenteEscenario1(){
        escenario1();
        try {
            sistema.eliminarMozo(mozo);
            Assert.fail("no se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException e) {
            Assert.fail("Se emitio una excepcion incorrecta");
        } catch (MozoInexistenteException e){

        }
    }

    @Test
    public void testEliminarMozoEscenario2(){
        escenario2();
        agregarMozo(mozo);
        try {
            sistema.eliminarMozo(mozo);
            Assert.fail("no se emitio una excepcion esperada");
        } catch (MozoInexistenteException e) {
            Assert.fail("Se emitio una excepcion incorrecta");
        } catch (OperacionNoAutorizadaException e){

        }
    }

    @Test
    public void testEliminarMozoInexistenteEscenario2(){
        escenario2();
        try {
            sistema.eliminarMozo(mozo);
            Assert.fail("no se emitio una excepcion esperada");
        } catch (OperacionNoAutorizadaException | MozoInexistenteException e) {

        }
    }

    @Test
    public void testAsignaEstadoMozo(){
        agregarMozo(mozo);
        try {
            sistema.establecerEstadoMozo(mozo, Estado.ACTIVO);
            Assert.assertEquals("No se asigno correctamente el estado del mozo", Estado.ACTIVO, mozo.getEstado());
        } catch (MozoInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esparada");
        }
    }

    @Test
    public void testAsignaEstadoMozoInexistente(){
        try{
            sistema.establecerEstadoMozo(mozo, Estado.ACTIVO);
            Assert.fail("No se emitio la excepcion esperada");
        } catch (MozoInexistenteException e) {

        }
    }



}
