import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;

public class calendario {

    // Scanner global para leer entrada del usuario
    static Scanner scanner = new Scanner(System.in);

    // Se añade (String[] args) para que sea compatible con la llamada estándar desde Principal
    public static void main() {
        // Definir constantes
        final int MAX_EVENTOS = 10;

        // Definir arreglos
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

        // Mostrar fecha de inicio al arrancar el módulo
        LocalDateTime inicio = LocalDateTime.now();
        System.out.println("--- SISTEMA DE CALENDARIO ---");
        System.out.println("Fecha actual: " + inicio.getDayOfMonth() + "/" + inicio.getMonthValue() + "/" + inicio.getYear());
        esperarTecla();

        do {
            // Mostrar menú del módulo
            limpiarPantalla();
            System.out.println("--- MÓDULO DE CALENDARIO ---");
            System.out.println("1. Registrar nuevo evento");
            System.out.println("2. Ver eventos y cuenta regresiva");
            System.out.println("3. Volver al Menú Principal"); // Cambio de texto
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
                    // --- Registrar nuevo evento ---
                    if (contadorEventos < MAX_EVENTOS) {
                        System.out.println("--- REGISTRAR NUEVO EVENTO ---");

                        System.out.print("Título del evento: ");
                        titulosEventos[contadorEventos] = scanner.nextLine();

                        System.out.print("Tipo (Examen/Proyecto): ");
                        tiposEventos[contadorEventos] = scanner.nextLine();

                        // Validación básica de entrada para evitar errores al escribir texto en lugar de números
                        try {
                            System.out.print("Fecha de entrega (Día): ");
                            diasEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                            System.out.print("Fecha de entrega (Mes): ");
                            mesesEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                            System.out.print("Fecha de entrega (Año): ");
                            aniosEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                            System.out.print("Hora (0-23): ");
                            horasEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                            System.out.print("Minutos (0-59): ");
                            minutosEventos[contadorEventos] = Integer.parseInt(scanner.nextLine());

                            contadorEventos++;
                            System.out.println("Evento registrado con éxito.");
                        } catch (Exception e) {
                            System.out.println("Error al ingresar datos numéricos. El evento no se guardó.");
                        }
                    } else {
                        System.out.println("No se pueden registrar más eventos.");
                    }
                    esperarTecla();
                    break;

                case 2:
                    // --- Ver eventos ---
                    limpiarPantalla();
                    System.out.println("--- EVENTOS REGISTRADOS ---");

                    LocalDateTime ahoraMismo = LocalDateTime.now();

                    if (contadorEventos > 0) {
                        for (int i = 0; i < contadorEventos; i++) {
                            System.out.println("----------------------------");
                            System.out.println("Título: " + titulosEventos[i]);
                            System.out.println("Tipo: " + tiposEventos[i]);
                            System.out.println("Fecha límite: " + diasEventos[i] + "/" + mesesEventos[i] + "/" + aniosEventos[i] +
                                    " - " + String.format("%02d", horasEventos[i]) + ":" + String.format("%02d", minutosEventos[i]));

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
                    // Solo mostramos mensaje. Al salir del switch y fallar la condición del while,
                    // el método termina y regresa a 'principal'.
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    esperarTecla();
                    break;
            }

        } while (opcionMenu != 3);

        // ¡IMPORTANTE!
        // NO cerramos scanner.close(); aquí.
        // Si lo cerramos, 'principal' no podrá leer más opciones.
    }

    // SubProceso para cálculos de tiempo
    public static void calcularCuentaRegresiva(LocalDateTime fechaActual, int diaB, int mesB, int anioB, int horaB, int minutoB) {
        try {
            LocalDateTime fechaEvento = LocalDateTime.of(anioB, mesB, diaB, horaB, minutoB);
            Duration duracion = Duration.between(fechaActual, fechaEvento);

            if (duracion.isNegative() || duracion.isZero()) {
                System.out.println(">> ESTADO: ¡El evento ya pasó o es ahora mismo!");
            } else {
                long dias = duracion.toDays();
                long horas = duracion.toHoursPart();
                long minutos = duracion.toMinutesPart();
                long segundos = duracion.toSecondsPart();

                System.out.println(">> FALTAN: " + dias + " días, " + horas + " horas, " + minutos + " minutos.");
            }
        } catch (Exception e) {
            System.out.println(">> ERROR: Fecha inválida.");
        }
    }

    // Métodos auxiliares
    public static void limpiarPantalla() {
        for (int i = 0; i < 2; i++) System.out.println(); // Reducido para no perder contexto en consolas pequeñas
    }

    public static void esperarTecla() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}