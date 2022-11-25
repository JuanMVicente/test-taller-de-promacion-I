package excepciones;

import modelos.Mozo;

public class MozoInexistenteException extends Exception {
    private Mozo mozo;

    public MozoInexistenteException(Mozo mozo) {
        super("El mozo no existe");
        this.mozo = mozo;
    }

    public Mozo getMozo() {
        return mozo;
    }
}
