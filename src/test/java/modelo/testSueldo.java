package modelo;

import static org.junit.Assert.fail;

import modelos.Sistema;
import modelos.Sueldo;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.SistemaYaInicializadoException;
import modelos.enums.ModoOperacion;

public class testSueldo {

	private Sistema sistema;

	@Test
	public void testConstructor() {
		double remuneracionbasica = 1000;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		Assert.assertEquals(remuneracionbasica, sueldo.getRemuneracionBasica(),0.5);

	}

	@Test
	public void testCalcualrSueldo1() {
		double remuneracionbasica = 100;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		int cantHijos = 3;
        double expected = remuneracionbasica + remuneracionbasica * cantHijos * 0.05;
		Assert.assertEquals("No se calculo el sueldo como correspondia", expected, sueldo.calcularSueldo(cantHijos),0.5);

	}

    @Test
	public void testCalcualrSueldo2() {
		double remuneracionbasica = 100;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		int cantHijos = -5;
        double expected = remuneracionbasica + remuneracionbasica * cantHijos * 0.05;
        Assert.assertEquals("No se calculo el sueldo como correspondia", expected, sueldo.calcularSueldo(cantHijos),0.5);

	}


}
