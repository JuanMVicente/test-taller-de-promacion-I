package excepciones;

public class MaximaCantidadMozosException extends Exception {
    public MaximaCantidadMozosException(int cantidad) {
        super("Maxima cantidad de mozos superada: " + cantidad);
    }
}
