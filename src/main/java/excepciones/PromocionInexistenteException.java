package excepciones;

import modelos.Promocion;

public class PromocionInexistenteException extends Exception {
    private Promocion promocion;

    public PromocionInexistenteException(String nombrePromocion) {
        super("La promoción no existe");
        this.promocion = promocion;
    }

    public Promocion getPromocion() {
        return promocion;
    }
}
