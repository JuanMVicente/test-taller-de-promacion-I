package modelos;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import excepciones.AdministradorExistenteException;
import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;

@TestMethodOrder(OrderAnnotation.class)
class TestAdministrador {
	
	private Administrador adm;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void crearAdministrador1() {
		try {
			this. adm = Administrador.crearAdministrador();
		} catch (AdministradorExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	void crearAdministrador2() {
		try {
			this. adm = Administrador.crearAdministrador();
		} catch (AdministradorExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void iniciarSesion1() {
		try {
			this.adm.iniciarSesion("ADMIN1234");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void iniciarSesion2() {
		this.adm.setActivo(false);
		try {
			this.adm.iniciarSesion("ADMIN1234");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	void iniciarSesion3() {
		this.adm.setActivo(true);
		
		try {
			this.adm.iniciarSesion("admin1234");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(5)
	void cambiarContrasenia() {
		
		this.adm.cambiarContrasenia("COnt1234");
		
	}

}
