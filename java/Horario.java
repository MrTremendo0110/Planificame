import java.util.*;

public class Horario {
    static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    static String[] matEsc;
    static String[][] matHorEnt;
    static String[][] matHorSal;

    static String[] actExt;
    static String[][] actHorEnt;
    static String[][] actHorSal;

    public static void main() {
        System.out.println(ANSI_PURPLE +
                "███       ███      ████████     ██████████    ██████████   ██████████    █████████     ████████                                         \n" +
                "███       ███     ███    ███    ██████████    ██████████   ██████████    █████████    ███    ███                            \n" +
                "███       ███    ███      ███   ███     ███   ███    ███   ███     ███      ███      ███      ███                              \n" +
                "█████████████    ███      ███   ███     ███   ███    ███   ███     ███      ███      ███      ███                            \n" +
                "█████████████    ███      ███   ██████████    ██████████   ██████████       ███      ███      ███                      \n" +
                "███       ███    ███      ███   ███  ███      ██████████   ███  ███         ███      ███      ███                   \n" +
                "███       ███     ███    ███    ███   ███     ███    ███   ███   ███     █████████    ███    ███                         \n" +
                "███       ███      ████████     ███    ███    ███    ███   ███    ███    █████████     ████████              \n" +
                ANSI_RESET);

        while (true) {
            System.out.println(ANSI_YELLOW + "\n=== MENÚ PRINCIPAL DE HORARIO ===");
            System.out.println("1.- Registro de horario escolar");
            System.out.println("2.- Registro de actividades extracurriculares");
            System.out.println("3.- Ver horario completo");
            System.out.println("4.- Salir");
            System.out.print(ANSI_RESET + "Seleccione una opción: ");

            int opcOp = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.println(ANSI_GREEN + "Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    // Registro de materias (cuántas cursa, nombre y qué días toca)
    public static void regHorEsc() {
        System.out.println(ANSI_YELLOW + "Haz seleccionado el registro de horario escolar.");
        System.out.print("¿Cuántas materias está cursando? ");
        int numMat = scanner.nextInt();
        scanner.nextLine();

        matEsc = new String[numMat];
        matHorEnt = new String[numMat][5];
        matHorSal = new String[numMat][5];

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        for (int x = 0; x < numMat; x++) {
            System.out.print("Ingrese el nombre de la materia " + (x + 1) + ": ");
            matEsc[x] = scanner.nextLine();

            for (int i = 0; i < 5; i++) {
                System.out.print("¿La materia \"" + matEsc[x] + "\" se cursa el " + dias[i] + "? (s/n) ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    matHorEnt[x][i] = obtenerHora("entrada", dias[i]);
                    matHorSal[x][i] = obtenerHora("salida", dias[i]);
                } else {
                    matHorEnt[x][i] = "No se encuentra";
                    matHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println(ANSI_GREEN + "Registro de materias completo.");
    }

    // Registro de actividades extracurriculares
    public static void regActExt() {
        System.out.println(ANSI_YELLOW + "Haz seleccionado el registro de actividades extracurriculares.");
        System.out.print("¿Qué cantidad de actividades extracurriculares realiza? ");
        int numAct = scanner.nextInt();
        scanner.nextLine();

        actExt = new String[numAct];
        actHorEnt = new String[numAct][5];
        actHorSal = new String[numAct][5];

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        for (int x = 0; x < numAct; x++) {
            System.out.print("Ingrese el nombre de la actividad " + (x + 1) + ": ");
            actExt[x] = scanner.nextLine();

            for (int i = 0; i < 5; i++) {
                System.out.print("¿La actividad \"" + actExt[x] + "\" se realiza el " + dias[i] + "? (s/n) ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    actHorEnt[x][i] = obtenerHora("entrada", dias[i]);
                    actHorSal[x][i] = obtenerHora("salida", dias[i]);
                } else {
                    actHorEnt[x][i] = "No se encuentra";
                    actHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println(ANSI_GREEN + "Registro de actividades completo.");
    }

    // Método para ingresar horarios con validación
    public static String obtenerHora(String tipo, String dia) {
        String hora = "";
        boolean horaValida = false;

        while (!horaValida) {
            System.out.print("Ingrese el horario de " + tipo + " (en formato HH:mm) para el día " + dia + ": ");
            hora = scanner.nextLine();
            if (hora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
                horaValida = true;
            } else {
                System.out.println("Error: El formato de la hora es incorrecto. Intente de nuevo.");
            }
        }
        return hora;
    }

    // Mostrar horario completo
    public static void verHorarioCompleto() {
        if (matEsc != null) {
            imprimirResumenPorDia(matEsc, matHorEnt, matHorSal, "Materia");
        } else {
            System.out.println(ANSI_RED + "No se ha registrado horario escolar aún." + ANSI_RESET);
        }

        if (actExt != null) {
            imprimirResumenPorDia(actExt, actHorEnt, actHorSal, "Actividad");
        } else {
            System.out.println(ANSI_RED + "No se han registrado actividades extracurriculares todavía." + ANSI_RESET);
        }
    }

    // Imprimir resumen de ambos horarios
    public static void imprimirResumenPorDia(String[] nombres, String[][] horEnt, String[][] horSal, String tipo) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        System.out.println(ANSI_PURPLE + "\n===== RESUMEN DE " + tipo.toUpperCase() + "S POR DÍA =====" + ANSI_RESET);

        for (int i = 0; i < 5; i++) {
            List<String[]> listaDia = new ArrayList<>();

            for (int x = 0; x < nombres.length; x++) {
                if (!horEnt[x][i].equals("No se encuentra")) {
                    listaDia.add(new String[]{nombres[x], horEnt[x][i], horSal[x][i]});
                }
            }

            // Ordenar por hora de entrada (asumiendo formato HH:mm)
            listaDia.sort((a, b) -> a[1].compareTo(b
