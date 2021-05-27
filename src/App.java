import java.io.IOException;
import java.util.Scanner;

public class App {
    static int opcion;
    static char op = '\n';

    static Scanner input = new Scanner(System.in);
    static String[] usuarios = new String[5];
    static String[] contrasenas = new String[5];
    static int contador_usuarios = 0;

    static String usuario, contrasena;
    static boolean logged_in = false, logged_out = false;

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < usuarios.length; i++) {
            usuarios[i] = "";
            contrasenas[i] = "";
        }
                
        menu_inicio();
    }

    public static void menu_inicio() {
        clear();
        System.out.println("MENU DE INICIO\n");
        System.out.println("1. Login");
        System.out.println("2. Crear player");
        System.out.println("3. Salir");
        System.out.print("Opcion: > ");
        opcion = input.nextInt();
        input.nextLine();

        switch(opcion) {
            case 1:
                login();
            break;
            case 2:
                crear_player();
            break;
            case 3:
                System.exit(0);
            break;
        }
    }

    public static void login() {
        clear();
        System.out.println("LOGIN\n");

        System.out.print("Ingrese el usuario: ");
        usuario = input.nextLine();

        for(int i = 0; i < usuarios.length; i++) {
            if(usuarios[i].equals(usuario)) {
                System.out.print("Ingrese el contrasena: ");    
                contrasena = input.nextLine();

                if(contrasenas[i].equals(contrasena))
                    menu_principal();
                else {
                    System.out.println("Contraseña incorrecta, intentalo de nuevo");
                    menu_inicio();
                }
            }
            else {
                System.out.println("El usuario ingresado no existe.");
                menu_inicio();
            }
        }
    }

    public static void crear_player() {
        clear();
        System.out.println("CREAR PLAYER\n");

        System.out.print("Ingrese el usuario: ");
        usuario = input.nextLine();

        System.out.print("Ingrese el contrasena: ");    
        contrasena = input.nextLine();

        for(int i = 0; i < usuarios.length; i++) {
            if(usuarios[i].equals("")) {
                usuarios[i] = usuario;
                contrasenas[i] = contrasena;
                break;
            }else {
                menu_inicio();
            }
        }

        menu_principal();
    }

    public static void menu_principal() {
        clear();

        System.out.println("MENU PRINCIPAL\n");

        if(usuario != "")
            System.out.println("LOGGEADO COMO: " + usuario + "\n");

        System.out.println("1. Jugar");
        System.out.println("2. Configuracion");
        System.out.println("3. Reportes");
        System.out.println("4. Mi perfil");
        System.out.println("5. Salir");
        System.out.print("Opcion: > ");
        opcion = input.nextInt();
        input.nextLine();

        switch(opcion) {
            case 1:
                jugar();
            break;
            case 2:
                configuracion();
            break;
            case 3:
                reportes();
            break;
            case 4:
                mi_perfil();
            break;
            case 5:
                menu_inicio();
            break;
        }
    }

    public static void jugar() {
        clear();
        Battleship.tablero();
    }

    public static void configuracion() {
        
    }

    public static void reportes() {
        clear();

        System.out.println("REPORTES\n");

        System.out.println("a. Descripcion de mis ultimos 10 juegos");
        System.out.println("b. Ranking de jugadores");
        System.out.println("c. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(opcion) {
            case 1:
                System.out.println("DESCRIPCION DE MIS ULTIMOS 10 JUEGOS\n");
            break;
            case 2:
                System.out.println("RANKING DE JUGADORES\n");
            break;
            case 3:
                menu_principal();
            break;
        }
    }

    public static void mi_perfil() {

    }

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }
}
