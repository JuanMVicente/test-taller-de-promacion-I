package modelos;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.SistemaYaInicializadoException;
import modelos.enums.ModoOperacion;

public class testSueldo {
	
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
    
    
       

	
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void constructor() {
		double remuneracionbasica = 1000;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		Assert.assertEquals(remuneracionbasica, sueldo.getRemuneracionBasica(),0.5);
		
	}
	
	@Test
	public void calcualrSueldo1() {
		double remuneracionbasica = 100;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		int cantHijos =3;
		double sueldoP= sueldo.calcularSueldo(cantHijos);
		Assert.assertEquals(remuneracionbasica, sueldo.getRemuneracionBasica(),0.5);
		
	}
	
	public void calcualrSueldo2() {
		double remuneracionbasica = 100;
		Sueldo sueldo = new Sueldo(remuneracionbasica);
		int cantHijos =-5;
		double sueldoP= sueldo.calcularSueldo(cantHijos);
		Assert.assertEquals(remuneracionbasica, sueldo.getRemuneracionBasica(),0.5);
		
	}


}
