import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int opcion; //variable global para los menus que tengan numeros
    static char op = '\n'; //variable global para los menus que tengan letras

    static Scanner input = new Scanner(System.in);
    static String usuario, contrasena; //variables para el ingreso del usuario y de la contraeña

    static Functions fn = new Functions(); //variable global para funciones
    static Player player = new Player(); //variable global para el player 1
    static Battleship bs = new Battleship(); //variable global para battleship

    public static void main(String[] args) throws Exception {
        menu_inicio();
    }

    public static void menu_inicio() {
        fn.clear();
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
        fn.clear();
        System.out.println("LOGIN\n");

        System.out.print("Ingresa el usuario: ");
        usuario = input.nextLine();

        System.out.print("Ingresa la contrasena: ");    
        contrasena = input.nextLine();

        if(player.usuario_existe(usuario)) {
            if(player.index_usuario(usuario) == player.index_contrasena(contrasena)) {
                player.set_usuario_logged(usuario);
                player.set_logged_in();

                menu_principal();
            }
            else {
                System.out.print("\nContraseña incorrecta.");
                fn.pause(1400);

                menu_inicio();
            }
        }else {
            System.out.print("\nEl usuario ingresado no existe.");
            fn.pause(1400);

            menu_inicio();
        }
    }

    public static void crear_player() {
        fn.clear();
        
        System.out.println("CREAR PLAYER\n");

        System.out.print("Ingrese el usuario: ");
        usuario = input.nextLine();

        System.out.print("Ingrese la contrasena: ");    
        contrasena = input.nextLine();

        if(player.usuario_existe(usuario)) {
            System.out.print("\nEste usuario ya existe.");
            fn.pause(1400);

            menu_inicio();
        } else {
            player.agregar_usuario(usuario, contrasena);

            System.out.print("\nUsuario creado con exito.");
            fn.pause(1400);

            player.set_usuario_logged(usuario);
            player.set_logged_in();
            menu_principal();
        }
    }

    public static void menu_principal() {
        fn.clear();

        System.out.println("MENU PRINCIPAL\n");

        System.out.println("LOGGEADO COMO: " + player.get_usuario() + "\n");

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
                player.set_logged_off();
                player.set_usuario_logged("");
                menu_inicio();
            break;
        }
    }

    public static void jugar() {
        fn.clear();

        Battleship bs = new Battleship();

        System.out.println("BATTLESHIP \n");

        bs.normal();
    }

    public static void configuracion() {
        fn.clear();

        System.out.println("CONFIGURACION\n");

        System.out.println("a. Dificultad");
        System.out.println("b. Modo de juego");
        System.out.println("c. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                fn.clear();

                System.out.println("DIFICULTAD\n");
                System.out.println("MODO DE JUEGO\n");

                System.out.println("1. EASY");
                System.out.println("2. NORMAL");
                System.out.println("3. EXPERT");
                System.out.println("4. GENIUS");
                System.out.print("Opcion: > ");
                opcion = input.nextInt();
                input.nextLine();

                bs.set_dificultad(opcion);

                configuracion();
            break;
            case 'b':
                fn.clear();
                
                System.out.println("MODO DE JUEGO\n");

                System.out.println("1. ARCADE");
                System.out.println("2. TUTORIAL");
                System.out.print("Opcion: > ");
                opcion = input.nextInt();
                input.nextLine();

                bs.set_modo(opcion);

                configuracion();
            break;
            case 'c':
                menu_principal();
            break;
        }
    }

    public static void reportes() {
        fn.clear();

        System.out.println("REPORTES\n");

        System.out.println("a. Descripcion de mis ultimos 10 juegos");
        System.out.println("b. Ranking de jugadores");
        System.out.println("c. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                fn.clear();
                System.out.println("DESCRIPCION DE MIS ULTIMOS 10 JUEGOS\n");

                player.show_juegos();
                fn.pause(5000);

                reportes();
            break;
            case 'b':
                fn.clear();
                System.out.println("RANKING DE JUGADORES\n");

                player.show_ranking();

                reportes();
            break;
            case 'c':
                menu_principal();
            break;
        }
    }

    public static void mi_perfil() {
        fn.clear();

        System.out.println("MI PERFIL\n");

        System.out.println("a. Ver mis datos");
        System.out.println("b. Modificar mis datos");
        System.out.println("c. Eliminar cuenta");
        System.out.println("d. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                fn.clear();

                System.out.println("DATOS DEL USUARIO\n");

                player.ver_datos();
                fn.pause(4000);

                mi_perfil();
            break;
            case 'b':
                fn.clear();
                System.out.println("Que dato desea modificar");
                System.out.println("1. Usuario");
                System.out.println("2. Contrasena");
                System.out.print("Opcion: > ");
                opcion = input.nextInt();
                input.nextLine();

                String nuevo = "";

                if(opcion == 1) {
                    System.out.print("Ingrese el nuevo usuario: ");
                    nuevo = input.nextLine();
                }else if(opcion == 2) {
                    System.out.print("Ingrese la nueva contrasena: ");
                    nuevo = input.nextLine();
                }

                player.modificar_usuario(usuario, nuevo, opcion);

                if(opcion == 1)
                    System.out.println("El usuario ha sido modificado con exito.");
                else if(opcion == 2)
                    System.out.print("La contrasena ha sido modificada con exito.");
                
                fn.pause(1400);

                mi_perfil();
            break;
            case 'c':
                fn.clear();
                if(player.eliminar_usuario(usuario))
                    System.out.println("Usuario eliminado con exito.");
                else
                    System.out.println("No se puede eliminar el usuario.");

                fn.pause(1400);

                menu_inicio();
            break;
            case 'd':
                menu_principal();
            break;
        }
    }

    public static void modificar_perfil() {
        String nuevo = "";

        System.out.println("MODIFICAR PERFIL DE " + player.get_usuario() + "\n");

        System.out.println("Modificar usuario o contrasena: ");
        System.out.println("1. Usuario");
        System.out.println("2. Contrasena");
        opcion = input.nextInt();
        input.nextLine();

        switch(opcion) {
            case 1:
                System.out.print("Ingresa el nuevo usuario: ");
                nuevo = input.nextLine();
            break;
            case 2:
                System.out.print("Ingresa la nueva contrasena: ");
                nuevo = input.nextLine();
            break;
            default:
                System.out.println("Opcion no valida.");
                mi_perfil();
            break;
        }

        player.modificar_usuario(usuario, nuevo, op);

        System.out.print("Usuario modificado exitosamente.");
        fn.pause(1400);

        menu_inicio();
    }
}
