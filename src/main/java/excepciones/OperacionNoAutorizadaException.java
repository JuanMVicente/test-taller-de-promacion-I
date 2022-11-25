package excepciones;

public class OperacionNoAutorizadaException extends Exception {
    public OperacionNoAutorizadaException() {
        super("La operación solo puede ser realizada por el administrador.");
    }
}
