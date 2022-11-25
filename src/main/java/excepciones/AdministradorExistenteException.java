package excepciones;

public class AdministradorExistenteException extends Exception {
    public AdministradorExistenteException() {
        super("El administrador ya existe.");
    }
}
