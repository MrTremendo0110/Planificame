import java.util.*;

public class Horario {
    static Scanner scanner = new Scanner(System.in);
// variables pa guardar hora de entrada y salida y horario en general

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    static String[] matEsc;
    static String[][] matHorEnt;
    static String[][] matHorSal;

    static String[] actExt;
    static String[][] actHorEnt;
    static String[][] actHorSal;

    public static void main() {
        System.out.println(ANSI_PURPLE +
                "███       ███      ████████     ██████████    ██████████   ██████████    █████████     ████████                                         \n" +
                "███       ███     ███    ███    ██████████    ██████████   ██████████    █████████    ███    ███                            \n" +
                "███       ███    ███      ███   ███     ███   ███    ███   ███     ███      ███      ███      ███                              \n" +
                "█████████████    ███      ███   ███     ███   ███    ███   ███     ███      ███      ███      ███                            \n" +
                "█████████████    ███      ███   ██████████    ██████████   ██████████       ███      ███      ███                      \n" +
                "███       ███    ███      ███   ███  ███      ██████████   ███  ███         ███      ███      ███                   \n" +
                "███       ███     ███    ███    ███   ███     ███    ███   ███   ███     █████████    ███    ███                         \n" +
                "███       ███      ████████     ███    ███    ███    ███   ███    ███    █████████     ████████              \n" +
                ANSI_RESET);

        while(true){
            System.out.println("\n=== MENÚ PRINCIPAL DE HORARIO ===");
            System.out.println("1.-Registro de horario escolar");
            System.out.println("2.-Registro de actividades extracurriculares");
            System.out.println("3.-Ver horario completo");
            System.out.println("4.-Salir");
            System.out.print("Seleccione una opción: ");

            int opcOp = scanner.nextInt();
            scanner.nextLine();

            switch(opcOp){
                case 1:
                    regHorEsc();
                    break;
                case 2:
                    regActExt();
                    break;
                case 3:
                    verHorarioCompleto();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    //REGISTRO DE MATERIAS ( cuantas cursa, nombre y que dias toca)
    public static void regHorEsc() {
        System.out.println("Haz seleccionado el registro de horario escolar");
        System.out.println("¿Cuántas materias está cursando?");
        int numMat = scanner.nextInt();
        scanner.nextLine();

        matEsc = new String[numMat];
        matHorEnt = new String[numMat][5];
        matHorSal = new String[numMat][5];

        String[] dias = {"Lunes","Martes","Miércoles","Jueves","Viernes"};

        for(int x = 0; x < numMat; x++){
            System.out.println("Ingrese el nombre de la materia " + (x+1));
            matEsc[x] = scanner.nextLine();

            for(int i = 0; i < 5; i++){
                System.out.println("¿La materia \"" + matEsc[x] + "\" se cursa el " + dias[i] + "? (s/n)");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if(respuesta.equals("s")){
                    System.out.print("Ingrese el horario de entrada (En formato HH:mm): ");
                    matHorEnt[x][i] = scanner.nextLine();
                    System.out.print("Ingrese el horario de salida (En formato HH:mm): ");
                    matHorSal[x][i] = scanner.nextLine();
                } else {
                    matHorEnt[x][i] = "No se encuentra";
                    matHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println("Registro de materias completo.");
    }

    //REGISTRO DE ACTIVIDADES (cuantas act, días que toca y hora)
    public static void regActExt() {
        System.out.println("Haz seleccionado el registro de actividades extracurriculares");
        System.out.println("¿Qué cantidad de actividades extracurriculares realiza?");
        int numAct = scanner.nextInt();
        scanner.nextLine();

        actExt = new String[numAct];
        actHorEnt = new String[numAct][5];
        actHorSal = new String[numAct][5];

        String[] dias = {"Lunes","Martes","Miércoles","Jueves","Viernes"};

        for(int x = 0; x < numAct; x++){
            System.out.println("Ingrese el nombre de la actividad " + (x+1));
            actExt[x] = scanner.nextLine();

            for(int i = 0; i < 5; i++){
                System.out.println("¿La actividad \"" + actExt[x] + "\" se realiza el " + dias[i] + "? (s/n)");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if(respuesta.equals("s")){
                    System.out.print("Ingrese el horario de entrada (En formato HH:mm): ");
                    actHorEnt[x][i] = scanner.nextLine();
                    System.out.print("Ingrese el horario de salida (En formato HH:mm): ");
                    actHorSal[x][i] = scanner.nextLine();
                } else {
                    actHorEnt[x][i] = "No se encuentra";
                    actHorSal[x][i] = "No se encuentra";
                }
            }
        }

        System.out.println("Registro de actividades completo.");
    }

    //HORARIO COMPLETO
    public static void verHorarioCompleto() {
        if(matEsc != null){
            imprimirResumenPorDia(matEsc, matHorEnt, matHorSal, "Materia");
        } else {
            System.out.println("No se ha registrado horario escolar aún.");
        }

        if(actExt != null){
            imprimirResumenPorDia(actExt, actHorEnt, actHorSal, "Actividad");
        } else {
            System.out.println("No se ha registrado actividades extracurriculares todavía.");
        }
    }

    //RESUMEN DE AMBOS HORARIOS
    public static void imprimirResumenPorDia(String[] nombres, String[][] horEnt, String[][] horSal, String tipo) {
        String[] dias = {"Lunes","Martes","Miércoles","Jueves","Viernes"};
        System.out.println("······································\n" +
                ": _   _                      _       :\n" +
                ":| | | | ___  _ __ __ _ _ __(_) ___  :\n" +
                ":| |_| |/ _ \\| '__/ _` | '__| |/ _ \\ :\n" +
                ":|  _  | (_) | | | (_| | |  | | (_) |:\n" +
                ":|_| |_|\\___/|_|  \\__,_|_|  |_|\\___/ :\n" +
                "······································");

        System.out.println("\nResumen de " + tipo.toLowerCase() + "s por día:");

        for(int i = 0; i < 5; i++){
            List<String[]> listaDia = new ArrayList<>();

            for(int x = 0; x < nombres.length; x++){
                if(!horEnt[x][i].equals("No aplica")){
                    listaDia.add(new String[]{nombres[x], horEnt[x][i], horSal[x][i]});
                }
            }

            // Ordenar por hora de entrada (asumiendo formato HH:mm)
            listaDia.sort((a,b) -> a[1].compareTo(b[1]));

            System.out.print(dias[i] + ": ");
            if(listaDia.isEmpty()){
                System.out.println("Sin " + tipo.toLowerCase() + "s");
            } else {
                for(int j = 0; j < listaDia.size(); j++){
                    String[] m = listaDia.get(j);
                    System.out.print(m[0] + " (" + m[1] + "-" + m[2] + ")");
                    if(j < listaDia.size()-1) System.out.print(", ");
                }
                System.out.println();
            }
        }
    }
}