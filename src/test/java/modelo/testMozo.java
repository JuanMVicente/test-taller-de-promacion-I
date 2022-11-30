package modelo;

import java.util.Date;
import java.util.GregorianCalendar;

import modelos.Mozo;
import modelos.Sueldo;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.SistemaYaInicializadoException;
import junit.framework.Assert;
import modelos.enums.ModoOperacion;

public class testMozo {


	@Test
	public void constructor() {
		String nombre= "jose";
		String apellido= "perez";
		Date fechaNacimiento = new GregorianCalendar(2000, 10,10).getTime();
		int hijosACargo = 2;
		double remuneracionbasica=1000;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		Mozo mozo = new Mozo(nombre,apellido,fechaNacimiento,hijosACargo,sueldo);
		
		Assert.assertEquals("No se asigno correctamente el nombre", nombre, mozo.getNombre());
		Assert.assertEquals("No se asigno correctamente el apellido", apellido, mozo.getApellido());
		Assert.assertEquals("No se asigno correctamente la fecha", fechaNacimiento, mozo.getFechaNacimiento());
		Assert.assertEquals("No se asigno correctamente la cantidad de hijos a cargo", hijosACargo, mozo.getHijosACargo());
		Assert.assertEquals("No se asigno correctamente el sueldo", sueldo.calcularSueldo(hijosACargo), mozo.getSueldo(), 0.5);
	}
	

}
