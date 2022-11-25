package modelos;

import modelos.enums.Dia;

import java.util.List;

/**
 * Clase que representa una promocion. <br>
 * <b>Inv:</b> <br>
 * diasPromo != null
 * diasPromo.size() > 0
 */
public abstract class Promocion {
    private int id;
    private boolean activa;
    private List<Dia> diasPromo;
    private static int ultimoId = 0;

    /**
     * Constructor de la clase Promocion. <br>
     * <b>Pre:</b> <br>
     * diasPromo != null <br>
     * diasPromo.size() > 0 <br>
     * <b>Post:</b> <br>
     * Se crea una nueva promocion con los datos ingresados. <br>
     * @param diasPromo Dias en los que se aplica la promocion.
     */
    public Promocion(List<Dia> diasPromo) {
        this.id = ultimoId;
        this.activa = true;
        this.diasPromo = diasPromo;

        ultimoId++;
    }

    public List<Dia> getDiasPromo() {
        return diasPromo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void activar() {
        activa = true;
    }

    public void desactivar() {
        activa = false;
    }
}
