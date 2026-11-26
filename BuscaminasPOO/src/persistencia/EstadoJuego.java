package persistencia;

import java.io.Serializable;
import modelo.Jugador;
import modelo.Tablero;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;
    private Tablero tablero;
    private Jugador jugador;

    public EstadoJuego(Tablero tablero, Jugador jugador) {
        this.tablero = tablero;
        this.jugador = jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
