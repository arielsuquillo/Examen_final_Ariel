package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorArchivos {
    private static final String RUTA_GUARDADO = "partida_buscaminas.dat";

    public static void guardarPartida(EstadoJuego estado) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_GUARDADO))) {
            oos.writeObject(estado);
        }
    }

    public static EstadoJuego cargarPartida() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_GUARDADO))) {
            return (EstadoJuego) ois.readObject();
        }
    }
}
