import java.util.*;

/* =========================================================
   MÓDULO HORARIO
   Gestiona materias y actividades extracurriculares
   ========================================================= */

public class Horario {
    static Scanner scanner = new Scanner(System.in);

    // Variables globales para el color de letra en la consola
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    // Arreglos para materias y horarios
    static String[] matEsc;
    static String[][] matHorEnt;
    static String[][] matHorSal;

    static String[] actExt;
    static String[][] actHorEnt;
    static String[][] actHorSal;

    public static void main() {
        // Título decorado en formato ASCII
        System.out.println(ANSI_PURPLE +
                "██╗░░██╗░█████╗░██████╗░░█████╗░██████╗░██╗░█████╗░\n" +
                "██║░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔══██╗\n" +
                "███████║██║░░██║██████╔╝███████║██████╔╝██║██║░░██║\n" +
                "██╔══██║██║░░██║██╔══██╗██╔══██║██╔══██╗██║██║░░██║\n" +
                "██║░░██║╚█████╔╝██║░░██║██║░░██║██║░░██║██║╚█████╔╝\n" +
                "╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░╚════╝░" +
                ANSI_RESET);

        while (true) {

            // Menú del módulo
            System.out.println(ANSI_YELLOW + "\n=== MENÚ PRINCIPAL DE HORARIO ===");
            System.out.println("1.- Registro de horario escolar");
            System.out.println("2.- Registro de actividades extracurriculares");
            System.out.println("3.- Ver horario completo");
            System.out.println("4.- Salir");
            System.out.print(ANSI_RESET + "Seleccione una opción: ");

            int opcOp;

            // Error en caso de que la entrada de datos sea inválida
            if (!scanner.hasNextInt()) {
                System.out.println(ANSI_RED + "Entrada inválida. Debe ser un número." + ANSI_RESET);
                scanner.next();
                continue;
            }

            // Control de opciones
            opcOp = scanner.nextInt();

            switch (opcOp) {
                case 1:
                    regHorEsc();
                    break;
                case 2:
                    regActExt();
                    break;
                case 3:
                    verHorarioCompleto();
                    break;
                case 4:
                    System.out.println(ANSI_GREEN + "Saliendo..." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_RED + "Opción inválida, intente de nuevo." + ANSI_RESET);
            }
        }
    }

