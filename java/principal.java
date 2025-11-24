import java.util.Scanner;
public class principal {
    public static void main(String[] args) {
        //registro


        System.out.println("¿Que funcion desea realizar?");
        System.out.println("1. Horario");
        System.out.println("2. Calendario");
        System.out.println("3. Tareas");
        System.out.println("4. Alumno");
        System.out.println("cualquier tecla para salir");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.next();

        switch (opcion) {
            case "1":
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
                System.out.println("Hasta luego.");
                return;
        }


    }
}





