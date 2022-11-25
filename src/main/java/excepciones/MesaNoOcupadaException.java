package excepciones;

import modelos.Mesa;

public class MesaNoOcupadaException extends Exception {
    private Mesa mesa;

    public MesaNoOcupadaException(Mesa mesa) {
        super("La mesa " + mesa.getNroMesa() + " no está ocupada");
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
