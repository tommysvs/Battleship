import java.util.Scanner;

public class Main {
    static int opcion; //variable global para los menus que tengan numeros
    static char op = '\n'; //variable global para los menus que tengan letras

    static Scanner input = new Scanner(System.in);
    static String usuario, contrasena; //variables para el ingreso del usuario y de la contraeña

    static Player player = new Player(); //variable global para el player 1
    static Battleship bs = new Battleship(); //variable global para battleship
    static Functions fn = new Functions(); //variable global para funciones
    static Colors color = new Colors(); //variable global para los colores

    public static void main(String[] args) throws Exception {
        menu_inicio();
    }

    public static void menu_inicio() {
        fn.clear();
        fn.pause(100);
        System.out.println(color.YELLOW + "MENU DE INICIO\n");
        System.out.print(color.RESET);
        fn.pause(100);
        System.out.println("1. Login");
        fn.pause(100);
        System.out.println("2. Crear player");
        fn.pause(100);
        System.out.println("3. Salir");
        fn.pause(100);
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
        System.out.println(color.YELLOW + "LOGIN\n");
        System.out.print(color.RESET);

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
        
        System.out.println(color.YELLOW + "CREAR PLAYER\n");
        System.out.print(color.RESET);

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

        fn.pause(100);
        System.out.println(color.YELLOW + "MENU PRINCIPAL\n");
        System.out.print(color.RESET);
        fn.pause(100);
        System.out.println("LOGGEADO COMO: " + player.get_usuario() + "\n");
        fn.pause(100);
        System.out.println("1. Jugar");
        fn.pause(100);
        System.out.println("2. Configuracion");
        fn.pause(100);
        System.out.println("3. Reportes");
        fn.pause(100);
        System.out.println("4. Mi perfil");
        fn.pause(100);
        System.out.println("5. Cerrar sesion");
        fn.pause(100);
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

        System.out.println(color.YELLOW + "BATTLESHIP \n");
        System.out.print(color.RESET);

        do {
            System.out.print("Ingrese el username del PLAYER 2: ");
            usuario = input.nextLine();
        }while(!(player.usuario_existe(usuario) == true));

        if(usuario == "EXIT" || usuario == "exit")
            menu_principal();

        bs.jugar(usuario);
    }

    public static void configuracion() {
        fn.clear();

        fn.pause(100);
        System.out.println(color.YELLOW + "CONFIGURACION\n");
        System.out.print(color.RESET);

        fn.pause(100);
        System.out.println("a. Dificultad");
        fn.pause(100);
        System.out.println("b. Modo de juego");
        fn.pause(100);
        System.out.println("c. Regresar al menu principal");
        fn.pause(100);
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                fn.clear();

                fn.pause(100);
                System.out.println(color.YELLOW + "DIFICULTAD\n");
                System.out.print(color.RESET);

                fn.pause(100);
                System.out.println("1. EASY");
                fn.pause(100);
                System.out.println("2. NORMAL");
                fn.pause(100);
                System.out.println("3. EXPERT");
                fn.pause(100);
                System.out.println("4. GENIUS");
                fn.pause(100);
                System.out.print("Opcion: > ");
                opcion = input.nextInt();
                input.nextLine();

                bs.set_dificultad(opcion);

                configuracion();
            break;
            case 'b':
                fn.clear();
                
                fn.pause(100);
                System.out.println(color.YELLOW + "MODO DE JUEGO\n");
                System.out.print(color.RESET);

                fn.pause(100);
                System.out.println("1. ARCADE");
                fn.pause(100);
                System.out.println("2. TUTORIAL");
                fn.pause(100);
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

        fn.pause(100);
        System.out.println(color.YELLOW + "REPORTES\n");
        System.out.print(color.RESET);

        fn.pause(100);
        System.out.println("a. Descripcion de mis ultimos 10 juegos");
        fn.pause(100);
        System.out.println("b. Ranking de jugadores");
        fn.pause(100);
        System.out.println("c. Regresar al menu principal");
        fn.pause(100);
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
                fn.pause(5000);

                reportes();
            break;
            case 'c':
                menu_principal();
            break;
        }
    }

    public static void mi_perfil() {
        fn.clear();

        fn.pause(100);
        System.out.println(color.YELLOW + "MI PERFIL\n");
        System.out.print(color.RESET);

        fn.pause(100);
        System.out.println("a. Ver mis datos");
        fn.pause(100);
        System.out.println("b. Modificar mis datos");
        fn.pause(100);
        System.out.println("c. Eliminar cuenta");
        fn.pause(100);
        System.out.println("d. Regresar al menu principal");
        fn.pause(100);
        System.out.print("Opcion: > ");
        op = input.next().charAt(0);

        switch(op) {
            case 'a':
                fn.clear();

                System.out.println(color.YELLOW + "DATOS DEL USUARIO\n");
                System.out.print(color.RESET);

                player.ver_datos();
                fn.pause(4000);

                mi_perfil();
            break;
            case 'b':
                modificar_perfil();

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

        fn.clear();
        fn.pause(100);
        System.out.println(color.YELLOW + "MODIFICAR PERFIL DE " + player.get_usuario() + "\n");
        System.out.print(color.RESET);

        fn.pause(100);
        System.out.println("Modificar usuario o contrasena: ");
        fn.pause(100);
        System.out.println("\n1. Usuario");
        fn.pause(100);
        System.out.println("2. Contrasena");
        System.out.print("Opcion: > ");
        opcion = input.nextInt();
        input.nextLine();

        if(opcion == 1) {
            System.out.print("\nIngrese el nuevo usuario: ");
            nuevo = input.nextLine();
        }else if(opcion == 2) {
            System.out.print("\nIngrese la nueva contrasena: ");
            nuevo = input.nextLine();
        }

        player.modificar_usuario(usuario, nuevo, opcion);

        if(opcion == 1)
            System.out.println("El usuario ha sido modificado con exito.");
        else if(opcion == 2)
            System.out.print("La contrasena ha sido modificada con exito.");
        
        fn.pause(1400);
    }
}
