package excepciones;

import modelos.Mesa;

public class MesaInexistenteException extends Exception {
    private Mesa mesa;

    public MesaInexistenteException(Mesa mesa) {
        super("La mesa no existe.");
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
