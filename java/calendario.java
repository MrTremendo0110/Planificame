import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration; // Importamos Duration para cálculos exactos

public class calendario {

    // Scanner global para leer entrada del usuario
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Definir constantes
        final int MAX_EVENTOS = 10; // Número máximo de eventos

        // Definir arreglos (Dimension)
        String[] titulosEventos = new String[MAX_EVENTOS];
        String[] tiposEventos = new String[MAX_EVENTOS];
        int[] diasEventos = new int[MAX_EVENTOS];
        int[] mesesEventos = new int[MAX_EVENTOS];
        int[] aniosEventos = new int[MAX_EVENTOS];
        int[] horasEventos = new int[MAX_EVENTOS];
        int[] minutosEventos = new int[MAX_EVENTOS];

        // Variables simples
        int contadorEventos = 0;
        int opcionMenu = 0;

        // Mostrar fecha de inicio al arrancar
        LocalDateTime inicio = LocalDateTime.now();
        System.out.println("--- SISTEMA DE CALENDARIO ---");
        System.out.println("Iniciado el: " + inicio.getDayOfMonth() + "/" + inicio.getMonthValue() + "/" + inicio.getYear());
        esperarTecla();

        do {
            // Mostrar menú
            limpiarPantalla();
            System.out.println("--- MÓDULO DE CALENDARIO ---");
            System.out.println("1. Registrar nuevo evento");
            System.out.println("2. Ver eventos y cuenta regresiva");
            System.out.println("3. Salir");
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
                    // Registrar nuevo evento
                    if (contadorEventos < MAX_EVENTOS) {
                        System.out.println("--- REGISTRAR NUEVO EVENTO ---");

                        System.out.print("Título del evento: ");
                        titulosEventos[contadorEventos] = scanner.nextLine();

                        System.out.print("Tipo (Examen/Proyecto): ");
                        tiposEventos[contadorEventos] = scanner.nextLine();

                        // Lectura de fecha
                        System.out.print("Fecha de entrega (Día): ");
                        diasEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                        System.out.print("Fecha de entrega (Mes): ");
                        mesesEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                        System.out.print("Fecha de entrega (Año): ");
                        aniosEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                        // Lectura de hora
                        System.out.print("Hora (0-23): ");
                        horasEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                        System.out.print("Minutos (0-59): ");
                        minutosEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                        contadorEventos++;
                        System.out.println("Evento registrado con éxito.");
                    } else {
                        System.out.println("No se pueden registrar más eventos.");
                    }
                    esperarTecla();
                    break;

                case 2:
                    // Ver eventos
                    limpiarPantalla();
                    System.out.println("--- EVENTOS REGISTRADOS ---");

                    // Capturamos la fecha y hora EXACTA en el momento de la consulta
                    LocalDateTime ahoraMismo = LocalDateTime.now();

                    if (contadorEventos > 0) {
                        for (int i = 0; i < contadorEventos; i++) {
                            System.out.println("----------------------------");
                            System.out.println("Título: " + titulosEventos[i]);
                            System.out.println("Tipo: " + tiposEventos[i]);
                            System.out.println("Fecha límite: " + diasEventos[i] + "/" + mesesEventos[i] + "/" + aniosEventos[i] +
                                    " - " + String.format("%02d", horasEventos[i]) + ":" + String.format("%02d", minutosEventos[i]));

                            // Pasamos la fecha actual REAL y los datos del evento
                            calcularCuentaRegresiva(ahoraMismo,
                                    diasEventos[i], mesesEventos[i], aniosEventos[i],
                                    horasEventos[i], minutosEventos[i]);
                        }
                    } else {
                        System.out.println("No hay eventos registrados.");
                    }
                    esperarTecla();
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    esperarTecla();
                    break;
            }

        } while (opcionMenu != 3);

        scanner.close();
    }

    // SubProceso actualizado para usar cálculos de tiempo reales de Java
    public static void calcularCuentaRegresiva(LocalDateTime fechaActual, int diaB, int mesB, int anioB, int horaB, int minutoB) {
        try {
            // Construimos la fecha del evento usando los datos guardados
            LocalDateTime fechaEvento = LocalDateTime.of(anioB, mesB, diaB, horaB, minutoB);

            // Calculamos la duración exacta entre AHORA y el EVENTO
            Duration duracion = Duration.between(fechaActual, fechaEvento);

            if (duracion.isNegative() || duracion.isZero()) {
                System.out.println(">> ESTADO: ¡El evento ya pasó o es ahora mismo!");
            } else {
                // Extraemos los componentes de tiempo restantes
                long dias = duracion.toDays();
                long horas = duracion.toHoursPart();     // Parte de horas (Java 9+)
                long minutos = duracion.toMinutesPart(); // Parte de minutos
                long segundos = duracion.toSecondsPart(); // Parte de segundos

                System.out.println(">> FALTAN: " + dias + " días, " + horas + " horas, " + minutos + " minutos y " + segundos + " segundos.");
            }
        } catch (Exception e) {
            // Captura errores si el usuario metió una fecha inválida (ej: mes 13 o día 32)
            System.out.println(">> ERROR: La fecha registrada para este evento no es válida (" + e.getMessage() + ")");
        }
    }

    // Métodos auxiliares
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static void esperarTecla() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}