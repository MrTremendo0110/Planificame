import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;

public class calendario {

    static Scanner scanner = new Scanner(System.in);

    // Arrays y Constantes
    final static int MAX_EVENTOS = 10;
    static String[] titulosEventos = new String[MAX_EVENTOS];
    static String[] tiposEventos = new String[MAX_EVENTOS];
    static int[] diasEventos = new int[MAX_EVENTOS];
    static int[] mesesEventos = new int[MAX_EVENTOS];
    static int[] aniosEventos = new int[MAX_EVENTOS];
    static int[] horasEventos = new int[MAX_EVENTOS];
    static int[] minutosEventos = new int[MAX_EVENTOS];
    static int contadorEventos = 0;

    // Colores
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    public static void main() {

        System.out.println(CYAN +
                "░█████╗░░█████╗░██╗░░░░░███████╗███╗░░██╗██████╗░░█████╗░██████╗░██╗░█████╗░\n" +
                "██╔══██╗██╔══██╗██║░░░░░██╔════╝████╗░██║██╔══██╗██╔══██╗██╔══██╗██║██╔══██╗\n" +
                "██║░░╚═╝███████║██║░░░░░█████╗░░██╔██╗██║██║░░██║███████║██████╔╝██║██║░░██║\n" +
                "██║░░██╗██╔══██║██║░░░░░██╔══╝░░██║╚████║██║░░██║██╔══██║██╔══██╗██║██║░░██║\n" +
                "╚█████╔╝██║░░██║███████╗███████╗██║░╚███║██████╔╝██║░░██║██║░░██║██║╚█████╔╝\n" +
                "░╚════╝░╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░╚════╝░" +
                RESET);

        int opcionMenu = 0;

        LocalDateTime inicio = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println(BLUE + "┌───────────────────────────────┐" + RESET);
        System.out.println(BLUE + "│       SISTEMA DE CALENDARIO   │" + RESET);
        System.out.println(BLUE + "└───────────────────────────────┘" + RESET);
        System.out.println("Fecha actual: " + CYAN + inicio.format(formatoFecha) + RESET);

        esperarTecla();

        do {
            limpiarPantalla();
            System.out.println(PURPLE + "╔═══════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║       MÓDULO DE CALENDARIO    ║" + RESET);
            System.out.println(PURPLE + "╚═══════════════════════════════╝" + RESET);

            System.out.println(CYAN + "1." + RESET + " Registrar nuevo evento");
            System.out.println(CYAN + "2." + RESET + " Ver eventos y cuenta regresiva");
            System.out.println(CYAN + "3." + RESET + " Volver al Menú Principal");
            System.out.println(YELLOW + "────────────────────────────────────" + RESET);
            System.out.print("Seleccione una opción: ");

            try {
                opcionMenu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcionMenu = 0;
            }

            switch (opcionMenu) {

                case 1:
                    registrarNuevoEvento();
                    break;

                case 2:
                    verificarYMostrarEventos();
                    break;

                case 3:
                    System.out.println(GREEN + "Volviendo al menú principal..." + RESET);
                    break;

                default:
                    System.out.println(RED + "Opción no válida. Intente de nuevo." + RESET);
                    esperarTecla();
                    break;
            }

        } while (opcionMenu != 3);

    }

