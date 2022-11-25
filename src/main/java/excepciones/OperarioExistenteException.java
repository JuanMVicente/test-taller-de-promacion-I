package excepciones;

import modelos.Operario;

public class OperarioExistenteException extends Exception {
    public OperarioExistenteException(Operario operario) {
        super("El operario " + operario.getNombre() + " " + operario.getApellido() + " ya existe");
    }
}
