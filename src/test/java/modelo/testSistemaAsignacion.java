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

public class testSistemaAsignacion {

    private Sistema sistema;
    private Mesa mesa;
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
        mesa = new Mesa(5,4);


    }

    @After
    public void teardown(){
        ResetInstance.reseteSistemaAndAdministrador();
        sistema = null;
        mesa = null;
        mozo = null;
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
    public void testAsignaMozoMesa(){
        agregarMesa(mesa);
        agregarMozo(mozo);
        try{
            sistema.asignarMesa(mozo, mesa);
            Assert.assertTrue("No se agrego correctamente la asignacion", sistema.getAsignacionMesas().get(mozo).contains(mesa));
        } catch (MozoInexistenteException | MesaInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        }
    }

    @Test
    public void testAsignaMozoInexistenteMesa(){
        agregarMesa(mesa);
        try{
            sistema.asignarMesa(mozo, mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (MesaInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (MozoInexistenteException e){

        }
    }

    @Test
    public void testAsignaMozoMesaInexistente(){
        agregarMozo(mozo);
        try{
            sistema.asignarMesa(mozo, mesa);
            Assert.fail("No se emitio una excepcion esperada");
        } catch (MozoInexistenteException e) {
            Assert.fail("Se emitio una excepcion no esperada");
        } catch (MesaInexistenteException e){

        }
    }
}
