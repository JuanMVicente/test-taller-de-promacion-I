package excepciones;

import modelos.Mesa;

public class MesaNoAsignadaException extends Exception {
    private Mesa mesa;

    public MesaNoAsignadaException(Mesa mesa) {
        super("La mesa no ha sido asignada");
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
