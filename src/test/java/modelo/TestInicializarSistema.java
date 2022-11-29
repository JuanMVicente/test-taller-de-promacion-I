package modelo;

import excepciones.SistemaYaInicializadoException;
import modelos.Sistema;
import modelos.enums.Estado;
import modelos.enums.ModoOperacion;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestInicializarSistema {

    Sistema sistema;

    @Before
    public void setup(){}

    @After
    public void tearDown(){}

    @Test
    public void InicializarSistema(){
        String nombreLocal = "Nombre Local";
        try {
            assertFalse("El sistema comienza inicializado cuando no corresponde", Sistema.isInicializado());
            Sistema.inicializarSistema(nombreLocal);
            sistema = Sistema.getInstancia();
            assertEquals("No se asigno correctamente el nombre del local", nombreLocal, sistema.getNombreLocal());
            assertEquals("No se inicializo correctamente las mesas", 0, sistema.getMesas().size());
            assertEquals("No se inicializo correctamente los mozos", 0, sistema.getMozos().size());
            assertEquals("No se inicializo correctamente los operarios", 0, sistema.getOperarios().size());
            assertEquals("No se inicializo correctamente las comandas", 0, sistema.getComandas().size());
            assertEquals("No se inicializo correctamente la asignacion de mesas", 0, sistema.getAsignacionMesas().size());
            assertEquals("No se inicializo correctamente las promociones producto", 0, sistema.getPromocionesProducto().size());
            assertEquals("No se inicializo correctamente las promociones temporales", 0, sistema.getPromocionesTemporales().size());
            assertTrue("No se asigno correctamente el modo inicial", sistema.getModoOperacion().equals(ModoOperacion.OPERARIO));

        } catch (SistemaYaInicializadoException e) {
            fail("Se emitio incorrectamente la excepcion");
        }
    }

    @Test
    public void InicializarSistemaYaInizializado(){
        String nombreLocal = "Nombre local";
        try{
            if(!Sistema.isInicializado())
                Sistema.inicializarSistema(nombreLocal);
        } catch (SistemaYaInicializadoException e) {
            fail("Error al inicializar el sistema");
        }
        try{
            Sistema.inicializarSistema(nombreLocal);
            fail("No se emitio correctamente la excepcion");
        } catch (SistemaYaInicializadoException e) {

        }
    }

}
