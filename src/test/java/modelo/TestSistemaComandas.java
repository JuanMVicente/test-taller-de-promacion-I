package modelo;

import excepciones.*;
import modelos.Mesa;
import modelos.Sistema;
import modelos.enums.ModoOperacion;
import org.junit.*;

public class TestSistemaComandas {

    private Sistema sistema;
    private Mesa mesa;

    @BeforeClass
    public void setup(){
        try {
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException ignored) {
        }
        sistema = Sistema.getInstancia();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
        mesa = new Mesa(3,3);
        try {
            sistema.agregarMesa(mesa);
        } catch (MesaRepetidaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al preparar escenario");
        }
    }

    @AfterClass
    public void tearDown(){
        sistema = null;
    }

    @Test
    public void crearComandaConMesaExistenteLibre(){
        try {
            sistema.crearComanda(mesa);
            Assert.assertTrue("La mesa no se ocupo correctamente",mesa.estaOcupada());
            Assert.assertTrue("La comanda no se adiciono correctamente", sistema.getComandas().containsKey(mesa));
        } catch (MesaInexistenteException | MesaOcupadaException e) {
            Assert.fail("La comanda no se creo correctamente");
        }
    }

    @Test
    public void crearComandaConMesaNoExistenteLibre(){
        Mesa nueva = new Mesa(4,5);
        try {
            sistema.agregarMesa(nueva);
            sistema.crearComanda(mesa);
            Assert.fail("No se arrojo la excepcion esperada");
        } catch (MesaRepetidaException | OperacionNoAutorizadaException e) {
            Assert.fail("Error al preparar escenario");
        } catch (MesaInexistenteException e) {
            Assert.fail("La comanda no se creo correctamente");
        } catch (MesaOcupadaException e){
            Assert.fail("Error al emitir excepcion");
        }
    }

    @Test
    public void crearComandaConMesaExistenteOcupada(){
        mesa.ocupar();
        try {
            sistema.crearComanda(mesa);
            Assert.fail("No se arrojo la excepcion esperada");
        } catch (MesaInexistenteException e) {
            Assert.fail("La comanda no se creo correctamente");
        } catch (MesaOcupadaException e){

        }
    }

    @Test
    public void cerrarComandaMesaExistenteOcupada(){
        try{
            sistema.crearComanda(mesa);
        } catch (MesaOcupadaException | MesaInexistenteException e) {
            Assert.fail("Error al preparar escenario");
        }

        try{
            sistema.cerrarComanda(mesa);
            Assert.assertFalse("La mesa no se libero correctamente", mesa.estaOcupada());
            Assert.assertFalse("La mesa no se retiro correctamente de la coleccion", sistema.getComandas().containsKey(mesa));
        } catch (MesaNoOcupadaException | MesaInexistenteException e) {
            Assert.fail("Error al cerrar la comanda");
        }
    }

    @Test
    public void cerrarComandaMesaInexistente(){
        Mesa nueva = new Mesa(8,5);
        nueva.ocupar();
        try {
            sistema.cerrarComanda(nueva);
            Assert.fail("Excepcion no emitida");
        } catch (MesaInexistenteException e) {

        } catch (MesaNoOcupadaException e) {
            Assert.fail("Excepcion incorrecta emitida");
        }
    }

    @Test
    public void cerrarComandaMesaNoOcupada(){
        mesa.desocupar();
        try{
            sistema.cerrarComanda(mesa);
            Assert.fail("Excepcion no emitida");
        } catch (MesaNoOcupadaException e) {

        } catch (MesaInexistenteException e) {
            Assert.fail("Excepcion incorrecta emitida");
        }
    }
}
