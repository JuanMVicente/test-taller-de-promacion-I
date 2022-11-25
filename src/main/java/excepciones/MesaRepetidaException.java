package excepciones;

public class MesaRepetidaException extends Exception {
    public MesaRepetidaException() {
        super("La mesa ya está en el sistema");
    }
}