    // Registro de materias escolares
    public static void regHorEsc() {
        System.out.println(ANSI_YELLOW + "Haz seleccionado el registro de horario escolar." + ANSI_RESET);
        System.out.print("¿Cuántas materias está cursando? ");
        int numMat = scanner.nextInt();
        scanner.nextLine();

        matEsc = new String[numMat];
        matHorEnt = new String[numMat][5];
        matHorSal = new String[numMat][5];

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        // Lógica para la captura de los datos
        for (int x = 0; x < numMat; x++) { // Siempre que x sea menor al número de materias se ejecutará este bucle
            System.out.print("Ingrese el nombre de la materia " + (x + 1) + ": ");
            matEsc[x] = scanner.nextLine();

            for (int i = 0; i < 5; i++) { // En el mismo bucle anterior, siempre que i sea menor a 5 (parea cada día de la semana sin contar sabado ni domingo) se ejecutará este bucle
                System.out.print("¿La materia \"" + matEsc[x] + "\" se cursa el " + dias[i] + "? (s/n) ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
			// Se registran los datos de entrada y salida llamando la función "obtenerHora" posteriormente definida, dentro del número de día (i) de la semana
			// La variable x se refiere a la materia y la variable i al día de la semana, los datos se almacenan en un arreglo bidimensional (matHorEnt y matHorSal)
                    matHorEnt[x][i] = obtenerHora("entrada", dias[i]);
                    matHorSal[x][i] = obtenerHora("salida", dias[i]);
                } else { // En caso de que la respuesta sea negativa el valor "No se encuentra" se almacena en dicho día (i)
                    matHorEnt[x][i] = "No se encuentra";
                    matHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println(ANSI_GREEN + "Registro de materias completo." + ANSI_RESET);
    }

    // Registro de actividades extracurriculares
    public static void regActExt() {
        System.out.println(ANSI_YELLOW + "Haz seleccionado el registro de actividades extracurriculares." + ANSI_RESET);
        System.out.print("¿Cuántas actividades realiza? ");
        int numAct = scanner.nextInt();
        scanner.nextLine();

        actExt = new String[numAct];
        actHorEnt = new String[numAct][5];
        actHorSal = new String[numAct][5];

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        // Lógica para la captura de los datos
        for (int x = 0; x < numAct; x++) { // Siempre que x sea menor al número de actividades se ejecutará este bucle
            System.out.print("Ingrese el nombre de la actividad " + (x + 1) + ": ");
            actExt[x] = scanner.nextLine();

            for (int i = 0; i < 5; i++) { // En el mismo bucle anterior, siempre que i sea menor a 5 (parea cada día de la semana sin contar sabado ni domingo) se ejecutará este bucle

                System.out.print("¿La actividad \"" + actExt[x] + "\" se realiza el " + dias[i] + "? (s/n) ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
		// Se registran los datos de entrada y salida llamando la función "obtenerHora" posteriormente definida, dentro del número de día (i) de la semana
		// La variable x se refiere a la materia y la variable i al día de la semana, los datos se almacenan en un arreglo bidimensional (matHorEnt y matHorSal)
                    actHorEnt[x][i] = obtenerHora("entrada", dias[i]);
                    actHorSal[x][i] = obtenerHora("salida", dias[i]);
                } else { // En caso de que la respuesta sea negativa el valor "No se encuentra" se almacena en dicho día (i)
                    actHorEnt[x][i] = "No se encuentra";
                    actHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println(ANSI_GREEN + "Registro de actividades completo." + ANSI_RESET);
    }

    // Validación de hora
    public static String obtenerHora(String tipo, String dia) {
        String hora;
        while (true) {
            System.out.print("Ingrese la hora de " + tipo + " (HH:mm) para " + dia + ": ");
            hora = scanner.nextLine();

            if (hora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {  // Se valida que el formato de hora sea el adecuado
                return hora;
            }

            System.out.println(ANSI_RED + "Error: Formato incorrecto, intente de nuevo." + ANSI_RESET);
        }
    }

    // Resumen final decorado
    public static void verHorarioCompleto() {
        if (matEsc != null)
            imprimirResumenPorDia(matEsc, matHorEnt, matHorSal, "Materia");
        else
            System.out.println(ANSI_RED + "No se ha registrado horario escolar aún." + ANSI_RESET);

        if (actExt != null)
            imprimirResumenPorDia(actExt, actHorEnt, actHorSal, "Actividad");
        else
            System.out.println(ANSI_RED + "No se han registrado actividades aún." + ANSI_RESET);
    }

    // Imprimir por día
    public static void imprimirResumenPorDia(String[] nombres, String[][] horEnt, String[][] horSal, String tipo) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        System.out.println(ANSI_PURPLE + "\n========= RESUMEN DE " + tipo.toUpperCase() + "S =========" + ANSI_RESET);

        for (int i = 0; i < 5; i++) {
            System.out.println(ANSI_CYAN + "\n--- " + dias[i].toUpperCase() + " ---" + ANSI_RESET);

            List<String[]> listaDia = new ArrayList<>();

            for (int x = 0; x < nombres.length; x++) {
                if (!horEnt[x][i].equals("No se encuentra")) {
                    listaDia.add(new String[]{nombres[x], horEnt[x][i], horSal[x][i]});
                }
            }

            // Ordenar por hora de entrada
            listaDia.sort((a, b) -> a[1].compareTo(b[1]));

            if (listaDia.isEmpty()) {
                System.out.println("No hay " + tipo.toLowerCase() + "s este día.");
            } else {
                for (String[] dato : listaDia) {
                    System.out.println(ANSI_GREEN + dato[0] + ANSI_RESET +
                            " | Entrada: " + dato[1] +
                            " | Salida: " + dato[2]);
                }
            }
        }
    }
}
