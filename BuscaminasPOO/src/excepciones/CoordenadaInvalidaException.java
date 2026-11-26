package excepciones;

public class CoordenadaInvalidaException extends Exception {
    private static final long serialVersionUID = 1L;

    public CoordenadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
