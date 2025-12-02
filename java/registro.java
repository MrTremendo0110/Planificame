import java.util.Scanner;

public class registro {

    public static String nombre;
    public static String turnoEsc;
    public static int edad;
    public static int semestre;
    public static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main() {

        String bandera = "n";

        // TÍTULO PRINCIPAL
        System.out.println(ANSI_GREEN +
                        "██████╗░███████╗░██████╗░██╗░██████╗████████╗██████╗░░█████╗░\n" +
                        "██╔══██╗██╔════╝██╔════╝░██║██╔════╝╚══██╔══╝██╔══██╗██╔══██╗\n" +
                        "██████╔╝█████╗░░██║░░██╗░██║╚█████╗░░░░██║░░░██████╔╝██║░░██║\n" +
                        "██╔══██╗██╔══╝░░██║░░╚██╗██║░╚═══██╗░░░██║░░░██╔══██╗██║░░██║\n" +
                        "██║░░██║███████╗╚██████╔╝██║██████╔╝░░░██║░░░██║░░██║╚█████╔╝\n" +
                        "╚═╝░░╚═╝╚══════╝░╚═════╝░╚═╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░\n" +
                ANSI_RESET);

        // CICLO PRINCIPAL
        while (bandera.equals("n")) {

            System.out.println(ANSI_PURPLE + "\n===== REGISTRO DE INFORMACIÓN PERSONAL =====" + ANSI_RESET);

            nombre = leerNombre();
            edad = leerEdad();
            semestre = leerSemestre();
            turnoEsc = leerTurno();

            System.out.println(ANSI_CYAN +
                    "\nHa escrito:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Edad: " + edad + "\n" +
                    "Semestre: " + semestre + "\n" +
                    "Turno: " + turnoEsc + "\n" +
                    ANSI_RESET);

            System.out.print(ANSI_YELLOW + "¿Sus datos son correctos? (s/n): " + ANSI_RESET);
            bandera = scanner.next();
        }

        System.out.println(ANSI_GREEN + "✔ Su información ha sido registrada con éxito." + ANSI_RESET);
    }


    // ==========================
    // MÉTODOS DE VALIDACIÓN
    // ==========================

    public static String leerNombre() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Cuál es su nombre?: " + ANSI_RESET);
            nombre = scanner.next();

            if (nombre.isEmpty()) {
                System.out.println(ANSI_PURPLE + "✘ Error: No ha escrito nada, vuelva a intentarlo." + ANSI_RESET);
            } else {
                return nombre;
            }
        }
    }

    public static int leerEdad() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Cuál es su edad? (13-21): " + ANSI_RESET);
            edad = scanner.nextInt();

            if (edad < 13 || edad > 21) {
                System.out.println(ANSI_PURPLE + "✘ Error: Edad fuera del rango permitido." + ANSI_RESET);
            } else {
                return edad;
            }
        }
    }

    public static int leerSemestre() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Qué semestre está cursando? (1-6): " + ANSI_RESET);
            semestre = scanner.nextInt();

            if (semestre < 1 || semestre > 6) {
                System.out.println(ANSI_PURPLE + "✘ Error: Semestre fuera del rango permitido." + ANSI_RESET);
            } else {
                return semestre;
            }
        }
    }

    public static String leerTurno() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿En qué turno asiste a la escuela? (matutino/vespertino): " + ANSI_RESET);
            turnoEsc = scanner.next();

            if (!turnoEsc.equalsIgnoreCase("matutino") &&
                    !turnoEsc.equalsIgnoreCase("vespertino") &&
                    !turnoEsc.equalsIgnoreCase("despertino")) { // por si querías aceptar tu versión
                System.out.println(ANSI_PURPLE + "✘ Error: Turno inválido, escriba matutino o vespertino." + ANSI_RESET);
            } else {
                return turnoEsc.toLowerCase();
            }
        }
    }
}
