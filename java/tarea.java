import java.util.Scanner;

public class tarea {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main() {

        final int MAX_TAREAS = 50;

        String[] nombresTareas = new String[MAX_TAREAS];
        long[] tiemposVencimiento = new long[MAX_TAREAS];

        int num_tareas = 0;

        Scanner sc = new Scanner(System.in);

        boolean corre = true;

        // Header with color
        System.out.println(ANSI_PURPLE +
                "████████████████████    ████████████████████     ███████████████     ████████████████████   ████████████████████     ████████████████████                    \n" +
                "████████████████████    ████████████████████   ███████████████████   ████████████████████   ████████████████████     ████████████████████                  \n" +
                "       ██████           ███              ███   ███           ████    ██                     ███              ███     ███                   \n" +
                "       ██████           ███              ███   ███         ████      ██                     ███              ███     ███████████████████                 \n" +
                "       ██████           ████████████████████   ██████████████        ████████████████████   ████████████████████     ███████████████████                                 \n" +
                "       ██████           ███              ███   ███       ████        ██                     ███              ███                      ███ \n" +
                "       ██████           ███              ███   ███         ████      ████████████████████   ███              ███     ███████████████████                                   \n" +
                "       ██████           ███              ███   ███           ████    ████████████████████   ███              ███     ███████████████████                                     \n" +
                ANSI_RESET);

        while (corre) {
            // Menú decorado con un borde
            System.out.println(ANSI_CYAN + "╔══════════════════════════════════════════════╗");
            System.out.println("║              " + ANSI_YELLOW + "Tareas Pendientes" + ANSI_CYAN + "               ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1. " + ANSI_GREEN + "Agregar una nueva tarea (D/H/M/S)" + ANSI_RESET + "         ║");
            System.out.println("║ 2. " + ANSI_GREEN + "Volver al Menú Principal" + ANSI_RESET + "                  ║");
            System.out.println("║ 3. " + ANSI_GREEN + "Listar tareas pendientes" + ANSI_RESET + "                  ║");
            System.out.println("╚══════════════════════════════════════════════╝");

            System.out.print(ANSI_CYAN + "> " + ANSI_RESET);
            String opcion = sc.next();
            sc.nextLine();

            switch (opcion) {
                case "1":
                    // Agregar nueva tarea
                    if (num_tareas < MAX_TAREAS) {
                        System.out.print("Agrega descripcion de la tarea: ");
                        String tareaName = sc.nextLine();

                        try {
                            System.out.print("Dias restantes: ");
                            long dias = Long.parseLong(sc.nextLine());
                            System.out.print("Horas restantes: ");
                            long horas = Long.parseLong(sc.nextLine());
                            System.out.print("Minutos restantes: ");
                            long minutos = Long.parseLong(sc.nextLine());
                            System.out.print("Segundos restantes: ");
                            long segundos = Long.parseLong(sc.nextLine());

                            long duracionTotalMillis =
                                    (dias * 24 * 60 * 60 * 1000L) +
                                            (horas * 60 * 60 * 1000L) +
                                            (minutos * 60 * 1000L) +
                                            (segundos * 1000L);

                            if (duracionTotalMillis <= 0) {
                                System.out.println(ANSI_RED + "La duración debe ser mayor a cero." + ANSI_RESET);
                                break;
                            }

                            nombresTareas[num_tareas] = tareaName;
                            tiemposVencimiento[num_tareas] = System.currentTimeMillis() + duracionTotalMillis;

                            num_tareas++;
                            System.out.println(ANSI_GREEN + ">> Tarea agregada con éxito." + ANSI_RESET);

                        } catch (NumberFormatException e) {
                            System.out.println(ANSI_RED + "Error: Asegúrese de ingresar solo números enteros válidos para el tiempo." + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_RED + "Limite de tareas alcanzado." + ANSI_RESET);
                    }

                    System.out.println("(Presione Enter para continuar)");
                    sc.nextLine();
                    break;

                case "2":
                    System.out.println(ANSI_CYAN + "Volviendo al menú principal..." + ANSI_RESET);
                    corre = false;
                    break;

                case "3":
                    System.out.println(ANSI_YELLOW + ">>>>> Tareas Pendientes <<<<" + ANSI_RESET);
                    if (num_tareas == 0) {
                        System.out.println(ANSI_RED + "No hay tareas pendientes." + ANSI_RESET);
                    } else {
                        long ahora = System.currentTimeMillis();
                        for (int i = 0; i < num_tareas; i++) {
                            long restanteMillis = tiemposVencimiento[i] - ahora;
                            String tiempoFormateado;

                            if (restanteMillis <= 0) {
                                tiempoFormateado = ANSI_RED + "¡TERMINADO!" + ANSI_RESET;
                            } else {
                                // Formato del tiempo restante
                                long segundos = restanteMillis / 1000;
                                long minutos = segundos / 60;
                                long horas = minutos / 60;
                                long dias = horas / 24;
                                segundos %= 60;
                                minutos %= 60;
                                horas %= 24;
                                tiempoFormateado = String.format("%d d, %d h, %d m, %d s", dias, horas, minutos, segundos);
                            }
                            System.out.println((i + 1) + ". " + nombresTareas[i] + " | Restante: " + tiempoFormateado);
                        }
                    }
                    System.out.println("------------------------------------------------*");

                    System.out.println("(Presione Enter para continuar)");
                    sc.nextLine();
                    break;

                default:
                    System.out.println(ANSI_RED + "Escoja una opción correcta." + ANSI_RESET);
                    System.out.println("(Presione Enter para continuar)");
                    sc.nextLine();
            }
        }

    }
}
