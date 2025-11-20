import java.util.Scanner;
public class tarea {

    public static void main(String args[]) {
        int tareas [] = new int[50];
        int num_tareas = 0;

        //menu titulo
        Scanner sc = new Scanner(System.in);

        System.out.println("********************************************************************************");
        System.out.println("============   ||=========||     ||=======||     ||========       ||========||                       ");
        System.out.println("     ||        ||         ||     ||       ||     ||               ||        ||           ");
        System.out.println("     ||        ||=========||     ||=======||     ||=====          ||========||                ");
        System.out.println("     ||        ||         ||     ||     \\ \\      ||               ||        ||           " );;
        System.out.println("     ||        ||         ||     ||      \\ \\     ||========       ||        ||               ");;
        System.out.println("*********************************************************************************");
        System.out.println("Presione un numero para realizar una funcion");
        System.out.println("1.Agrega una nueva tarea");
        System.out.println("2.Salir");

        //inicia el programa

        String opcion = sc.next();
        switch (opcion)
        {
            case "1" :
                if (num_tareas < 50 )
                {
                    num_tareas++;
                    System.out.println("Agrega tarea " + (num_tareas) );
                    String tarea = sc.next();
                    System.out.println("¿Hasta cuando quieres que dure?");

                }
                break;
            //registrar tarea

            case "2":
                break;
            //salir
            default:
                //devuelve a lo normal
                System.out.println("escoje una opcion correcta");
                return;
        }

        System.out.println(">>>>>Tareas Pendientes<<<<");

    }

}
