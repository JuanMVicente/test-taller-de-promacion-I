package modelos;

import excepciones.AdministradorExistenteException;
import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;
import modelos.enums.ModoOperacion;

/**
 * Clase que representa a un administrador del sistema.
 * <b>Inv:</b>
 * Existe una unica instancia que se crea al inicio del programa.
 */
public class Administrador extends Operario {
    private boolean establecioContrasenia;
    private static Administrador instancia;
    private static boolean inicializado = false;

    /**
     * Crea un administrador con los valores por defecto.
     * <b>Post:</b>
     * establecioContrasenia == false
     */
    private Administrador() {
        super("ADMIN", "ADMIN", "ADMIN", "ADMIN1234");
        establecioContrasenia = false;

        assert !establecioContrasenia : "No se estableció el atributo 'establecioContrasenia'";
    }

    /**
     * Inicializa la instancia del administrador.
     * <b>Post</b>
     * Se crea una instancia del administrador.
     *
     * @return Instancia del administrador.
     * @throws AdministradorExistenteException Si ya existe una instancia del administrador.
     */
    public static Administrador crearAdministrador() throws AdministradorExistenteException {
        if (inicializado)
            throw new AdministradorExistenteException();

        Administrador administrador = new Administrador();
        inicializado = true;

        assert instancia != null : "No se creó la instancia del administrador";
        return administrador;
    }

    /**
     * Inicia sesion en el sistema.
     * <b>Pre:</b>
     * establecioContrasenia == true
     * <b>Post:</b>
     * Se establece el modo de operacion en modo administrador.
     *
     * @param contrasenia La contraseña del administrador.
     * @throws UsuarioInactivoException       Si el administrador no se encuentra activo.
     * @throws ContraseniaIncorrectaException Si la contraseña ingresada es incorrecta.
     */
    @Override
    public void iniciarSesion(String contrasenia) throws UsuarioInactivoException, ContraseniaIncorrectaException {
        assert establecioContrasenia : "No se estableció la contraseña del administrador";

        super.iniciarSesion(contrasenia);
        Sistema.getInstancia().setModoOperacion(ModoOperacion.ADMINISTRADOR);

        assert Sistema.getInstancia().getModoOperacion() == ModoOperacion.ADMINISTRADOR : "No se estableció el modo de operación en modo administrador";
    }

    /**
     * Establece la contraseña del administrador.
     * <b>Post:</b>
     * establecioContrasenia == true
     */
    @Override
    public void cambiarContrasenia(String contraseniaNueva) {
        super.cambiarContrasenia(contraseniaNueva);
        establecioContrasenia = true;

        assert establecioContrasenia;
    }

    /**
     * Devuelve si el administrador ya se inicializo.
     *
     * @return true si el administrador ya se inicializo, false en caso contrario.
     */
    public static boolean isInicializado() {
        return inicializado;
    }
}
