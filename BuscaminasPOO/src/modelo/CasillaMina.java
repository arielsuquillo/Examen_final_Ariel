package modelo;

public class CasillaMina extends Casilla {
    private static final long serialVersionUID = 1L;

    public CasillaMina(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public boolean esMina() {
        return true;
    }

    @Override
    public String obtenerSimboloConsola() {
        if (isDescubierta()) {
            return "X";
        }
        if (isMarcada()) {
            return "P";
        }
        return ".";
    }
}
