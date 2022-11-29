package modelo;

import java.util.ArrayList;
import java.util.List;

import modelos.PromocionTemporal;
import modelos.enums.Dia;
import modelos.enums.FormaPago;
import org.junit.*;

public class TestPromoTemporal {


	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void PromocionTemporal(){
		List<Dia> dias = new ArrayList<Dia>();
		dias.add(Dia.MARTES);
		String name = "Promocion1";
		boolean acumulable = true;
		FormaPago pago = FormaPago.EFECTIVO;

		int porcentajeDto = 10;
		PromocionTemporal promo = new PromocionTemporal(name,pago,porcentajeDto,acumulable,dias);
		Assert.assertEquals("No se asigno correctamente el nombre", name, promo.getNombre());
		Assert.assertEquals("No se asigno correctamente la forma de pago", pago, promo.getFormaPago());
		Assert.assertEquals("No se asigno correctamente el descuento", porcentajeDto, promo.getPorcentajeDescuento());
		Assert.assertEquals("No se asigno correctamente si es acumulable", acumulable, promo.isEsAcumulable());
		Assert.assertEquals("No se asigno correctamente los dias de promocion", dias, promo.getDiasPromo());
		Assert.assertTrue("No se inicializo correctamente el estado de la promo", promo.isActiva());
	}

}
