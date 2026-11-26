package modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int movimientos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.movimientos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void incrementarMovimientos() {
        this.movimientos++;
    }
}
