package org.example.infrastructure;


import java.io.*;

public class IDManager {
    private static final String FILE_PATH = "last_id.txt";

    // Método para obtener el último ID
    public static int obtenerUltimoId() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return 0; // Si no existe el archivo, es la primera vez que se usa
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para actualizar el último ID
    public static void actualizarUltimoId(int nuevoId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(nuevoId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

