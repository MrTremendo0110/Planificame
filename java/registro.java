import java.util.Scanner;

public class registro {

    public static String nombre;
    public static String turnoEsc;
    public static int edad;
    public static int semestre;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String bandera = "n";
        System.out.println("Bienvenido");
        while(bandera.equals("n")){
            nombre = leerNombre();
            edad = leerEdad();
            semestre = leerSemestre();
            turnoEsc = leerTurno();
            System.out.println("Ha escrito:\n"+ nombre + "\n" + edad + "\n" + semestre + "\n" + turnoEsc + "\n" + "¿Sus datos son correctos? (s/n)");
            bandera = scanner.nextLine();
        }
        System.out.println("Su información ha sido registrada con éxito");
    }


    public static String leerNombre(){
        System.out.println("¿Cuál es su nombre?");
        nombre = scanner.nextLine();
        return nombre;
    }

    public static int leerEdad(){
        while(true) {
            System.out.println("¿Cuál es su edad? (13-21)");
            edad = scanner.nextInt();
            if (edad < 13 || edad > 21) {
                System.out.println("Error, la edad no puede ser menor a 13 años, ni mayor a 21 años");
            }
            else{
                break;
            }
        }
        return edad;
    }
    public static int leerSemestre(){
        while(true) {
            System.out.println("¿Cuál es el número de semestre que está cursando? (1-6)");
            semestre = scanner.nextInt();
            if (semestre < 1 || semestre > 6) {
                System.out.println("Error, el semestre escrito no se encuentra en el rango especificado (1-6)");
            }
            else{
                break;
            }
        }
        return semestre;
    }
    public static String leerTurno(){
        while(true) {
            System.out.println("¿En qué turno asiste a la escuela? (matutino/despertino)");
            turnoEsc = scanner.nextLine();
            if (!turnoEsc.equals("matutino") && !turnoEsc.equals("despertino")) {
                System.out.println("Error, el turno no se escribió según lo especificado (matutino/despertino)");
            }
            else{
                break;
            }
        }
        return turnoEsc;
    }
}