    public static void registrarNuevoEvento() {
        if (contadorEventos >= MAX_EVENTOS) {
            System.out.println(RED + "No se pueden registrar más eventos." + RESET);
            esperarTecla();
            return;
        }

        System.out.println(PURPLE + "╔═════════════════════════════╗" + RESET);
        System.out.println(PURPLE + "║     REGISTRAR NUEVO EVENTO  ║" + RESET);
        System.out.println(PURPLE + "╚═════════════════════════════╝" + RESET);

        System.out.print("Título del evento: ");
        String titulo = scanner.nextLine();

        System.out.print("Tipo (Examen/Proyecto): ");
        String tipo = scanner.nextLine();

        try {
            System.out.print("Día (1-31): ");
            int dia = Integer.parseInt(scanner.nextLine());

            System.out.print("Mes (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine());

            System.out.print("Año: ");
            int anio = Integer.parseInt(scanner.nextLine());

            System.out.print("Hora (0-23): ");
            int hora = Integer.parseInt(scanner.nextLine());

            System.out.print("Minutos (0-59): ");
            int minuto = Integer.parseInt(scanner.nextLine());

            LocalDateTime fechaEvento = LocalDateTime.of(anio, mes, dia, hora, minuto);
            LocalDateTime ahora = LocalDateTime.now();

            if (fechaEvento.isBefore(ahora.truncatedTo(ChronoUnit.MINUTES))) {
                System.out.println(RED + "\n⚠ ¡ADVERTENCIA! La fecha ingresada ya pasó." + RESET);
                System.out.println("El evento no se registrará.");
            } else {
                titulosEventos[contadorEventos] = titulo;
                tiposEventos[contadorEventos] = tipo;
                diasEventos[contadorEventos] = dia;
                mesesEventos[contadorEventos] = mes;
                aniosEventos[contadorEventos] = anio;
                horasEventos[contadorEventos] = hora;
                minutosEventos[contadorEventos] = minuto;

                contadorEventos++;
                System.out.println(GREEN + "\n✔ Evento registrado con éxito." + RESET);
            }

        } catch (Exception e) {
            System.out.println(RED + "\nError: Datos inválidos. Intente de nuevo." + RESET);
        }

        esperarTecla();
    }

    public static void verificarYMostrarEventos() {
        limpiarPantalla();
        System.out.println(CYAN + "\n──── EVENTOS REGISTRADOS ────" + RESET);

        if (contadorEventos == 0) {
            System.out.println(YELLOW + "No hay eventos registrados." + RESET);
            esperarTecla();
            return;
        }

        LocalDateTime ahora = LocalDateTime.now();
        int eliminados = 0;

        int i = 0;
        while (i < contadorEventos) {
            try {
                LocalDateTime fechaEvento = LocalDateTime.of(
                        aniosEventos[i], mesesEventos[i], diasEventos[i],
                        horasEventos[i], minutosEventos[i]);

                System.out.println(YELLOW + "\n──────────────────────────────" + RESET);
                System.out.println("Título: " + BLUE + titulosEventos[i] + RESET);
                System.out.println("Tipo: " + tiposEventos[i]);
                System.out.println("Fecha: " + fechaEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

                if (fechaEvento.isBefore(ahora.truncatedTo(ChronoUnit.MINUTES))) {

                    System.out.println(RED + "🗑 ESTADO: ¡Evento ya pasado! Eliminando..." + RESET);
                    eliminarEvento(i);
                    eliminados++;

                } else {
                    calcularCuentaRegresiva(ahora, diasEventos[i], mesesEventos[i],
                            aniosEventos[i], horasEventos[i], minutosEventos[i]);
                    i++;
                }

            } catch (Exception e) {
                eliminarEvento(i);
                eliminados++;
            }
        }

        if (eliminados > 0)
            System.out.println(RED + "\nSe eliminaron " + eliminados + " eventos caducados." + RESET);

        esperarTecla();
    }

    public static void eliminarEvento(int indice) {
        if (indice < 0 || indice >= contadorEventos) return;

        for (int k = indice; k < contadorEventos - 1; k++) {
            titulosEventos[k] = titulosEventos[k + 1];
            tiposEventos[k] = tiposEventos[k + 1];
            diasEventos[k] = diasEventos[k + 1];
            mesesEventos[k] = mesesEventos[k + 1];
            aniosEventos[k] = aniosEventos[k + 1];
            horasEventos[k] = horasEventos[k + 1];
            minutosEventos[k] = minutosEventos[k + 1];
        }

        contadorEventos--;
    }

    public static void calcularCuentaRegresiva(LocalDateTime ahora, int dia, int mes, int anio, int hora, int minuto) {
        try {
            LocalDateTime fechaEvento = LocalDateTime.of(anio, mes, dia, hora, minuto);
            Duration duracion = Duration.between(ahora, fechaEvento);

            System.out.println(GREEN +
                    " Faltan: " +
                    duracion.toDays() + " días, " +
                    duracion.toHoursPart() + " horas, " +
                    duracion.toMinutesPart() + " minutos." +
                    RESET);

        } catch (Exception e) {
            System.out.println(RED + "Error en el cálculo de la cuenta regresiva." + RESET);
        }
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 2; i++) System.out.println();
    }

    public static void esperarTecla() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
