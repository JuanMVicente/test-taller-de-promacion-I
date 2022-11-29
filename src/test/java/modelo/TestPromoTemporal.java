package modelos;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelos.enums.Dia;
import modelos.enums.FormaPago;

class TestPromoTemporal {

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
	public void PromocionTemporal(){
	    	List<Dia> dias = new ArrayList<Dia>();
	    	dias.add(Dia.MARTES);
	    	
	    	PromocionTemporal promo = new PromocionTemporal("Promocion1",FormaPago.EFECTIVO,10,true,dias);
	}

}
