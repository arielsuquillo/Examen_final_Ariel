package modelo;

public class CasillaVacia extends Casilla {
    private static final long serialVersionUID = 1L;
    private int minasAdyacentes;

    public CasillaVacia(int fila, int columna) {
        super(fila, columna);
        this.minasAdyacentes = 0;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    @Override
    public boolean esMina() {
        return false;
    }

    @Override
    public String obtenerSimboloConsola() {
        if (!isDescubierta()) {
            return isMarcada() ? "P" : ".";
        }
        if (minasAdyacentes == 0) {
            return "V";
        }
        return String.valueOf(minasAdyacentes);
    }
}
