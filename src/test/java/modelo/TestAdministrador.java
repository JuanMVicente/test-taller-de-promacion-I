package modelo;

import modelos.Administrador;
import org.junit.*;

import excepciones.AdministradorExistenteException;
import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;
import org.junit.runners.MethodSorters;

import java.security.SignatureSpi;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdministrador {
	
	private Administrador adm;

	@Test
	public void test1_CrearAdministrador() {
		if(Administrador.isInicializado())
			Assert.fail("Escenario mal inicializado");
		try {
			adm = Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
	}

	@Test
	public void test2_CrearAdministradorYaInicializado() {
		if(!Administrador.isInicializado()) {
			try {
				adm = Administrador.crearAdministrador();
			} catch (AdministradorExistenteException e) {
				Assert.fail("Se emitio incorrectamente la excepcion");
			}
		}
		try {
			this.adm = Administrador.crearAdministrador();
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch (AdministradorExistenteException e) {
		}
	}



	
	@Test
	public void test3_IniciaSesionUsuarioActivoContraseñaCorrecta() {
		try{
			adm = (Administrador.isInicializado()) ? Administrador.getInstancia() : Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
			adm.setActivo(true);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
		try {
			adm.iniciarSesion("ADMIN1234");
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
	}
	
	@Test
	public void test4_IniciaSesionUsuarioActivoContraseñaIncorrecta() {
		try{
			adm = (Administrador.isInicializado()) ? Administrador.getInstancia() : Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
			adm.setActivo(true);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
		try {
			adm.iniciarSesion("ADMIN123456789");
			Assert.fail("No se emitio correctamente la excepcion correspondiente");
		} catch (UsuarioInactivoException  e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		} catch (ContraseniaIncorrectaException e){
		}
	}

	public void test5_IniciaSesionUsuarioInactivoContraseñaCorrecta() {
		try{
			adm = (Administrador.isInicializado()) ? Administrador.getInstancia() : Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
			adm.setActivo(false);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
		try {
			adm.iniciarSesion("ADMIN1234");
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch ( ContraseniaIncorrectaException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		} catch (UsuarioInactivoException e){

		}
	}

	@Test
	public void test6_IniciaSesionUsuarioInactivoContraseñaIncorrecta() {
		try{
			adm = (Administrador.isInicializado()) ? Administrador.getInstancia() : Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
			adm.setActivo(false);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
		try {
			adm.iniciarSesion("ADMIN123456789");
			Assert.fail("No se emitio correctamente la excepcion correspondiente");
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {
		}
	}

	@Test
	public void test7_CambioDeContrasenia(){
		try{
			adm = (Administrador.isInicializado()) ? Administrador.getInstancia() : Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
			adm.setActivo(false);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
		adm.cambiarContrasenia("Nueva123456");
		Assert.assertTrue("No se cambio correctamente el parametro establecioContrasenia", adm.isEstablecioContrasenia());
	}

}
