package modelos;

/**
 * Clase que representa el sueldo de un mozo. <br>
 * <b>Inv:</b> <br>
 * remuneracion >= 0 <br>
 */
public class Sueldo {
    private double remuneracionBasica;

    /**
     * Crea un sueldo con la remuneración básica indicada. <br>
     * <b>Pre:</b> <br>
     * remuneracionBasica > 0 <br>
     * <b>Post:</b> <br>
     * Se crea un nuevo sueldo con la remuneración básica indicada. <br>
     *
     * @param remuneracionBasica Remuneración básica del sueldo.
     */
    public Sueldo(double remuneracionBasica) {
        assert remuneracionBasica > 0 : "La remuneración básica debe ser mayor a 0";

        this.remuneracionBasica = remuneracionBasica;

        assert this.remuneracionBasica == remuneracionBasica : "La remuneración básica no se asignó correctamente";
        verificarInvariantes();
    }

    /**
     * Devuelve el sueldo del mozo. <br>
     *
     * @param cantHijos Cantidad de hijos del mozo.
     * @return Sueldo del mozo.
     */
    public double calcularSueldo(int cantHijos) {
        return remuneracionBasica + cantHijos * (remuneracionBasica * 0.05);
    }

    private void verificarInvariantes() {
        assert remuneracionBasica >= 0 : "La remuneración básica no puede ser negativa";
    }
}
