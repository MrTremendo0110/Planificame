import java.util.Scanner;

/* =========================================================
   MÓDULO REGISTRO
   Solicita la información personal del alumno
   ========================================================= */

public class registro {

    // Variables globales para la información personal del alumno
    public static String nombre;
    public static String turnoEsc;
    public static int edad;
    public static int semestre;
    public static Scanner scanner = new Scanner(System.in);

    // Variables globales para el color de letra en la consola
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

        // CICLO PRINCIPAL, permite corregir los datos si es que son incorrectos
        while (bandera.equals("n")) {

            System.out.println(ANSI_PURPLE + "\n===== REGISTRO DE INFORMACIÓN PERSONAL =====" + ANSI_RESET);

    	// Registro de los datos personales del alumno
            nombre = leerNombre();
            edad = leerEdad();
            semestre = leerSemestre();
            turnoEsc = leerTurno();

    	// Confirmación de los datos personales
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


    // =============================================================
    // MÉTODOS PARA EL REGISTRO DE LA INFORMACIÓN PERSONAL DEL ALUMNO
    // =============================================================

    public static String leerNombre() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Cuál es su nombre?: " + ANSI_RESET);
            nombre = scanner.next();

		// En caso de no escribirse nada en esta sección, aparece este error.
            if (nombre.isEmpty()) {
                System.out.println(ANSI_PURPLE + " Error: No ha escrito nada, vuelva a intentarlo." + ANSI_RESET);
            } else {
                return nombre;
            }
        }
    }

    public static int leerEdad() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Cuál es su edad? (13-21): " + ANSI_RESET);
            edad = scanner.nextInt();

		// Si la edad no entra en el rango especificado, aparece este error
            if (edad < 13 || edad > 21) {
                System.out.println(ANSI_PURPLE + " Error: Edad fuera del rango permitido." + ANSI_RESET);
            } else {
                return edad;
            }
        }
    }

    public static int leerSemestre() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿Qué semestre está cursando? (1-6): " + ANSI_RESET);
            semestre = scanner.nextInt();

		// Si el semestre cursado no entra en el rango de 1 a 6, aparece este error
            if (semestre < 1 || semestre > 6) {
                System.out.println(ANSI_PURPLE + " Error: Semestre fuera del rango permitido." + ANSI_RESET);
            } else {
                return semestre;
            }
        }
    }

    public static String leerTurno() {
        while (true) {
            System.out.print(ANSI_YELLOW + "¿En qué turno asiste a la escuela? (matutino/vespertino): " + ANSI_RESET);
            turnoEsc = scanner.next();

		// Si no se escribe matutino o vespertino, aparece este error
            if (!turnoEsc.equalsIgnoreCase("matutino") && // En este caso no importa si la entrada de datos esta en mayúsculas, minúsculas o ambas
                    !turnoEsc.equalsIgnoreCase("vespertino") &&
                    !turnoEsc.equalsIgnoreCase("despertino")) { // por si querías aceptar tu versión
                System.out.println(ANSI_PURPLE + " Error: Turno inválido, escriba matutino o vespertino." + ANSI_RESET);
            } else {
                return turnoEsc.toLowerCase(); // Devuelve el valor del turno en minusculas
            }
        }
    }
}
