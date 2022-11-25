package excepciones;

public class OperarioInexistenteException extends Exception {
    private String nombreOperario;

    public OperarioInexistenteException(String nombreUsuario) {
        super("El operario " + nombreUsuario + " no existe");
        this.nombreOperario = nombreUsuario;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }
}
