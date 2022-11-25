package excepciones;

import modelos.Operario;

public class UsuarioInactivoException extends Exception {
    public UsuarioInactivoException(Operario operario) {
        super("El usuario " + operario.getNombreUsuario() + " se encuentra inactivo");
    }
}
