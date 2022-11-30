package modelos;

import java.util.Date;

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
		Date fechaNacimiento= new Date(2000,10,10);
		int hijosACargo = 2;
		double remuneracionbasica=1000;
		Sueldo sueldo=new Sueldo(remuneracionbasica);
		Mozo mozo = new Mozo(nombre,apellido,fechaNacimiento,hijosACargo,sueldo);
		
		Assert.assertEquals(nombre, mozo.getNombre());
		Assert.assertEquals(apellido, mozo.getApellido());
		Assert.assertTrue(fechaNacimiento.equals(mozo.getFechaNacimiento()));
		Assert.assertEquals(hijosACargo, mozo.getHijosACargo());
		Assert.assertTrue(sueldo.equals(mozo.getSueldo()));
	}
	

}
