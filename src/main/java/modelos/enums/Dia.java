package modelos.enums;

import java.util.Calendar;

public enum Dia {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO;

    public static Dia getDiaActual() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_WEEK);
        Dia diaActual = null;
        switch (dia) {
            case Calendar.MONDAY:
                diaActual = Dia.LUNES;
                break;
            case Calendar.TUESDAY:
                diaActual = Dia.MARTES;
                break;
            case Calendar.WEDNESDAY:
                diaActual = Dia.MIERCOLES;
                break;
            case Calendar.THURSDAY:
                diaActual = Dia.JUEVES;
                break;
            case Calendar.FRIDAY:
                diaActual = Dia.VIERNES;
                break;
            case Calendar.SATURDAY:
                diaActual = Dia.SABADO;
                break;
            case Calendar.SUNDAY:
                diaActual = Dia.DOMINGO;
                break;
        }
        return diaActual;
    }
}
