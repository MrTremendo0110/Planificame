import java.util.Scanner;

public class alumno {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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

        System.out.println(ANSI_YELLOW +
                "██████████                                           \n" +
                "██████████                               \n" +
                "███    ███                               \n" +
                "███    ███                               \n" +
                "██████████                         \n" +
                "██████████                      \n" +
                "███    ███                            \n" +
                "███    ███               \n" +
                ANSI_RESET);


        for (i = 0; i < 10; i++) {
            if (seguirPrograma) {
                System.out.println("Ingresa el nombre de la materia " + (i + 1) + " (o escribe 'salir' para terminar):");
                materiaIngresada = scanner.nextLine();

                if (materiaIngresada.equalsIgnoreCase("salir")) {
                    seguirPrograma = false;
                } else {

                    materias[i] = materiaIngresada;


                    for (j = 0; j < 3; j++) {
                        boolean calificacionValida = false;

                        do {
                            System.out.print("Ingresa la calificacion " + (j + 1) + " para " + materias[i] + " (entre 0 y 10): ");


                            if (scanner.hasNextDouble()) {
                                calificacion = scanner.nextDouble();

                                scanner.nextLine();


                                if (calificacion >= 0.0 && calificacion <= 10.0) {
                                    calificaciones[i][j] = calificacion;
                                    sumaTotalCalificaciones += calificacion;
                                    calificacionValida = true;
                                } else {
                                    System.out.println(">> Calificacion no valida. Por favor, ingresa un numero entre 0 y 10.");
                                }
                            } else {

                                System.out.println(">> Entrada invalida. Por favor, ingresa un numero.");

                                scanner.nextLine();
                                calificacion = -1.0;
                            }
                        } while (!calificacionValida);
                    }


                    promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;
                    System.out.printf(">> El promedio de la materia %s es: %.2f%n", materias[i], promedioMateria);
                    System.out.println();

                    numMaterias++;
                }
            }
        }
        System.out.println("\n*** Resumen de calificaciones ***");

        if (numMaterias > 0) {

            for (i = 0; i < numMaterias; i++) {
                System.out.println("Materia: " + materias[i]);
                System.out.printf("  Calificaciones: %.2f, %.2f, %.2f%n",
                        calificaciones[i][0],
                        calificaciones[i][1],
                        calificaciones[i][2]);

                promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;
                System.out.printf("  Promedio: %.2f%n", promedioMateria);
                System.out.println("--------------------------------");
            }


            promedioGeneral = sumaTotalCalificaciones / (numMaterias * 3);
            System.out.printf("*** El promedio general del alumno es: %.2f ***%n", promedioGeneral);
        } else {
            System.out.println("No se ingresaron calificaciones.");
        }


        System.out.println("\nPresione Enter para volver al Menú Principal...");
        scanner.nextLine();
    }
}