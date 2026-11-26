package modelo;

import java.io.Serializable;

public abstract class Casilla implements Serializable, IMostrable {
    private static final long serialVersionUID = 1L;
    private int fila;
    private int columna;
    private boolean descubierta;
    private boolean marcada;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.descubierta = false;
        this.marcada = false;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void alternarMarca() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }

    public abstract boolean esMina();
}
