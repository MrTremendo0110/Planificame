import java.util.Scanner;

public class principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        // Inicio del bucle
        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("¿Que funcion desea realizar?");
            System.out.println("1. Horario");
            System.out.println("2. Calendario");
            System.out.println("3. Tareas");
            System.out.println("4. Alumno");
            System.out.println("Cualquier otra tecla para salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.next();

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

                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    continuar = false;
                    break;
            }
        }

    }
}