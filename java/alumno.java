import java.util.Scanner;

// Algoritmo ModuloCalificacionesAlumno
//Este programa simula el ingreso de calificaciones de un alumno para un máximo de 10 materias, con 3 calificaciones por materia.
//Calcula y muestra el promedio por materia y el promedio general.

public class alumno {

    public static void main() {
        // Se define un tamaño máximo de 10 materias y 3 calificaciones por materia.
        // Los arr se declaran con un tamaño de 10 materias
        String[] materias = new String[10];
        double[][] calificaciones = new double[10][3];

        // Declaración de variables
        int i, j;
        int numMaterias = 0; // Contará el número de materias que se ingresaron
        double sumaTotalCalificaciones = 0.0;
        double calificacion;
        double promedioMateria;
        double promedioGeneral;
        String materiaIngresada;
        boolean seguirPrograma = true;

        // Inicialización para la entrada de datos
        Scanner scanner = new Scanner(System.in);

        // Ciclo para ingresar las calificaciones de hasta 10 materias
        for (i = 0; i < 10; i++) {
            if (seguirPrograma) {
                System.out.println("Ingresa el nombre de la materia " + (i + 1) + " (o salir para terminar):");
                materiaIngresada = scanner.nextLine();
                if (materiaIngresada.equalsIgnoreCase("salir")) {
                    seguirPrograma = false;
                } else {
                    // Guardar el nombre de la materia en el arr
                    materias[i] = materiaIngresada;

                    // Ciclo para ingresar las 3 calificaciones por materia
                    for (j = 0; j < 3; j++) {
                        boolean calificacionValida = false;
                        // Equivalente a Repetir... Hasta Que
                        do {
                            System.out.println("Ingresa la calificacion " + (j + 1) + " para " + materias[i] + " (entre 0 y 10):");

                            // Verificar si la entrada es un número para evitar errores
                            if (scanner.hasNextDouble()) {
                                calificacion = scanner.nextDouble();
                                // Consumir el salto de línea pendiente después de leer el double
                                scanner.nextLine();

                                // Equivalente a Si calificacion >= 0 Y calificacion <= 10
                                if (calificacion >= 0.0 && calificacion <= 10.0) {
                                    calificaciones[i][j] = calificacion;
                                    sumaTotalCalificaciones += calificacion;
                                    calificacionValida = true;
                                } else {
                                    System.out.println("Calificacion no valida. Por favor, ingresa un numero entre 0 y 10.");
                                }
                            } else {
                                // Si la entrada no es un double
                                System.out.println("Entrada invalida. Por favor, ingresa un numero.");
                                // Consumir la entrada no válida para evitar un bucle infinito
                                scanner.nextLine();
                                calificacion = -1.0;
                            }
                        } while (!calificacionValida);
                    } // Fin del ciclo j (calificaciones)

                    // Calcular y mostrar el promedio de la materia
                    promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;
                    System.out.printf("El promedio de la materia %s es: %.2f%n", materias[i], promedioMateria);
                    System.out.println();

                    numMaterias++; // Incrementar el contador de materias ingresadas
                }
            }
        } // Fin del ciclo i (materias)

        scanner.close();

        // --- Resumen de Calificaciones ---
        System.out.println("*** Resumen de calificaciones ***");

        // Mostrar las calificaciones y promedios individuales de cada materia ingresada
        for (i = 0; i < numMaterias; i++) {
            System.out.println("Materia: " + materias[i]);
            System.out.printf("Calificaciones: %.2f, %.2f, %.2f%n",
                    calificaciones[i][0],
                    calificaciones[i][1],
                    calificaciones[i][2]);

            promedioMateria = (calificaciones[i][0] + calificaciones[i][1] + calificaciones[i][2]) / 3.0;
            System.out.printf("Promedio: %.2f%n", promedioMateria);
            System.out.println();
        }

        // Calcular y mostrar el promedio general del alumno
        if (numMaterias > 0) {
            // El total de calificaciones es numMaterias * 3
            promedioGeneral = sumaTotalCalificaciones / (numMaterias * 3);
            System.out.printf("*** El promedio general del alumno es: %.2f ***%n", promedioGeneral);
        } else {
            System.out.println("No se ingresaron calificaciones.");
        }
    }
}