import java.util.Scanner;

public class tarea {
    public static void main() {
        //definimos una constante
        final int MAX_TAREAS = 50;
        // Arrays paralelos para almacenar los datos
        String[] nombresTareas = new String[MAX_TAREAS];
        long[] tiemposVencimiento = new long[MAX_TAREAS]; // Tiempo en milisegundos
        //tareas
        int num_tareas = 0;
        Scanner sc = new Scanner(System.in);
        //true para que se ejecute
        boolean corre = true;

        while (corre) {
            System.out.println("********************************************************************************");
            System.out.println("============   ||=========||     ||=======||     ||========       ||========||                       ");
            System.out.println("     ||        ||         ||     ||       ||     ||               ||        ||           ");
            System.out.println("     ||        ||=========||     ||=======||     ||=====          ||========||                ");
            System.out.println("     ||        ||         ||     ||     \\ \\      ||               ||        ||           " );;
            System.out.println("     ||        ||         ||     ||      \\ \\     ||========       ||        ||               ");;
            System.out.println("*********************************************************************************");
            System.out.println("Presione un numero para realizar una funcion");
            System.out.println("1. Agregar una nueva tarea (D/H/M/S)");
            System.out.println("2. Salir");
            System.out.println("3. Listar tareas pendientes");

            //opcion a elejir
            System.out.print("> ");
            String opcion = sc.next();
            sc.nextLine(); // linea de espacio

            switch (opcion) {
                case "1":
                    //agregamos tareas
                    if (num_tareas < MAX_TAREAS) {
                        System.out.print("Agrega descripcion de la tarea: ");
                        String tareaName = sc.nextLine();

                        try {
                            // Solicitar cada unidad de tiempo por separado
                            System.out.print("Dias: ");
                            long dias = Long.parseLong(sc.nextLine());
                            System.out.print("Horas: ");
                            long horas = Long.parseLong(sc.nextLine());
                            System.out.print("Minutos: ");
                            long minutos = Long.parseLong(sc.nextLine());
                            System.out.print("Segundos: ");
                            long segundos = Long.parseLong(sc.nextLine());

                            long duracionTotalMillis =
                                    (dias * 24 * 60 * 60 * 1000L) +
                                            (horas * 60 * 60 * 1000L) +
                                            (minutos * 60 * 1000L) +
                                            (segundos * 1000L);

                            if (duracionTotalMillis <= 0) {
                                System.out.println("La duración debe ser mayor a cero.");
                                break;
                            }

                            // Almacenar en los arrays paralelos
                            nombresTareas[num_tareas] = tareaName;
                            tiemposVencimiento[num_tareas] = System.currentTimeMillis() + duracionTotalMillis;

                            num_tareas++;
                            System.out.println("Tarea agregada con éxito.");

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Asegurese de ingresar solo números enteros válidos para el tiempo.");
                        }
                    } else {
                        System.out.println("Limite de tareas alcanzado.");
                    }
                    break;
                case "2":
                    System.out.println("Saliendo del programa...");
                    corre = false;
                    break;
                case "3":
                    System.out.println(">>>>> Tareas Pendientes <<<<");
                    if (num_tareas == 0) {
                        System.out.println("No hay tareas pendientes.");
                    } else {
                        long ahora = System.currentTimeMillis();
                        for (int i = 0; i < num_tareas; i++) {
                            long restanteMillis = tiemposVencimiento[i] - ahora;
                            String tiempoFormateado;

                            if (restanteMillis <= 0) {
                                tiempoFormateado = "¡TERMINADO!";
                            } else {
                                // Lógica de formateo manual (copiada de la versión POO)
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
                    System.out.println("--------------------------------------------------------------------------------*");
                    break;
                default:
                    System.out.println("Escoja una opcion correcta.");
            }
        }
        sc.close();
    }
}
