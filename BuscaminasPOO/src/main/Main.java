package main;

import controlador.ControladorJuego;

/**
 * Punto de entrada principal de la aplicacion Buscaminas en Consola.
 */
public class Main {
    public static void main(String[] args) {
        ControladorJuego juego = new ControladorJuego();
        juego.iniciar();
    }
}
