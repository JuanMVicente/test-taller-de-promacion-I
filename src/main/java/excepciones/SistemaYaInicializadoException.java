package excepciones;

public class SistemaYaInicializadoException extends Exception {
    public SistemaYaInicializadoException() {
        super("El sistema ya ha sido inicializado");
    }
}
