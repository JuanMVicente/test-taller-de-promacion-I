package modelo;

import excepciones.SistemaYaInicializadoException;
import modelos.Administrador;
import modelos.Sistema;
import org.junit.*;

import excepciones.AdministradorExistenteException;
import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdministrador {
	
	private Administrador adm;

	@Before
	public void setup(){
	}

	@After
	public void teardown(){
		ResetInstance.reseteSistemaAndAdministrador();
	}

	private void inicializaAdministrador(){
		try {
			Sistema.inicializarSistema("nombre-test");
			adm = Administrador.getInstancia();
		} catch (SistemaYaInicializadoException e) {
			Assert.fail("Se emitio incorrectamente la excepcion");
		}
	}

	private void cambiarContrasenia(){
		adm.cambiarContrasenia("Nueva123456");
	}

	@Test
	public void test1_CrearAdministrador() {
		try {
			adm = Administrador.crearAdministrador();
			Assert.assertNotNull("No se intancio correctamente el administrador", adm);
		} catch (AdministradorExistenteException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
	}

	@Test
	public void test2_CrearAdministradorYaInicializado() {
		inicializaAdministrador();
		try {
			this.adm = Administrador.crearAdministrador();
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch (AdministradorExistenteException e) {
		}
	}



	
	@Test
	public void test3_IniciaSesionUsuarioActivoContraseñaCorrecta() {
		inicializaAdministrador();
		cambiarContrasenia();
		adm.setActivo(true);
		try {
			adm.iniciarSesion("Nueva123456");
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		}
	}
	
	@Test
	public void test4_IniciaSesionUsuarioActivoContraseñaIncorrecta() {
		inicializaAdministrador();
		cambiarContrasenia();
		adm.setActivo(true);
		try {
			adm.iniciarSesion("ADMIN123456789");
			Assert.fail("No se emitio correctamente la excepcion correspondiente");
		} catch (UsuarioInactivoException  e) {
			Assert.fail("Se emitio excepcion de manera incorrecta");
		} catch (ContraseniaIncorrectaException e){
		}
	}

	public void test5_IniciaSesionUsuarioInactivoContraseñaCorrecta() {
		inicializaAdministrador();
		cambiarContrasenia();
		adm.setActivo(false);
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
		inicializaAdministrador();
		cambiarContrasenia();
		adm.setActivo(false);
		try {
			adm.iniciarSesion("ADMIN123456789");
			Assert.fail("No se emitio correctamente la excepcion correspondiente");
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {
		}
	}

	@Test
	public void test7_CambioDeContrasenia(){
		inicializaAdministrador();
		adm.cambiarContrasenia("Nueva123456");
		Assert.assertTrue("No se cambio correctamente el parametro establecioContrasenia", adm.isEstablecioContrasenia());
	}

}
