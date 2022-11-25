package excepciones;

import modelos.Mozo;

public class MozoExistenteException extends Exception {
    private Mozo mozo;

    public MozoExistenteException(Mozo mozo) {
        super("El mozo ya existe");
        this.mozo = mozo;
    }

    public Mozo getMozo() {
        return mozo;
    }
}
