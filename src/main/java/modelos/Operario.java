package modelos;

import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioInactivoException;
import modelos.enums.ModoOperacion;

/**
 * Clase que representa a un operario del sistema. <br>
 * <b>Inv:</b> <br>
 * nombre != null <br>
 * nombre != "" <br>
 * apellido != null <br>
 * apellido != "" <br>
 * nombreUsuario != null <br>
 * nombreUsuario != "" <br>
 * nombreUsuario.length() <= 10 <br>
 * contrasenia != null <br>
 * contrasenia.length() >= 6 <br>
 * contrasenia.length() <= 12 <br>
 * contrasenia contiene al menos un numero <br>
 * contrasenia contiene al menos una letra mayuscula <br>
 */
public class Operario {
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private boolean activo;

    /**
     * Constructor de la clase Operario.
     * <b>Pre:</b> <br>
     * nombre != null <br>
     * nombre != "" <br>
     * apellido != null <br>
     * apellido != "" <br>
     * nombreUsuario != null <br>
     * nombreUsuario != "" <br>
     * nombreUsuario.length() <= 10 <br>
     * contrasenia != null <br>
     * contrasenia.length() >= 6 <br>
     * contrasenia.length() <= 12 <br>
     * contrasenia contiene al menos un numero <br>
     * contrasenia contiene al menos una letra mayuscula <br>
     * <b>Post:</b> <br>
     * Se crea un nuevo operario con los datos ingresados y con el estado activo. <br>
     * @param nombre        Nombre del operario.
     * @param apellido      Apellido del operario.
     * @param nombreUsuario Nombre de usuario del operario.
     * @param contrasenia   Contraseña del operario. <br>
     */
    public Operario(String nombre, String apellido, String nombreUsuario, String contrasenia) {
        assert nombre != null : "El nombre del operario no puede ser nulo";
        assert nombre != "" : "El nombre del operario no puede ser vacío";
        assert apellido != null : "El apellido del operario no puede ser nulo";
        assert apellido != "" : "El apellido del operario no puede ser vacío";
        assert nombreUsuario != null : "El nombre de usuario del operario no puede ser nulo";
        assert nombreUsuario != "" : "El nombre de usuario del operario no puede ser vacío";
        assert nombreUsuario.length() <= 10 : "El nombre de usuario del operario no puede tener más de 10 caracteres";
        assert contrasenia != null : "La contraseña del operario no puede ser nula";
        assert contrasenia.length() >= 6 : "La contraseña del operario no puede tener menos de 6 caracteres";
        assert contrasenia.length() <= 12 : "La contraseña del operario no puede tener más de 12 caracteres";
        assert contrasenia.matches(".*[0-9].*") : "La contraseña del operario debe contener al menos un número";
        assert contrasenia.matches(".*[A-Z].*") : "La contraseña del operario debe contener al menos una letra mayúscula";

        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.activo = true;

        assert this.nombre == nombre : "El nombre no se asignó correctamente";
        assert this.apellido == apellido : "El apellido no se asignó correctamente";
        assert this.nombreUsuario == nombreUsuario : "El nombre de usuario no se asignó correctamente";
        assert this.contrasenia == contrasenia : "La contraseña no se asignó correctamente";
        assert this.activo == true : "El estado del operario no se asignó correctamente";
        verificarInvariantes();
    }

    /**
     * Inicia sesion a un operario dentro del sistema. <br>
     * <b>Post:</b> <br>
     * Se establece el sistema en modo operario. <br>
     *
     * @param contrasenia La contraseña del operario.
     * @throws UsuarioInactivoException       Si el usuario se encuentra inactivo.
     * @throws ContraseniaIncorrectaException Si la contraseña ingresada es incorrecta.
     */
    public void iniciarSesion(String contrasenia) throws UsuarioInactivoException, ContraseniaIncorrectaException {
        if(!activo) {
            throw new UsuarioInactivoException(this);
        }

        if(!this.contrasenia.equals(contrasenia)) {
            throw new ContraseniaIncorrectaException();
        }

        Sistema.getInstancia().setModoOperacion(ModoOperacion.OPERARIO);
        verificarInvariantes();
    }

    /**
     * Metodo que permite cambiar la contraseña del operario. <br>
     * <b>Pre:</b> <br>
     * contraseniaNueva != null <br>
     * contraseniaNueva.length() >= 6 <br>
     * contraseniaNueva.length() <= 12 <br>
     * contraseniaNueva contiene al menos un numero <br>
     * contraseniaNueva contiene al menos una letra mayuscula <br>
     * <b>Post:</b> <br>
     * Se cambia la contraseña del operario. <br>
     * @param contraseniaNueva Contraseña nueva del operario.
     */
    public void cambiarContrasenia(String contraseniaNueva) {
        assert contraseniaNueva != null;
        assert contraseniaNueva.length() >= 6;
        assert contraseniaNueva.length() <= 12;
        assert contraseniaNueva.matches(".*[0-9].*");
        assert contraseniaNueva.matches(".*[A-Z].*");

        contrasenia = contraseniaNueva;

        assert contrasenia.equals(contraseniaNueva);
        verificarInvariantes();
    }

    /**
     * Metodo que permite verificar si la contrasena es valida. <br>
     *
     * @param contrasenia Contraseña a verificar.
     * @return true si la contraseña es valida, false en caso contrario.
     */
    public static boolean verificarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.length() >= 6 && contrasenia.length() <= 12 && contrasenia.matches(".*[0-9].*") && contrasenia.matches(".*[A-Z].*");
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    //TODO Disenar el proceso de activo e inactivo.
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    private void verificarInvariantes() {
        assert nombre != null : "El nombre del operario no puede ser nulo";
        assert nombre != "" : "El nombre del operario no puede ser vacío";
        assert apellido != null : "El apellido del operario no puede ser nulo";
        assert apellido != "" : "El apellido del operario no puede ser vacío";
        assert nombreUsuario != null : "El nombre de usuario del operario no puede ser nulo";
        assert nombreUsuario != "" : "El nombre de usuario del operario no puede ser vacío";
        assert nombreUsuario.length() <= 10 : "El nombre de usuario del operario no puede tener más de 10 caracteres";
        assert contrasenia != null : "La contraseña del operario no puede ser nula";
        assert contrasenia.length() >= 6 : "La contraseña del operario no puede tener menos de 6 caracteres";
        assert contrasenia.length() <= 12 : "La contraseña del operario no puede tener más de 12 caracteres";
        assert contrasenia.matches(".*[0-9].*") : "La contraseña del operario debe contener al menos un número";
        assert contrasenia.matches(".*[A-Z].*") : "La contraseña del operario debe contener al menos una letra mayúscula";
    }
}