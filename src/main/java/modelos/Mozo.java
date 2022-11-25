package modelos;

import modelos.enums.Estado;

import java.util.Date;

/**
 * <b>Inv:</b> <br>
 * nombre != null <br>
 * apellido != null <br>
 * fechaNacimiento != null <br>
 * hijosACargo >= 0 <br>
 * estado != null <br>
 * la edad del mozo es mayor o igual a 18 años. <br>
 * sueldo != null <br>
 */
public class Mozo {
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private int hijosACargo;
    private Estado estado;
    private Sueldo sueldo;

    /**
     * Crea un mozo con los datos indicados. <br>
     * <b>Pre:</b> <br>
     * nombre != null <br>
     * apellido != null <br>
     * fechaNacimiento != null <br>
     * hijosACargo >= 0 <br>
     * la edad del mozo es mayor o igual a 18 años. <br>
     * sueldo != null <br>
     * <b>Post:</b> <br>
     * Se crea el mozo con los datos indicados y con estado ACTIVO. <br>
     *
     * @param nombre          Nombre del mozo.
     * @param apellido        Apellido del mozo.
     * @param fechaNacimiento Fecha de nacimiento del mozo.
     * @param hijosACargo     Cantidad de hijos a cargo del mozo.
     * @param sueldo          Sueldo del mozo.
     */
    public Mozo(String nombre, String apellido, Date fechaNacimiento, int hijosACargo, Sueldo sueldo) {
        assert nombre != null : "El nombre no puede ser nulo";
        assert apellido != null : "El apellido no puede ser nulo";
        assert fechaNacimiento != null : "La fecha de nacimiento no puede ser nula";
        assert hijosACargo >= 0 : "La cantidad de hijos a cargo no puede ser negativa";
        assert mayorDeEdad(fechaNacimiento) : "El mozo es menor de edad";
        assert sueldo != null : "El sueldo no puede ser nulo";

        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.hijosACargo = hijosACargo;
        this.estado = Estado.ACTIVO;
        this.sueldo = sueldo;

        assert this.nombre == nombre : "El nombre no se ha asignado correctamente";
        assert this.apellido == apellido : "El apellido no se ha asignado correctamente";
        assert this.fechaNacimiento == fechaNacimiento : "La fecha de nacimiento no se ha asignado correctamente";
        assert this.hijosACargo == hijosACargo : "La cantidad de hijos a cargo no se ha asignado correctamente";
        assert this.estado == Estado.ACTIVO : "El estado no se ha asignado correctamente";
        assert this.sueldo == sueldo : "El sueldo no se ha asignado correctamente";
        verificarInvariantes();
    }

    /**
     * Determina si la persona es mayor de edad. <br>
     * <b>Pre:</b> <br>
     * fechaNacimiento != null <br>
     * @param fechaNacimiento La fecha de nacimiento de la persona.
     * @return Si es mozo es mayor de edad.
     */
    private static boolean mayorDeEdad(Date fechaNacimiento) {
        assert fechaNacimiento != null : "La fecha de nacimiento no puede ser null.";

        Date fechaActual = new Date();
        long ms = fechaActual.getTime() - fechaNacimiento.getTime();
        long edad = ms / (1000l * 60 * 60 * 24 * 365);
        return edad >= 18;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getHijosACargo() {
        return hijosACargo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getSueldo() {
        return sueldo.calcularSueldo(hijosACargo);
    }

    public void verificarInvariantes() {
        assert nombre != null : "nombre no puede ser null.";
        assert apellido != null : "apellido no puede ser null.";
        assert fechaNacimiento != null : "fechaNacimiento no puede ser null";
        assert hijosACargo >= 0 : "hijosACargo no puede ser menor a 0";
        assert estado != null : "estado no puede ser null";
        assert mayorDeEdad(fechaNacimiento) : "El mozo no puede ser menor de edad";
        assert sueldo != null : "sueldo no puede ser null";
    }
}
