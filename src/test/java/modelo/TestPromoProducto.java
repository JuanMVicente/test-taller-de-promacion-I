package modelos;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelos.enums.Dia;

class TestPromoProducto {

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
	void PromocionProducto1() {
		List<Dia> dias = new ArrayList<Dia>();
    	dias.add(Dia.MARTES);	
		PromocionProducto promo = new PromocionProducto(false,true,5,100,dias);
	}
	
	@Test
	void PromocionProducto2() {
		List<Dia> dias = new ArrayList<Dia>();
    	dias.add(Dia.MARTES);	
		PromocionProducto promo = new PromocionProducto(true,false,0,0,dias);
	}

}
