package vista;

import java.util.Scanner;
import modelo.Jugador;
import modelo.Tablero;

public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("==========================================");
        System.out.println("      BUSCAMINAS EN CONSOLA - POO UPS     ");
        System.out.println("==========================================");
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int solicitarOpcionMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Nueva partida");
        System.out.println("2. Cargar partida guardada");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
        String entrada = scanner.nextLine().trim();
        return Integer.parseInt(entrada);
    }

    public void mostrarTablero(Tablero tablero, Jugador jugador) {
        System.out.println("\nJugador: " + jugador.getNombre() + " | Movimientos: " + jugador.getMovimientos());
        System.out.println("Leyenda: [.] Cubierta | [V] Vacia | [1-8] Minas cerca | [P] Marcada | [X] Mina");
        System.out.print("    ");
        for (int col = 1; col <= Tablero.COLUMNAS; col++) {
            System.out.printf("%-3d", col);
        }
        System.out.println();
        System.out.println("   ---------------------------------");

        for (int i = 0; i < Tablero.FILAS; i++) {
            char letraFila = (char) ('A' + i);
            System.out.printf(" %c |", letraFila);
            for (int j = 0; j < Tablero.COLUMNAS; j++) {
                String simbolo = tablero.getCasilla(i, j).obtenerSimboloConsola();
                System.out.printf(" %-2s", simbolo);
            }
            System.out.println();
        }
    }

    public void mostrarOpcionesTurno() {
        System.out.println("\nAcciones disponibles:");
        System.out.println(" - Descubrir casilla: ingrese coordenada (ej. A5)");
        System.out.println(" - Marcar/Desmarcar mina: ingrese M + coordenada (ej. MA5)");
        System.out.println(" - Guardar partida: ingrese G");
        System.out.println(" - Salir al menu: ingrese S");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
