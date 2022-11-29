package modelo;

import modelos.PromocionProducto;
import modelos.enums.Dia;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPromoProducto {

    @Test
    public void testPromoDosPorUno(){
        boolean dosPorUno = true;
        boolean descPorCant = false;
        int cantMin = 0;
        double precioUnit = 0;
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        PromocionProducto promo = new PromocionProducto(dosPorUno, descPorCant, cantMin, precioUnit, dias);
        Assert.assertEquals("No se asigno correctamente el dos por uno", dosPorUno, promo.isAplicaDosPorUno());
        Assert.assertEquals("No se asigno correctamente el descuento por cantidad", descPorCant, promo.isAplicaDtoPorCantidad());
        Assert.assertEquals("No se asigno correctamente la cantidad minima", cantMin, promo.getDtoPorCantidad_CantMinima());
        Assert.assertEquals("No se asigno correctamente el precio unitario", (float) precioUnit, (float) promo.getDtoPorCantidad_PrecioUnitario(), 0.01);
        Assert.assertEquals("No se asignaron correctamente los dias de promocion", dias, promo.getDiasPromo());
    }

    @Test
    public void testPromoDescuencoPorCantidad(){
        boolean dosPorUno = false;
        boolean descPorCant = true;
        int cantMin = 5;
        double precioUnit = 100;
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        PromocionProducto promo = new PromocionProducto(dosPorUno, descPorCant, cantMin, precioUnit, dias);
        Assert.assertEquals("No se asigno correctamente el dos por uno", dosPorUno, promo.isAplicaDosPorUno());
        Assert.assertEquals("No se asigno correctamente el descuento por cantidad", descPorCant, promo.isAplicaDtoPorCantidad());
        Assert.assertEquals("No se asigno correctamente la cantidad minima", cantMin, promo.getDtoPorCantidad_CantMinima());
        Assert.assertEquals("No se asigno correctamente el precio unitario", (float) precioUnit, (float) promo.getDtoPorCantidad_PrecioUnitario(), 0.01);
        Assert.assertEquals("No se asignaron correctamente los dias de promocion", dias, promo.getDiasPromo());
    }
}
