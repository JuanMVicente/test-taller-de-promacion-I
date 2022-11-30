package modelo;

import static org.junit.Assert.*;

import modelos.Mesa;
import modelos.Sistema;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.SistemaYaInicializadoException;
import junit.framework.Assert;
import modelos.enums.ModoOperacion;

import java.util.Date;

public class testMesa {

	private Sistema sistema;
	private Mesa mesa;

	@Before
	public void setUp() {
		mesa = new Mesa(4, 5);
	}

	@After
	public void tearDown() {
		mesa = null;
	}

	@Test
	public void testConstructor() {
		int nroMesa=4;
		int capacidad=3;
		Mesa mesa = new Mesa(nroMesa,capacidad);
		Assert.assertEquals(nroMesa, mesa.getNroMesa());
		Assert.assertEquals(capacidad,mesa.getCapacidad());
		Assert.assertFalse(mesa.estaOcupada());
	}
	
	@Test
	public void testOcupar() {
		mesa.ocupar();
		Assert.assertTrue(mesa.estaOcupada());
	}

	@Test
	public void testDesocupar() {
		mesa.desocupar();
		Assert.assertFalse(mesa.estaOcupada());
	}
}
