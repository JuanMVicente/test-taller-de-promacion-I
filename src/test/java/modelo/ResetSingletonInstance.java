package modelo;

import modelos.Administrador;
import modelos.Sistema;

import java.lang.reflect.Field;

public class ResetSingletonInstance {

    public static void reseteSistemaAndAdministrador(){
        resetSistema();
        resetAdministrador();
    }

    public static void resetSistema(){
        resetSingleton(Sistema.class, "instancia");
    }

    public static void resetAdministrador(){
        resetSingleton(Administrador.class, "instancia");
        resetBoolean(Administrador.class, "inicializado");
    }

    private static void resetSingleton(Class clase, String field){
        Field instance;
        try{
            instance = clase.getDeclaredField(field);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void resetBoolean(Class clase, String field){
        Field instance;
        try{
            instance = clase.getDeclaredField(field);
            instance.setAccessible(true);
            instance.set(null, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
