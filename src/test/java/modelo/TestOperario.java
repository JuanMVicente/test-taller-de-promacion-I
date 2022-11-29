package modelos;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;

@TestMethodOrder(OrderAnnotation.class)
class TestOperario {

	private Operario oper;
	private Sistema sistema;
	
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
	void Operario() {
		this.oper = new Operario("Jose","Perez","joseperez1","Cont123");
	}
	
	@Test
	@Order(2)
	void iniciarSesion1() {
		this.sistema = Sistema.getInstancia();
		this.oper.setActivo(true);
		try {
			this.oper.iniciarSesion("Cont123");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(3)
	void iniciarSesion2() {
		this.sistema = Sistema.getInstancia();
		
		this.oper.setActivo(false);
		try {
			this.oper.iniciarSesion("Cont123");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(3)
	void iniciarSesion3() {
		this.sistema = Sistema.getInstancia();
		this.oper.setActivo(true);
		try {
			this.oper.iniciarSesion("Cont123");
		} catch (UsuarioInactivoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(3)
	void cambiarContrasenia() {
		this.sistema = Sistema.getInstancia();
		this.oper.setActivo(true);
		this.oper.cambiarContrasenia("COnt123");
		
	}
	
	@Test
	@Order(3)
	void verificarContrasenia1() {
		assert modelos.Operario.verificarContrasenia("Cont123");
	}
	
	@Test
	@Order(3)
	void verificarContrasenia2() {
		assert modelos.Operario.verificarContrasenia("Cont1");
	}
	
	@Test
	@Order(3)
	void verificarContrasenia3() {
		assert modelos.Operario.verificarContrasenia("Contrasenia123");
	}
	
	@Test
	@Order(3)
	void verificarContrasenia4() {
		assert modelos.Operario.verificarContrasenia("contrasenia");
	}
	@Test
	@Order(3)
	void verificarContrasenia5() {
		assert modelos.Operario.verificarContrasenia("cont123");
	}
	
	@Test
	@Order(3)
	void verificarContrasenia6() {
		assert modelos.Operario.verificarContrasenia(null);
	}

}
