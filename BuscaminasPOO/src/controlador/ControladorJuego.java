package controlador;

import java.io.IOException;
import java.util.InputMismatchException;
import excepciones.CasillaYaDescubiertaException;
import excepciones.CoordenadaInvalidaException;
import modelo.Jugador;
import modelo.Tablero;
import persistencia.EstadoJuego;
import persistencia.GestorArchivos;
import vista.VistaConsola;

public class ControladorJuego {
    private Tablero tablero;
    private Jugador jugador;
    private VistaConsola vista;

    public ControladorJuego() {
        this.vista = new VistaConsola();
    }

    public void iniciar() {
        vista.mostrarBienvenida();
        boolean salir = false;

        while (!salir) {
            try {
                int opcion = vista.solicitarOpcionMenu();
                switch (opcion) {
                    case 1:
                        String nombre = vista.solicitarTexto("Ingrese el nombre del jugador: ");
                        if (nombre.isEmpty()) nombre = "Jugador 1";
                        this.jugador = new Jugador(nombre);
                        this.tablero = new Tablero();
                        jugarPartida();
                        break;
                    case 2:
                        cargarPartida();
                        break;
                    case 3:
                        salir = true;
                        vista.mostrarMensaje("Gracias por jugar Buscaminas.");
                        break;
                    default:
                        vista.mostrarMensaje("Opcion no valida. Intente de nuevo.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                vista.mostrarMensaje("Error: Debe ingresar un numero entero valido.");
            }
        }
    }

    private void cargarPartida() {
        try {
            EstadoJuego estado = GestorArchivos.cargarPartida();
            this.tablero = estado.getTablero();
            this.jugador = estado.getJugador();
            vista.mostrarMensaje("Partida cargada correctamente.");
            jugarPartida();
        } catch (IOException | ClassNotFoundException e) {
            vista.mostrarMensaje("No se pudo cargar ninguna partida guardada: " + e.getMessage());
        }
    }

    private void jugarPartida() {
        boolean enCurso = true;

        while (enCurso) {
            vista.mostrarTablero(tablero, jugador);

            if (tablero.isMinaExplotada()) {
                vista.mostrarMensaje("\n¡BOOM! Has descubierto una mina. Fin del juego.");
                break;
            }
            if (tablero.verificarVictoria()) {
                vista.mostrarMensaje("\n¡FELICIDADES " + jugador.getNombre().toUpperCase() + "! Has ganado en " + jugador.getMovimientos() + " movimientos.");
                break;
            }

            vista.mostrarOpcionesTurno();
            String entrada = vista.solicitarTexto("Ingrese su jugada: ").toUpperCase();

            try {
                if (entrada.equals("S")) {
                    enCurso = false;
                } else if (entrada.equals("G")) {
                    GestorArchivos.guardarPartida(new EstadoJuego(tablero, jugador));
                    vista.mostrarMensaje("Partida guardada exitosamente.");
                } else if (entrada.startsWith("M") && entrada.length() >= 3) {
                    int[] coords = parsearCoordenada(entrada.substring(1));
                    tablero.marcarCasilla(coords[0], coords[1]);
                } else {
                    int[] coords = parsearCoordenada(entrada);
                    tablero.descubrirCasilla(coords[0], coords[1]);
                    jugador.incrementarMovimientos();
                }
            } catch (CoordenadaInvalidaException | CasillaYaDescubiertaException | ArrayIndexOutOfBoundsException e) {
                vista.mostrarMensaje("Aviso: " + e.getMessage());
            } catch (IOException e) {
                vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }

    private int[] parsearCoordenada(String texto) throws CoordenadaInvalidaException {
        if (texto == null || texto.length() < 2 || texto.length() > 3) {
            throw new CoordenadaInvalidaException("Formato invalido. Use letra (A-J) y numero (1-10), ej: A5.");
        }
        char letraFila = texto.charAt(0);
        if (letraFila < 'A' || letraFila > 'J') {
            throw new CoordenadaInvalidaException("Fila fuera de rango. Debe ser entre A y J.");
        }
        int fila = letraFila - 'A';
        int columna;
        try {
            columna = Integer.parseInt(texto.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new CoordenadaInvalidaException("La columna debe ser un numero entre 1 y 10.");
        }
        if (columna < 0 || columna >= Tablero.COLUMNAS) {
            throw new CoordenadaInvalidaException("Columna fuera de rango. Debe ser entre 1 y 10.");
        }
        return new int[]{fila, columna};
    }
}
