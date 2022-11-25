package excepciones;

import modelos.Mesa;

public class MesaOcupadaException extends Exception {
    private Mesa mesa;

    public MesaOcupadaException(Mesa mesa) {
        super("La mesa " + mesa.getNroMesa() + " está ocupada");
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
