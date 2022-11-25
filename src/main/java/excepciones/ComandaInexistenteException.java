package excepciones;

public class ComandaInexistenteException extends Exception {
    public ComandaInexistenteException() {
        super("La comanda no existe");
    }
}
