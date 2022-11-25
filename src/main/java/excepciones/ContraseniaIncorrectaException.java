package excepciones;

public class ContraseniaIncorrectaException extends Exception {
    public ContraseniaIncorrectaException() {
        super("La contraseña ingresada es incorrecta");
    }
}
