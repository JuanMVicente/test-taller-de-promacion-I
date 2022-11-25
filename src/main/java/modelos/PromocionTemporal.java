package modelos;

import modelos.enums.Dia;
import modelos.enums.FormaPago;

import java.util.List;

/**
 * Clase que modela una promocion temporal. <br>
 * <b>Inv:</b> <br>
 * nombre != null <br>
 * nombre != "" <br>
 * formaPago != null <br>
 * porcentajeDescuento > 0 <br>
 * porcentajeDescuento <= 100 <br>
 */
public class PromocionTemporal extends Promocion {
    private String nombre;
    private FormaPago formaPago;
    private int porcentajeDescuento;
    private boolean esAcumulable;

    /**
     * Constructor de la clase PromocionTemporal. <br>
     * <b>Pre:</b> <br>
     * nombre != null <br>
     * nombre != "" <br>
     * formaPago != null <br>
     * porcentajeDescuento > 0 <br>
     * porcentajeDescuento <= 100 <br>
     * <b>Post:</b> <br>
     * Se crea una nueva promocion temporal con los datos ingresados. <br>
     * @param nombre              Nombre de la promocion temporal.
     * @param formaPago           Forma de pago de la promocion temporal.
     * @param porcentajeDescuento Porcentaje de descuento de la promocion temporal.
     * @param esAcumulable        Indica si la promocion temporal es acumulable.
     * @param diasPromo           Dias en los que se aplica la promocion temporal.
     */
    public PromocionTemporal(String nombre, FormaPago formaPago, int porcentajeDescuento, boolean esAcumulable, List<Dia> diasPromo) {
        super(diasPromo);

        assert nombre != null : "El nombre de la promocion temporal no puede ser nulo";
        assert nombre != "" : "El nombre de la promocion temporal no puede ser vacío";
        assert formaPago != null : "La forma de pago de la promocion temporal no puede ser nula";
        assert porcentajeDescuento > 0 : "El porcentaje de descuento de la promocion temporal no puede ser menor o igual a 0";
        assert porcentajeDescuento <= 100 : "El porcentaje de descuento de la promocion temporal no puede ser mayor a 100";

        this.nombre = nombre;
        this.formaPago = formaPago;
        this.porcentajeDescuento = porcentajeDescuento;
        this.esAcumulable = esAcumulable;

        assert this.nombre.equals(nombre) : "El nombre de la promocion temporal no es el esperado";
        assert this.formaPago == formaPago : "La forma de pago de la promocion temporal no es la esperada";
        assert this.porcentajeDescuento == porcentajeDescuento : "El porcentaje de descuento de la promocion temporal no es el esperado";
        assert this.esAcumulable == esAcumulable : "El valor de esAcumulable de la promocion temporal no es el esperado";
        verificarInvariantes();
    }

    private void verificarInvariantes() {
        assert nombre != null : "El nombre de la promocion temporal no puede ser nulo";
        assert nombre != "" : "El nombre de la promocion temporal no puede ser vacío";
        assert formaPago != null : "La forma de pago de la promocion temporal no puede ser nula";
        assert porcentajeDescuento > 0 : "El porcentaje de descuento de la promocion temporal no puede ser menor o igual a 0";
        assert porcentajeDescuento <= 100 : "El porcentaje de descuento de la promocion temporal no puede ser mayor a 100";
    }
}
