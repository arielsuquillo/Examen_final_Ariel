package modelo;

import excepciones.CasillaYaDescubiertaException;

public class TableroTest {

    public static void main(String[] args) {
        testInicializacionTablero();
        testMarcarCasilla();
        System.out.println("Todas las pruebas unitarias pasaron correctamente.");
    }

    public static void testInicializacionTablero() {
        Tablero tablero = new Tablero();
        int minas = 0;
        for (int i = 0; i < Tablero.FILAS; i++) {
            for (int j = 0; j < Tablero.COLUMNAS; j++) {
                if (tablero.getCasilla(i, j).esMina()) {
                    minas++;
                }
            }
        }
        assert minas == Tablero.TOTAL_MINAS : "El tablero debe tener exactamente 10 minas.";
    }

    public static void testMarcarCasilla() {
        Tablero tablero = new Tablero();
        try {
            tablero.marcarCasilla(0, 0);
            assert tablero.getCasilla(0, 0).isMarcada() : "La casilla A1 deberia estar marcada.";
        } catch (CasillaYaDescubiertaException e) {
            assert false : "No deberia lanzar excepcion en una casilla cubierta.";
        }
    }
}
