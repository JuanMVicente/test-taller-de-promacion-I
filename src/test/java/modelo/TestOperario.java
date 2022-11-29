package modelo;


import excepciones.SistemaYaInicializadoException;
import modelos.Operario;
import modelos.Sistema;
import modelos.enums.ModoOperacion;
import org.junit.*;

import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;

public class TestOperario {

	private Operario oper;
	private Sistema sistema;


	@BeforeClass
	public static void setUp() {
		try {
			if(!Sistema.isInicializado())
				Sistema.inicializarSistema("Nombre local");
		} catch (Exception e) {
			//Se da por supuesto que la inicializacion del sistema se debe testear antes
		}
	}

	@Before
	public void setup(){
		oper = new Operario("Jose","Perez","joseperez1","Cont123");
		sistema = Sistema.getInstancia();
	}

	@After
	public void tearDown() {
		oper = null;
		sistema = null;
	}

	@Test
	public void constructor(){
		String nombre = "pepe";
		String apellido = "Gonzalez";
		String nombreUsuario = "PepeGonzalez";
		String pass = "Admin123";
		Operario nuevo = new Operario(nombre, apellido, nombreUsuario, pass);
		Assert.assertEquals("No se asigno correctamente el nombre", nombre, nuevo.getNombre());
		Assert.assertEquals("No se asigno correctamente el apellido", apellido, nuevo.getApellido());
		Assert.assertEquals("No se asigno correctamente el nombre de usuario", nombreUsuario, nuevo.getNombreUsuario());
		Assert.assertTrue("No se inicializo correctamente el estado del operario", nuevo.isActivo());
	}

	@Test
	public void iniciaSesionOperarioActivoContraseniaCorrecta(){
		Assert.assertNotNull("Error al inicializar el sistema",sistema);
		oper.setActivo(true);
		try {
			oper.iniciarSesion("Cont1");
			Assert.assertTrue("No se asigno correctamente el modo de operacion", sistema.getModoOperacion().equals(ModoOperacion.OPERARIO));
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {
			Assert.fail("Se emitio una excepcion que no correspondia");
		}
	}

	@Test
	public void iniciaSesionOperarioActivoContraseniaIncorrecta(){
		Assert.assertNotNull("Error al inicializar el sistema",sistema);
		oper.setActivo(true);
		try {
			oper.iniciarSesion("Cont1234");
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch (UsuarioInactivoException e) {
			Assert.fail("Se emitio una excepcion que no correspondia");
		} catch (ContraseniaIncorrectaException e){

		}
	}

	@Test
	public void iniciaSesionOperarioInactivoContraseniaCorrecta(){
		Assert.assertNotNull("Error al inicializar el sistema",sistema);
		oper.setActivo(false);
		try {
			oper.iniciarSesion("Cont1");
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch (ContraseniaIncorrectaException e) {
			Assert.fail("Se emitio una excepcion que no correspondia");
		} catch (UsuarioInactivoException e){

		}
	}

	@Test
	public void iniciaSesionOperarioInactivoContraseniaIncorrecta(){
		Assert.assertNotNull("Error al inicializar el sistema",sistema);
		oper.setActivo(false);
		try {
			oper.iniciarSesion("Cont1234");
			Assert.fail("No se emitio la excepcion correspondiente");
		} catch (UsuarioInactivoException | ContraseniaIncorrectaException e) {

		}
	}

	@Test
	public void verificarContraseniaCorrecta(){
		String pass = "Admin1234";
		Assert.assertTrue("No retorno lo que se esperaba", Operario.verificarContrasenia(pass));
	}

	@Test
	public void verificarContraseniaIncorrecta1(){
		String pass = "admin123";
		Assert.assertFalse("No retorno lo que se esperaba", Operario.verificarContrasenia(pass));
	}

	@Test
	public void verificarContraseniaIncorrecta2(){
		String pass = "adm";
		Assert.assertFalse("No retorno lo que se esperaba", Operario.verificarContrasenia(pass));
	}

	@Test
	public void verificarContraseniaIncorrecta3(){
		String pass = "Administrador123456789";
		Assert.assertFalse("No retorno lo que se esperaba", Operario.verificarContrasenia(pass));
	}

	@Test
	public void verificarContraseniaIncorrecta4(){
		String pass = "Adminis";
		Assert.assertFalse("No retorno lo que se esperaba", Operario.verificarContrasenia(pass));
	}




}
