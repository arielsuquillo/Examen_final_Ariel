package modelo;

import java.io.Serializable;
import java.util.Random;
import excepciones.CasillaYaDescubiertaException;

public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int FILAS = 10;
    public static final int COLUMNAS = 10;
    public static final int TOTAL_MINAS = 10;

    private Casilla[][] matriz;
    private boolean minaExplotada;

    public Tablero() {
        this.matriz = new Casilla[FILAS][COLUMNAS];
        this.minaExplotada = false;
        inicializarTablero();
    }

    public void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = new CasillaVacia(i, j);
            }
        }
        colocarMinasAleatorias();
        calcularMinasAdyacentes();
    }

    private void colocarMinasAleatorias() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < TOTAL_MINAS) {
            int f = random.nextInt(FILAS);
            int c = random.nextInt(COLUMNAS);
            if (!matriz[f][c].esMina()) {
                matriz[f][c] = new CasillaMina(f, c);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!matriz[i][j].esMina()) {
                    int conteo = contarMinasAlrededor(i, j);
                    ((CasillaVacia) matriz[i][j]).setMinasAdyacentes(conteo);
                }
            }
        }
    }

    private int contarMinasAlrededor(int fila, int col) {
        int contador = 0;
        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (df == 0 && dc == 0) continue;
                int nf = fila + df;
                int nc = col + dc;
                if (esCoordenadaValida(nf, nc) && matriz[nf][nc].esMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public boolean esCoordenadaValida(int fila, int col) {
        return fila >= 0 && fila < FILAS && col >= 0 && col < COLUMNAS;
    }

    public void descubrirCasilla(int fila, int col) throws CasillaYaDescubiertaException {
        Casilla actual = matriz[fila][col];
        if (actual.isDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla seleccionada ya fue descubierta.");
        }
        if (actual.isMarcada()) {
            actual.alternarMarca();
        }

        if (actual.esMina()) {
            actual.setDescubierta(true);
            this.minaExplotada = true;
            revelarTodasLasMinas();
            return;
        }

        descubrirRecursivo(fila, col);
    }

    private void descubrirRecursivo(int fila, int col) {
        if (!esCoordenadaValida(fila, col)) return;
        Casilla actual = matriz[fila][col];
        if (actual.isDescubierta() || actual.esMina() || actual.isMarcada()) return;

        actual.setDescubierta(true);

        if (actual instanceof CasillaVacia) {
            CasillaVacia vacia = (CasillaVacia) actual;
            if (vacia.getMinasAdyacentes() == 0) {
                for (int df = -1; df <= 1; df++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        if (df != 0 || dc != 0) {
                            descubrirRecursivo(fila + df, col + dc);
                        }
                    }
                }
            }
        }
    }

    public void marcarCasilla(int fila, int col) throws CasillaYaDescubiertaException {
        Casilla actual = matriz[fila][col];
        if (actual.isDescubierta()) {
            throw new CasillaYaDescubiertaException("No se puede marcar una casilla ya descubierta.");
        }
        actual.alternarMarca();
    }

    public void revelarTodasLasMinas() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (matriz[i][j].esMina()) {
                    matriz[i][j].setDescubierta(true);
                }
            }
        }
    }

    public boolean isMinaExplotada() {
        return minaExplotada;
    }

    public boolean verificarVictoria() {
        if (minaExplotada) return false;
        int casillasSegurasDescubiertas = 0;
        int totalSeguras = (FILAS * COLUMNAS) - TOTAL_MINAS;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!matriz[i][j].esMina() && matriz[i][j].isDescubierta()) {
                    casillasSegurasDescubiertas++;
                }
            }
        }
        return casillasSegurasDescubiertas == totalSeguras;
    }

    public Casilla getCasilla(int fila, int col) {
        return matriz[fila][col];
    }
}
