import java.util.Scanner;

public class Horario {

    static Scanner scanner = new Scanner(System.in);

    static String[] materias;
    static String[][] entradaMat;
    static String[][] salidaMat;

    static String[] actividades;
    static String[][] entradaAct;
    static String[][] salidaAct;

    static String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n----- MENÚ PRINCIPAL -----");
            System.out.println("1.- Registro de horario escolar");
            System.out.println("2.- Registro de actividades extracurriculares");
            System.out.println("3.- Mostrar horarios registrados");
            System.out.println("4.- Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    regHorEsc();
                    break;
                case 2:
                    regActExt();
                    break;
                case 3:
                    mostrarHorarios();
                    break;
                case 4:
                    System.out.println("Adiós...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    // horario escolar
    public static void regHorEsc() {
        System.out.println("\n--- Registro de horario escolar ---");
        System.out.print("¿Cuántas materias está cursando? ");

        int numMat = scanner.nextInt();
        scanner.nextLine();

        materias = new String[numMat];
        entradaMat = new String[numMat][5];
        salidaMat = new String[numMat][5];

        for (int m = 0; m < numMat; m++) {
            System.out.print("Nombre de la materia " + (m + 1) + ": ");
            materias[m] = scanner.nextLine();

            for (int d = 0; d < 5; d++) {
                System.out.println("Horario para " + dias[d] + ":");
                System.out.print("Entrada: ");
                entradaMat[m][d] = scanner.nextLine();
                System.out.print("Salida: ");
                salidaMat[m][d] = scanner.nextLine();
            }
        }

        System.out.println("\nHorarios registrados con éxito.");
    }

    // actividad extracurricular
    public static void regActExt() {
        System.out.println("\n--- Registro de actividades extracurriculares ---");
        System.out.print("¿Cuántas actividades realiza? ");

        int numAct = scanner.nextInt();
        scanner.nextLine();

        actividades = new String[numAct];
        entradaAct = new String[numAct][5];
        salidaAct = new String[numAct][5];

        for (int a = 0; a < numAct; a++) {
            System.out.print("Nombre de la actividad " + (a + 1) + ": ");
            actividades[a] = scanner.nextLine();

            for (int d = 0; d < 5; d++) {
                System.out.println("Horario para " + dias[d] + ":");
                System.out.print("Entrada: ");
                entradaAct[a][d] = scanner.nextLine();
                System.out.print("Salida: ");
                salidaAct[a][d] = scanner.nextLine();
            }
        }

        System.out.println("\nActividades registradas con éxito.");
    }

    // regitro de horario
    public static void mostrarHorarios() {

        System.out.println("\n----- HORARIOS REGISTRADOS -----");

        // Mostrar materias
        if (materias != null) {
            System.out.println("\n--- Materias ---");
            for (int m = 0; m < materias.length; m++) {
                System.out.println("\nMateria: " + materias[m]);
                for (int d = 0; d < 5; d++) {
                    System.out.println("  " + dias[d] + ": " +
                            entradaMat[m][d] + " - " + salidaMat[m][d]);
                }
            }
        } else {
            System.out.println("\nNo hay materias registradas.");
        }

        // Mostrar actividades
        if (actividades != null) {
            System.out.println("\n--- Actividades Extracurriculares ---");
            for (int a = 0; a < actividades.length; a++) {
                System.out.println("\nActividad: " + actividades[a]);
                for (int d = 0; d < 5; d++) {
                    System.out.println("  " + dias[d] + ": " +
                            entradaAct[a][d] + " - " + salidaAct[a][d]);
                }
            }
        } else {
            System.out.println("\nNo hay actividades registradas.");
        }

        System.out.println("\n-------------------------------");
    }
}