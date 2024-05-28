package vista;

import controlador.PlayerController;
import controlador.TeamController;
import controlador.Validaciones;
import modelo.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static Scanner scan = new Scanner(System.in);
    public static PlayerController playerController = new PlayerController();
    public static TeamController teamController = new TeamController();


    public static void main(String[] args) {
//       afegirJugador_a_Equip();
//        traspasarJugador_a_Equipo();
//        actualizarDatosPartido();
    }
    /*
    Ejercicio 6. Por defecto los datos se guardan en la carpeta actualizarDatos, en un fichero .txt con el mismo nombre.
     */
//    private static void actualizarDatosPartido() {
//
//        if (Validaciones.leerArchivo("actualizarDatos.txt")){
//            System.out.println("Registros actualizados correctamente");
//        }
//    }

    /*
    4.- Inserir un nou jugador a un equip.
Demanarem el nom del jugador i l’equip on el volen inserir, si el jugador ja existeix a la BDD en un equip diferent,
donarem l’opció d’executar el punt Traspassar un judador a un altra equip.
     */

//    public static void afegirJugador_a_Equip() {
//        String nombreJugador;
//        String nombreEquipo;
//        int id_equip;
//
//
//        System.out.println("Introduce el nombre del jugador:");
//        nombreJugador = scan.nextLine();
//        if (!Validaciones.verificarJugador(nombreJugador, 4)) {
//            System.out.println("Vas a añadir un nuevo jugador a la base de datos. ¿A qué equipo quieres añadirlo?");
//
//            nombreEquipo = scan.nextLine();
//            if (Validaciones.isValidTeamName(nombreEquipo)) {
//                if (teamController.existTeamName(nombreEquipo)) {
//                    id_equip = teamController.getTeamId(nombreEquipo);
//                    System.out.println("Completa los siguientes datos para insertar al nuevo jugador:");
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        System.out.print("Introduce el nombre del jugador: ");
//                        String nom = scan.nextLine();
//
//                        System.out.print("Introduce el apellido del jugador: ");
//                        String cognom = scan.nextLine();
//
//                        System.out.print("Introduce la fecha de nacimiento del jugador (YYYY-MM-DD): ");
//                        String dataNaixementStr = scan.nextLine();
//                        Date dataNaixement = dateFormat.parse(dataNaixementStr);
//
//                        System.out.print("Introduce la altura del jugador (por ejemplo, 1.80m o 180cm): ");
//                        String alcada = scan.nextLine();
//
//                        System.out.print("Introduce el peso del jugador (por ejemplo, 75kg): ");
//                        String pes = scan.nextLine();
//
//                        System.out.print("Introduce el número dorsal del jugador: ");
//                        String dorsal = scan.nextLine();
//
//                        System.out.print("Introduce la posición del jugador (por ejemplo, delantero, defensa, portero): ");
//                        String posicio = scan.nextLine();
//                        //El campo jugador_id es autoincremental
//                        int numJugadores = playerController.getAllPlayers().size();
//                        int jugador_id = numJugadores + 4;
//                        System.out.println("Id del nuevo jugador creado: " + jugador_id);
//                        System.out.println("Añadiendo jugador... Espere");
//                        playerController.createPlayer(
//                                jugador_id,
//                                nom,
//                                cognom,
//                                dataNaixement,
//                                alcada,
//                                pes,
//                                dorsal,
//                                posicio,
//                                id_equip
//                        );
//                        System.out.println("Jugador añadido a la base de datos exitosamente:");
//
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    } finally {
//                        scan.close();
//                    }
//
//                }
//            }
//        }
//    }


    public static void traspasarJugador_a_Equipo() {
        String nombreJugador;
        boolean salir = false;
        do {

            System.out.print("Introduce el nombre del jugador (o escribe 'regresar' para volver al menú): ");
            nombreJugador = scan.nextLine();


            if (!Validaciones.verificarJugador(nombreJugador, 5)) {
                System.out.println("El jugador introducido no existe. Vuelve a probar");
            } else salir = true;


        } while (!salir);

    }


    public static void traspasarJugador_a_Equipo(Player player) {

        String nombreEquipo;
        boolean salir = false;

        System.out.print("Introduce el nombre del equipo al que quieres cambiar el jugador: (o escribe 'regresar' para volver al menú): ");
        nombreEquipo = scan.nextLine();
        do {
            if (nombreEquipo.equalsIgnoreCase("regresar")) {
                return; // Volver al menú principal
            }
            if (!Validaciones.isValidTeamName(nombreEquipo)) {
                System.out.println("El nombre del equipo no es válido. Inténtalo de nuevo.");
            } else {
                // Si es un nombre válido, comprueba si existe:
                if (teamController.existTeamName(nombreEquipo)) {
                    int equip_id = teamController.getTeamId(nombreEquipo);
                    if (playerController.changeNameTeamOfPlayer(player.getJugador_id(), equip_id)) {
                        System.out.println("El jugador ha sido traspasado exitosamente al equipo.");
                        salir = true;
                    } else {
                        System.out.println("Se ha producido un error al traspasar el jugador.");
                    }
                } else {
                    // Si no existe:
                    System.out.println("El nombre del equipo no existe. Inténtalo de nuevo.");
                }
            }
        }
        while (!salir);
    }
}
