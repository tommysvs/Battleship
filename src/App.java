import java.io.IOException;
import java.util.Scanner;

public class App {
    //constantes para definir la dificultad del juego y el modo de juego
    static String[] dificultad_const = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    static String[] modo_const = {"ARCADE", "TUTORIAL"};

    static int opcion; //variable global para los menus que tengan numeros
    static char op = '\n'; //variable global para los menus que tengan letras

    static Scanner input = new Scanner(System.in); //variable global para el scanner
    static String[] usuarios = new String[2]; //variable global que almacena los usuarios cuando se crean
    static String[] contrasenas = new String[2]; //variable global que almacena las contrasseñas cuando se crean
    static int contador_usuarios = 0;
    static int puntos_usr1 = 0, puntos_usr2 = 0;

    static String usuario, contrasena; //variables para el scanner
    static boolean logged = false; 
    static String dificultad = "NORMAL";
    static String modo = "TUTORIAL";

    public static void main(String[] args) throws Exception {
        //ciclo for para inicialiar la variable 'usuarios' y 'contrasenas' para que no de error de null exception
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

        if(!validar_usuario()) 
            menu_inicio();

        menu_principal();
    }

    public static boolean validar_usuario() {
        for(int i = 0; i < usuarios.length; i++) {
            if(contador_usuarios == usuarios.length) {
                System.out.println("Se excedio el limite de usuarios.");
                break;
            }

            if(usuarios[i].equals("")) {
                usuarios[i] = usuario;
                contrasenas[i] = contrasena;

                contador_usuarios++;

                break;
            }else if(usuarios[i + contador_usuarios].equals("")) {
                usuarios[i + contador_usuarios] = usuario;
                contrasenas[i + contador_usuarios] = contrasena;

                contador_usuarios++;

                break;
            }else {
                return false;
            }
        }

        return true;
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
        System.out.println("5. Cerrar sesion");
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
                logged = false;
                menu_inicio();
            break;
        }
    }

    public static void jugar() {
        clear();

        System.out.println("BATTLESHIP \n");

        do {
            System.out.print("Ingrese username del PLAYER 2:");
            usuario = input.nextLine();

            System.out.print("Ingrese contrasena del PLAYER 2:");
            usuario = input.nextLine();

            if(usuario == "EXIT")
                menu_principal(); 

            if(validar_usuario())
                break;
        }while(validar_usuario() != false);

        clear();
    }

    public static void configuracion() {
        clear();

        System.out.println("CONFIGURACION\n");

        System.out.println("a. Dificultad");
        System.out.println("b. Modo de juego");
        System.out.println("c. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                clear();

                System.out.println("DIFICULTAD\n");

                configuracion();
            break;
            case 'b':
                clear();
                
                System.out.println("MODO DE JUEGO\n");

                System.out.println("1. ARCADE");
                System.out.println("2. TUTORIAL");
                System.out.print("Opcion: > ");
                opcion = input.nextInt();
                input.nextLine();

                if(opcion == 1)
                    modo = modo_const[0]; //arcade
                else
                    modo = modo_const[1]; //tutorial

                configuracion();
            break;
            case 'c':
                menu_principal();
            break;
        }
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
            case 'a':
                System.out.println("DESCRIPCION DE MIS ULTIMOS 10 JUEGOS\n");
            break;
            case 'b':
                System.out.println("RANKING DE JUGADORES\n");

                reportes();
            break;
            case 'c':
                menu_principal();
            break;
        }
    }

    public static void mi_perfil() {
        clear();

        System.out.println("MI PERFIL\n");

        System.out.println("a. Ver mis datos");
        System.out.println("b. Modificar mis datos");
        System.out.println("c. Eliminar cuenta");
        System.out.println("c. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                mi_perfil();
            break;
            case 'b':
                mi_perfil();
            break;
            case 'c':
                menu_inicio();
            break;
            case 'd':
                menu_principal();
            break;
        }
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
