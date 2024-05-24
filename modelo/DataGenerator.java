package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    private static final String[] NOMBRES = {"John", "Mike", "James", "Oscar", "Chris"};
    private static final String[] APELLIDOS = {"Smith", "Johnson", "Brown", "Williams", "Jones"};
    private static final String[] CIUDADES = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
    private static final String[] EQUIPOS = {"Lakers", "Bulls", "Warriors", "Celtics", "Nets"};
    private static final String[] DIVISIONES = {"East", "West"};
    private static final String[] POSICIONES = {"Base", "Escolta", "Alero", "Ala-pivot", "Pivot"};
    private static final Random RANDOM = new Random();

    public static void generarDatosAleatorios() {
        String directory = "modelo/ArchivosGenerados";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileWriter equiposWriter = null;
        FileWriter jugadoresWriter = null;
        FileWriter estadisticasWriter = null;
        FileWriter partidosWriter = null;

        try {
            equiposWriter = new FileWriter(new File(dir, "equipos.txt"));
            jugadoresWriter = new FileWriter(new File(dir, "jugadores.txt"));
            estadisticasWriter = new FileWriter(new File(dir, "estadisticas.txt"));
            partidosWriter = new FileWriter(new File(dir, "partidos.txt"));

            // Generar datos para equipos
            for (int i = 1; i <= 5; i++) {
                String equipo = i + "," + CIUDADES[RANDOM.nextInt(CIUDADES.length)] + "," + EQUIPOS[RANDOM.nextInt(EQUIPOS.length)] + "," + getRandomAcronimo() + "," + DIVISIONES[RANDOM.nextInt(DIVISIONES.length)] + "," + RANDOM.nextInt(100) + "," + RANDOM.nextInt(100) + "\n";
                equiposWriter.write(equipo);
            }

            // Generar datos para jugadores
            for (int i = 1; i <= 10; i++) {
                String jugador = i + "," + NOMBRES[RANDOM.nextInt(NOMBRES.length)] + "," + APELLIDOS[RANDOM.nextInt(APELLIDOS.length)] + "," + getRandomDate() + "," + (RANDOM.nextDouble() * 2 + 1) + "," + (RANDOM.nextDouble() * 50 + 50) + "," + RANDOM.nextInt(99) + "," + POSICIONES[RANDOM.nextInt(POSICIONES.length)] + "," + (RANDOM.nextInt(5) + 1) + "\n";
                jugadoresWriter.write(jugador);
            }

            // Generar datos para estadisticas de jugadores
            for (int i = 1; i <= 10; i++) {
                String estadistica = i + "," + (RANDOM.nextInt(5) + 1) + "," + (RANDOM.nextDouble() * 48) + "," + RANDOM.nextInt(30) + "," + RANDOM.nextInt(15) + "," + RANDOM.nextInt(20) + "," + RANDOM.nextInt(10) + "," + RANDOM.nextInt(15) + "," + RANDOM.nextInt(15) + "," + RANDOM.nextInt(15) + "," + RANDOM.nextInt(10) + "," + RANDOM.nextInt(10) + "," + RANDOM.nextInt(10) + "," + RANDOM.nextInt(10) + "," + RANDOM.nextInt(10) + "\n";
                estadisticasWriter.write(estadistica);
            }

            // Generar datos para partidos
            for (int i = 1; i <= 5; i++) {
                String partido = i + "," + (RANDOM.nextInt(5) + 1) + "," + getRandomDate() + "," + "Matx" + i + "," + (RANDOM.nextBoolean() ? 'W' : 'L') + "\n";
                partidosWriter.write(partido);
            }

            System.out.println("Ficheros generados en: " + dir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (equiposWriter != null) equiposWriter.close();
                if (jugadoresWriter != null) jugadoresWriter.close();
                if (estadisticasWriter != null) estadisticasWriter.close();
                if (partidosWriter != null) partidosWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getRandomAcronimo() {
        return "" + (char) (RANDOM.nextInt(26) + 'A') + (char) (RANDOM.nextInt(26) + 'A') + (char) (RANDOM.nextInt(26) + 'A');
    }

    private static String getRandomDate() {
        int anyo = 1970 + RANDOM.nextInt(30);
        int mes = 1 + RANDOM.nextInt(12);
        int dia = 1 + RANDOM.nextInt(28);
        return anyo + "-" + (mes < 10 ? "0" : "") + mes + "-" + (dia < 10 ? "0" : "") + dia;
    }
}
