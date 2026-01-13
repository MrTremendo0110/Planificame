import java.util.Scanner;

/* =========================================================
   Modulo Principal
   Controla el flujo y contiene el menú del programa
   ========================================================= */

public class principal {

    // Constantes para el color de letra en la consola
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {

        // Llamada al módulo de registro
        registro.main();

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        // Inicio del bucle del menu
        while (continuar) {

            // Decoración del título en ASCII
            System.out.println(ANSI_CYAN +
                    "███╗░░░███╗███████╗███╗░░██╗██╗░░░██╗  ██████╗░██████╗░██╗███╗░░██╗░█████╗░██╗██████╗░░█████╗░██╗░░░░░\n" +
                    "████╗░████║██╔════╝████╗░██║██║░░░██║  ██╔══██╗██╔══██╗██║████╗░██║██╔══██╗██║██╔══██╗██╔══██╗██║░░░░░\n" +
                    "██╔████╔██║█████╗░░██╔██╗██║██║░░░██║  ██████╔╝██████╔╝██║██╔██╗██║██║░░╚═╝██║██████╔╝███████║██║░░░░░\n" +
                    "██║╚██╔╝██║██╔══╝░░██║╚████║██║░░░██║  ██╔═══╝░██╔══██╗██║██║╚████║██║░░██╗██║██╔═══╝░██╔══██║██║░░░░░\n" +
                    "██║░╚═╝░██║███████╗██║░╚███║╚██████╔╝  ██║░░░░░██║░░██║██║██║░╚███║╚█████╔╝██║██║░░░░░██║░░██║███████╗\n" +
                    "╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝░╚═════╝░  ╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚════╝░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚══════╝\n" +
                    ANSI_RESET);

            // Crear el cuadro con las opciones disponibles del programa
            String opciones = "\n" +
                    "  ****************************  \n" +
                    "  *   1. Horario              *  \n" +
                    "  *   2. Calendario           *  \n" +
                    "  *   3. Tareas               *  \n" +
                    "  *   4. Alumno               *  \n" +
                    "  *   Cualquier otra tecla para salir *  \n" +
                    "  ****************************  \n";

            // Centramos el texto
            String lineas[] = opciones.split("\n");
            int terminalWidth = 80;  // Supuesto ancho de la consola
            for (String linea : lineas) {
                int padding = (terminalWidth - linea.length()) / 2;
                String paddedLine = " ".repeat(padding) + linea;
                System.out.println(paddedLine);
            }

            // Solicitar opción del menú
            System.out.print(ANSI_CYAN + "Seleccione una opción: " + ANSI_RESET);
            String opcion = sc.next();

            // Condicional switch para las opciones del menú, mandando a llamar al módulo correspondiente
            switch (opcion) {
                case "1":
                    Horario.main();
                    break;
                case "2":
                    calendario.main();
                    break;
                case "3":
                    tarea.main();
                    break;
                case "4":
                    alumno.main();
                    break;
                default:   
		// Si no se selecciona ninguna de las opciones anteriores el programa finaliza
                    System.out.println(ANSI_RED + "Saliendo del sistema. ¡Hasta luego!" + ANSI_RESET);
                    continuar = false;
                    break;
            }
        }
    }
}
