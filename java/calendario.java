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


    public static void main() {

        int opcionMenu = 0;

        LocalDateTime inicio = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- SISTEMA DE CALENDARIO ---");
        System.out.println("Fecha actual: " + inicio.format(formatoFecha));
        esperarTecla();

        do {

            limpiarPantalla();
            System.out.println("--- MÓDULO DE CALENDARIO ---");
            System.out.println("1. Registrar nuevo evento");
            System.out.println("2. Ver eventos y cuenta regresiva");
            System.out.println("3. Volver al Menú Principal");
            System.out.println("----------------------------");
            System.out.print("Elija una opción: ");

            try {
                String input = scanner.nextLine();
                opcionMenu = Integer.parseInt(input);
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
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    esperarTecla();
                    break;
            }

        } while (opcionMenu != 3);


    }

    public static void registrarNuevoEvento() {
        if (contadorEventos >= MAX_EVENTOS) {
            System.out.println("No se pueden registrar más eventos.");
            esperarTecla();
            return;
        }

        System.out.println("--- REGISTRAR NUEVO EVENTO ---");

        System.out.print("Título del evento: ");
        String titulo = scanner.nextLine();

        System.out.print("Tipo (Examen/Proyecto): ");
        String tipo = scanner.nextLine();

        try {
            System.out.print("Fecha de entrega (Día): ");
            int dia = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha de entrega (Mes): ");
            int mes = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha de entrega (Año): ");
            int anio = Integer.parseInt(scanner.nextLine());

            System.out.print("Hora (0-23): ");
            int hora = Integer.parseInt(scanner.nextLine());

            System.out.print("Minutos (0-59): ");
            int minuto = Integer.parseInt(scanner.nextLine());

            LocalDateTime fechaEvento = LocalDateTime.of(anio, mes, dia, hora, minuto);
            LocalDateTime ahora = LocalDateTime.now();

            if (fechaEvento.isBefore(ahora.truncatedTo(ChronoUnit.MINUTES))) {
                System.out.println("\n ¡ADVERTENCIA! La fecha ingresada (" + fechaEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ") ya pasó.");
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
                System.out.println("\n Evento registrado con éxito.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n Error al ingresar datos numéricos (Día/Mes/Año/Hora/Minuto). El evento no se guardó.");
        } catch (DateTimeException e) {
            System.out.println("\n Error: La fecha ingresada no es válida (ej. Día 30 en Febrero). El evento no se guardó.");
        }
        esperarTecla();
    }


    public static void verificarYMostrarEventos() {
        limpiarPantalla();
        System.out.println("--- EVENTOS REGISTRADOS ---");

        if (contadorEventos == 0) {
            System.out.println("No hay eventos registrados.");
            esperarTecla();
            return;
        }

        LocalDateTime ahoraMismo = LocalDateTime.now();
        int eventosEliminados = 0;

        int i = 0;
        while (i < contadorEventos) {
            try {
                LocalDateTime fechaEvento = LocalDateTime.of(aniosEventos[i], mesesEventos[i], diasEventos[i], horasEventos[i], minutosEventos[i]);

                if (fechaEvento.isBefore(ahoraMismo.truncatedTo(ChronoUnit.MINUTES))) {

                    System.out.println("----------------------------");
                    System.out.println("Título: " + titulosEventos[i]);
                    System.out.println("Fecha: " + fechaEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                    System.out.println("🗑️ ESTADO: ¡El evento ya pasó! Eliminando del registro.");

                    eliminarEvento(i);
                    eventosEliminados++;

                } else {
                    System.out.println("----------------------------");
                    System.out.println("Título: " + titulosEventos[i]);
                    System.out.println("Tipo: " + tiposEventos[i]);
                    System.out.println("Fecha límite: " + fechaEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));


                    calcularCuentaRegresiva(ahoraMismo, diasEventos[i], mesesEventos[i], aniosEventos[i], horasEventos[i], minutosEventos[i]);
                    i++;
                }
            } catch (Exception e) {
                System.out.println(">> ERROR al procesar evento en índice " + i + ". Eliminando.");
                eliminarEvento(i);
                eventosEliminados++;
            }
        }

        if (eventosEliminados > 0) {
            System.out.println("\nSe eliminaron " + eventosEliminados + " eventos pasados.");
        }
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

        titulosEventos[contadorEventos - 1] = null;

        contadorEventos--;
    }


    public static void calcularCuentaRegresiva(LocalDateTime fechaActual, int diaB, int mesB, int anioB, int horaB, int minutoB) {
        try {
            LocalDateTime fechaEvento = LocalDateTime.of(anioB, mesB, diaB, horaB, minutoB);
            Duration duracion = Duration.between(fechaActual, fechaEvento);

            long dias = duracion.toDays();
            long horas = duracion.toHoursPart();
            long minutos = duracion.toMinutesPart();

            System.out.println(">> FALTAN: " + dias + " días, " + horas + " horas, " + minutos + " minutos.");

        } catch (Exception e) {
            System.out.println(">> ERROR: Fecha inválida. No se pudo calcular la cuenta regresiva.");
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