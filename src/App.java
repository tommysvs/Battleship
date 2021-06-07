import java.util.Scanner;

public class App {
    //constantes para definir la dificultad del juego y el modo de juego
    static String[] dificultad_const = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    static String[] modo_const = {"ARCADE", "TUTORIAL"};

    static int opcion; //variable global para los menus que tengan numeros
    static char op = '\n'; //variable global para los menus que tengan letras

    static Scanner input = new Scanner(System.in);
    static String usuario, contrasena; //variables para el ingreso del usuario y de la contraeña
    static String dificultad = "NORMAL";
    static String modo = "TUTORIAL";

    static Functions fn = new Functions();

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

        Player player = new Player();

        if(player.usuario_existe(usuario)) {
            if(player.index_usuario(usuario) == player.index_contrasena(contrasena))
                menu_principal();
            else {
                System.out.print("\nContraseña incorrecta.");
                fn.pause();

                menu_inicio();
            }
        }else {
            System.out.print("\nEl usuario ingresado no existe.");
            fn.pause();

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

        Player player = new Player();

        if(player.usuario_existe(usuario)) {
            System.out.print("\nEste usuario ya existe.");
            fn.pause();

            menu_inicio();
        } else {
            player.agregar_usuario(usuario, contrasena);

            System.out.print("\nUsuario creado con exito.");
            fn.pause();

            menu_principal();
        }
    }

    public static void menu_principal() {
        fn.clear();

        Player player = new Player();

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
                menu_inicio();
            break;
        }
    }

    public static void jugar() {
        fn.clear();

        Player pl = new Player();
        Battleship bs = new Battleship();

        System.out.println("BATTLESHIP \n");

        do {
            System.out.print("Ingrese username del PLAYER 2: ");
            usuario = input.nextLine();

            if(usuario.equals("EXIT")) {
                menu_principal(); 
            }
        }while(!pl.usuario_existe(usuario));

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
        fn.clear();

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
        fn.clear();

        Player player = new Player();

        System.out.println("MI PERFIL\n");

        System.out.println("a. Ver mis datos");
        System.out.println("b. Modificar mis datos");
        System.out.println("c. Eliminar cuenta");
        System.out.println("d. Regresar al menu principal");
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                player.ver_datos();
                mi_perfil();
            break;
            case 'b':

                mi_perfil();
            break;
            case 'c':
                if(player.eliminar_usuario(usuario))
                    System.out.println("Usuario eliminado con exito.");
                else
                    System.out.println("No se puede eliminar el usuario.");

                fn.pause();

                menu_inicio();
            break;
            case 'd':
                menu_principal();
            break;
        }
    }

    public static void modificar_perfil() {
        Player player = new Player();
        String nuevo = "";

        System.out.println("MODIFICAR PERFIL DE " + player.get_usuario() + "\n");

        System.out.println("Modificar usuario o contrasena: ");
        System.out.println("1. Usuario");
        System.out.println("2. Contrasena");
        opcion = input.nextInt();

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
        fn.pause();

        menu_inicio();
    }
}
