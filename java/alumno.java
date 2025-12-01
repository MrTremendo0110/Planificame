import java.util.Scanner;

public class alumno {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main() {

        String[] materias = new String[10];
        double[][] calificaciones = new double[10][3];

        int i, j;
        int numMaterias = 0;
        double sumaTotalCalificaciones = 0.0;
        double calificacion;
        double promedioMateria;
        double promedioGeneral;
        String materiaIngresada;
        boolean seguirPrograma = true;

        Scanner scanner = new Scanner(System.in);

        // Título decorado en ASCII
        System.out.println(ANSI_CYAN +
                "██████████   ███        ███     ███   ███     ███   ███      ███      ████████ \n" +
                "██████████   ███        ███     ███   ████   ████   ████     ███     ███    ███\n" +
                "███    ███   ███        ███     ███   ███ █ █ ███   █████    ███    ███      ███\n" +
                "███    ███   ███        ███     ███   ███  █  ███   ███ ██   ███    ███      ███\n" +
                "██████████   ███        ███     ███   ███     ███   ███  ██  ███    ███      ███\n" +
                "██████████   ███        ███     ███   ███     ███   ███   ██ ███    ███      ███\n" +
                "███    ███   ███         ███   ███    ███     ███   ███    █████     ███    ███\n" +
                "███    ███   █████████    ███████     ███     ███   ███     ████      ████████\n" +
                ANSI_RESET);

        for (i = 0; i < 10; i++) {
            if (seguirPrograma) {
                System.out.println(ANSI_GREEN + "¡Bienvenido al sistema de calificaciones! \n");
                System.out.println("Ingresa el nombre de la materia " + (i + 1) + " (o escribe 'salir' para terminar):");
                materiaIngresada = scanner.nextLine();

                if (materiaIngresada.equalsIgnoreCase("salir")) {
                    seguirPrograma = false;
                } else {
                    materias[i] = materiaIngresada;

                    for (j = 0; j < 3; j++) {
                        boolean calificacionValida = false;

                        do {
                            System.out.print(ANSI_YELLOW + "Ingresa la calificación " + (j + 1) + " para " + materias[i] + " (0 a 10): " + ANSI_RESET);

                            if (scanner.hasNextDouble()) {
                                calificacion = scanner.nextDouble();
                                scanner.nextLine();

                                if (calificacion >= 0.0 && calificacion <= 10.0) {
                                    calificaciones[i][j] = calificacion;
                                    sumaTotalCalificaciones += calificacion;
                                    calificacionValida = true;
                                } else {
                                    System.out.println(ANSI_RED + ">> Calificación no válida. Debe ser entre 0 y 10." + ANSI_RESET);
                                }
                            } else {
                                System.out.println(ANSI_RED + ">> Entrada inválida. Ingresa un número." + ANSI_RESET);
                                scanner.nextLine();
                            }
                        } while (!calificacionValida);
                    }

                    // Elegir método de promedio
                    int opcion;
                    do {
                        System.out.println(ANSI_CYAN + "\n¿Cómo deseas calcular el promedio de " + materias[i] + "?" + ANSI_RESET);
                        System.out.println("1) Promedio normal");
                        System.out.println("2) Promedio con porcentajes");
                        System.out.print(ANSI_GREEN + "Elige una opción: " + ANSI_RESET);

                        opcion = scanner.nextInt();
                        scanner.nextLine();

                    } while (opcion != 1 && opcion != 2);

                    if (opcion == 1) {
                        // PROMEDIO NORMAL
                        promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;

                    } else {
                        // PROMEDIO CON PORCENTAJES
                        double p1, p2, p3;

                        System.out.println(ANSI_YELLOW + "Ingresa el porcentaje de cada calificación (deben sumar 100):" + ANSI_RESET);

                        do {
                            System.out.print("Porcentaje 1: ");
                            p1 = scanner.nextDouble();
                            System.out.print("Porcentaje 2: ");
                            p2 = scanner.nextDouble();
                            System.out.print("Porcentaje 3: ");
                            p3 = scanner.nextDouble();
                            scanner.nextLine();

                            if (p1 + p2 + p3 != 100) {
                                System.out.println(ANSI_RED + ">> Los porcentajes deben sumar 100. Intenta de nuevo." + ANSI_RESET);
                            }

                        } while (p1 + p2 + p3 != 100);

                        promedioMateria =
                                calificaciones[i][0] * (p1 / 100) +
                                        calificaciones[i][1] * (p2 / 100) +
                                        calificaciones[i][2] * (p3 / 100);
                    }

                    System.out.printf(ANSI_CYAN + ">> El promedio de la materia %s es: %.2f%n" + ANSI_RESET, materias[i], promedioMateria);
                    System.out.println();

                    numMaterias++;
                }
            }
        }

        // Resumen
        System.out.println(ANSI_GREEN + "\n*** Resumen de calificaciones ***" + ANSI_RESET);

        if (numMaterias > 0) {

            for (i = 0; i < numMaterias; i++) {
                System.out.println(ANSI_YELLOW + "Materia: " + materias[i] + ANSI_RESET);
                System.out.printf("  Calificaciones: %.2f, %.2f, %.2f%n",
                        calificaciones[i][0],
                        calificaciones[i][1],
                        calificaciones[i][2]);

                promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;
                System.out.printf("  Promedio de la materia: %.2f%n", promedioMateria);
                System.out.println("--------------------------------");
            }

            promedioGeneral = sumaTotalCalificaciones / (numMaterias * 3);
            System.out.printf(ANSI_CYAN + "*** El promedio general del alumno es: %.2f ***%n" + ANSI_RESET, promedioGeneral);
        } else {
            System.out.println(ANSI_RED + "No se ingresaron calificaciones." + ANSI_RESET);
        }

        System.out.println(ANSI_GREEN + "\nPresione Enter para volver al Menú Principal..." + ANSI_RESET);
        scanner.nextLine();
    }
}
