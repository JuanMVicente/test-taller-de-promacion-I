package modelos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.SistemaYaInicializadoException;
import junit.framework.Assert;
import modelos.enums.ModoOperacion;

public class testMesa {
	
	
	private Sistema sistema;


    @BeforeClass
    public void setup(){
        try {
            Sistema.inicializarSistema("local");
        } catch (SistemaYaInicializadoException ignored) {
        }
        sistema = Sistema.getInstancia();
        sistema.setModoOperacion(ModoOperacion.ADMINISTRADOR);
    }
    

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor() {
		int nroMesa=4;
		int capacidad=3;
		Mesa mesa = new Mesa(nroMesa,capacidad);
		Assert.assertEquals(nroMesa, mesa.getNroMesa());
		Assert.assertEquals(capacidad,mesa.getCapacidad());
		Assert.assertFalse(mesa.estaOcupada());
		
	}
	
	@Test
	public void ocupar() {
		int nroMesa=4;
		int capacidad=3;
		Mesa mesa = new Mesa(nroMesa,capacidad);
		mesa.ocupar();
		Assert.assertTrue(mesa.estaOcupada());
		
		
	}
	
	public void desocupar() {
		int nroMesa=4;
		int capacidad=3;
		Mesa mesa = new Mesa(nroMesa,capacidad);
		mesa.desocupar();
		Assert.assertFalse(mesa.estaOcupada());
		
		
	}
}
