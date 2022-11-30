package modelos;

import modelos.enums.Dia;

import java.util.List;

/**
 * <b>Inv:</b> <br>
 * aplicaDosPorUno y aplicaDtoPorCantidad no pueden ser falsos al mismo tiempo. <br>
 * dtoPorCantidad_CantMinima > 0 si aplica descuento por cantidad <br>
 * dtoPorCantidad_PrecioUnitario > 0 si aplica descuento por cantidad <br>
 */
public class PromocionProducto extends Promocion {
    private boolean aplicaDosPorUno;
    private boolean aplicaDtoPorCantidad;
    private int dtoPorCantidad_CantMinima;
    private double dtoPorCantidad_PrecioUnitario;

    /**
     * Constructor de la clase PromocionProducto. <br>
     * <b>Pre:</b> <br>
     * aplicaDosPorUno y aplicaDtoPorCantidad no pueden ser falsos al mismo tiempo. <br>
     * dtoPorCantidad_CantMinima > 0 si aplica descuento por cantidad <br>
     * dtoPorCantidad_PrecioUnitario > 0 si aplica descuento por cantidad <br>
     * <b>Post:</b> <br>
     * Se crea una nueva promocion de producto con los datos ingresados. <br>
     * @param aplicaDosPorUno               Indica si la promocion aplica dos por uno.
     * @param aplicaDtoPorCantidad          Indica si la promocion aplica descuento por cantidad.
     * @param dtoPorCantidad_CantMinima     Cantidad minima para aplicar el descuento por cantidad.
     * @param dtoPorCantidad_PrecioUnitario Precio unitario para aplicar el descuento por cantidad.
     * @param diasPromo                     Dias en los que se aplica la promocion.
     */
    public PromocionProducto(boolean aplicaDosPorUno, boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnitario, List<Dia> diasPromo) {
        super(diasPromo);

        assert aplicaDosPorUno || aplicaDtoPorCantidad : "La promocion de producto debe aplicar al menos un descuento";
        assert dtoPorCantidad_CantMinima > 0 || !aplicaDtoPorCantidad : "La cantidad minima para aplicar el descuento por cantidad debe ser mayor o igual a 0 si la promocion aplica descuento por cantidad";
        assert dtoPorCantidad_PrecioUnitario > 0 || !aplicaDtoPorCantidad : "El precio unitario para aplicar el descuento por cantidad debe ser mayor o igual a 0 si la promocion aplica descuento por cantidad";

        this.aplicaDosPorUno = aplicaDosPorUno;
        this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
        this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
        this.dtoPorCantidad_PrecioUnitario = dtoPorCantidad_PrecioUnitario;

        assert this.aplicaDosPorUno == aplicaDosPorUno : "Error al crear la promocion de producto";
        assert this.aplicaDtoPorCantidad == aplicaDtoPorCantidad : "Error al crear la promocion de producto";
        assert this.dtoPorCantidad_CantMinima == dtoPorCantidad_CantMinima : "Error al crear la promocion de producto";
        assert this.dtoPorCantidad_PrecioUnitario == dtoPorCantidad_PrecioUnitario : "Error al crear la promocion de producto";

        verificarInvariantes();
    }

    public boolean isAplicaDosPorUno() {
        return aplicaDosPorUno;
    }

    public boolean isAplicaDtoPorCantidad() {
        return aplicaDtoPorCantidad;
    }

    public int getDtoPorCantidad_CantMinima() {
        return dtoPorCantidad_CantMinima;
    }

    public double getDtoPorCantidad_PrecioUnitario() {
        return dtoPorCantidad_PrecioUnitario;
    }

    private void verificarInvariantes() {
        assert aplicaDosPorUno || aplicaDtoPorCantidad : "La promocion de producto debe aplicar al menos un descuento";
        assert dtoPorCantidad_CantMinima > 0 || !aplicaDtoPorCantidad : "La cantidad minima para aplicar el descuento por cantidad debe ser mayor o igual a 0 si la promocion aplica descuento por cantidad";
        assert dtoPorCantidad_PrecioUnitario > 0 || !aplicaDtoPorCantidad : "El precio unitario para aplicar el descuento por cantidad debe ser mayor o igual a 0 si la promocion aplica descuento por cantidad";
    }
}
