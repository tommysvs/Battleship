import java.util.Random;
import java.util.Scanner;

public class Battleship extends Main {
    Scanner input = new Scanner(System.in);

    Battleship[] ship = new Battleship[4];
    String Barco;
    int Bombas;
    String Codigo;

    public void set_ships() {
        ship[0] = new Battleship("PORTAAVIONES", 5, "PA");
        ship[1] = new Battleship("ACORAZADO", 4, "AZ");
        ship[2] = new Battleship("SUBMARINO", 3, "SM");
        ship[3] = new Battleship("DESTRUCTOR", 2, "DT");
    }

    static String[] dificultad_nombre = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    static int[] dificultad_barcos = {5, 4, 2, 1};
    static String[] modo_const = {"ARCADE", "TUTORIAL"};

    static String dificultad = "NORMAL";
    static String modo = "TUTORIAL";

    String agua = "~", hit = "X", fail = "F";

    static String tablero_player1[][] = new String[8][8];
    static String tablero_player2[][] = new String[8][8];
    static String player2 = "";

    int filas = 8, columnas = 8;
    int fil, col;
    String _codigo;

    int player1_barcos = 0, player2_barcos = 0;

    char opcion;
    boolean ren_1 = false;
    boolean ren_2 = false;

    int dif = 0;
 
    Battleship() { }

    Battleship(String s, int b, String c) {
        Barco = s;
        Bombas = b;
        Codigo = c;
    }

    public void jugar(String _player2) {
        player2 = _player2;
        ren_1 = false;
        ren_2 = false;

        set_ships();
        iniciar_tableros();

        if(dificultad == dificultad_nombre[0]) {
            player1_barcos = player2_barcos = dificultad_barcos[0];
            dif = dificultad_barcos[0];
        }if(dificultad == dificultad_nombre[1]) {
            player1_barcos = player2_barcos = dificultad_barcos[1];
            dif = dificultad_barcos[1];
        }if(dificultad == dificultad_nombre[2]) {
            player1_barcos = player2_barcos = dificultad_barcos[2];
            dif = dificultad_barcos[2];
        }if(dificultad == dificultad_nombre[3]) {
            player1_barcos = player2_barcos = dificultad_barcos[3];
            dif = dificultad_barcos[3];
        }

        posicionar_player1();
        posicionar_player2();

        do {
            turno_player1();
            fn.pause(2000);
            turno_player2();
            fn.pause(2000);
        }while(player1_barcos != 0 && player2_barcos != 0);

        game_over();
    }

    public void iniciar_tableros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero_player1[i][j] = agua;
                tablero_player2[i][j] = agua;
            }
        }
    }

    public void print_tablero1() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(modo.contains(modo_const[0])) {
                    if(tablero_player1[i][j].contains(agua))
                        System.out.print(color.CYAN + tablero_player1[i][j] + " ");
                    else if(tablero_player1[i][j].contains(hit))
                        System.out.print(color.WHITE + tablero_player1[i][j] + " ");
                    else if(tablero_player1[i][j].contains(fail))
                        System.out.print(color.RED + tablero_player1[i][j] + " ");
                }else if(modo.contains(modo_const[1])) {
                    if(tablero_player1[i][j].contains(agua))
                        System.out.print(color.CYAN + tablero_player1[i][j] + " ");
                    else if(tablero_player1[i][j].contains(ship[0].get_codigo_barco()))
                        System.out.print(color.YELLOW + tablero_player1[i][j].toUpperCase() + " ");
                    else if(tablero_player1[i][j].contains(ship[1].get_codigo_barco()))
                        System.out.print(color.LIGHT_PURPLE + tablero_player1[i][j].toUpperCase() + " ");
                    else if(tablero_player1[i][j].contains(ship[2].get_codigo_barco()))
                        System.out.print(color.LIGHT_PINK + tablero_player1[i][j].toUpperCase() + " ");
                    else if(tablero_player1[i][j].contains(ship[3].get_codigo_barco()))
                        System.out.print(color.LIGHT_GREEN + tablero_player1[i][j].toUpperCase() + " ");
                    else if(tablero_player1[i][j].contains(hit))
                        System.out.print(color.WHITE + tablero_player1[i][j] + " ");
                    else if(tablero_player1[i][j].contains(fail))
                        System.out.print(color.RED + tablero_player1[i][j] + " ");
                }
            }

            System.out.println();
        }

        System.out.print(color.RESET);
    }

    public void print_tablero2() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(modo.contains(modo_const[0])) {
                    if(tablero_player2[i][j].contains(agua)) 
                        System.out.print(color.CYAN + tablero_player2[i][j] + " ");
                    else if(tablero_player2[i][j].contains(hit))
                        System.out.print(color.WHITE + tablero_player2[i][j] + " ");
                    else if(tablero_player2[i][j].contains(fail))
                        System.out.print(color.RED + tablero_player2[i][j] + " ");
                }else if(modo.contains(modo_const[1])) {
                    if(tablero_player2[i][j].contains(agua))
                        System.out.print(color.CYAN + tablero_player2[i][j] + " ");
                    else if(tablero_player2[i][j].contains(ship[0].get_codigo_barco()))
                        System.out.print(color.YELLOW + tablero_player2[i][j].toUpperCase() + " ");
                    else if(tablero_player2[i][j].contains(ship[1].get_codigo_barco()))
                        System.out.print(color.LIGHT_PURPLE + tablero_player2[i][j].toUpperCase() + " ");
                    else if(tablero_player2[i][j].contains(ship[2].get_codigo_barco()))
                        System.out.print(color.LIGHT_PINK + tablero_player2[i][j].toUpperCase() + " ");
                    else if(tablero_player2[i][j].contains(ship[3].get_codigo_barco()))
                        System.out.print(color.LIGHT_GREEN + tablero_player2[i][j].toUpperCase() + " ");
                    else if(tablero_player2[i][j].contains(hit))
                        System.out.print(color.WHITE + tablero_player2[i][j] + " ");
                    else if(tablero_player2[i][j].contains(fail))
                        System.out.print(color.RED + tablero_player2[i][j] + " ");
                }
            }

            System.out.println();
        }

        System.out.print(color.RESET);
    }

    public void posicionar_player1() {
        fn.clear();
        System.out.println("BATTLESHIP\n");

        System.out.println("Posicionamiento del PLAYER 1: " + player.get_usuario());
        System.out.println("BARCOS: PA, AZ, SM, DT\n");

        for(int i = 1; i <= dif;) {
            System.out.print("Ingrese el codigo del barco: ");
            _codigo = input.nextLine();
            _codigo.toUpperCase();
            
            System.out.print("Ingrese el indice de la fila: ");
            fil = input.nextInt();
            input.nextLine();
            
            if(eval_rendirse(fil, 1))
                break;

            System.out.print("Ingrese el indice de la columna: ");  
            col = input.nextInt();
            input.nextLine();

            fil--;
            col--;

            if(eval_rendirse(col, 1))
                break;

            if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas) && tablero_player1[fil][col] == agua) {
                tablero_player1[fil][col] = _codigo.toUpperCase();
                i++;
            }else if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas) && 
            tablero_player1[fil][col].contains(ship[0].get_codigo_barco()) || tablero_player1[fil][col].contains(ship[2].get_codigo_barco()) || 
            tablero_player1[fil][col].contains(ship[1].get_codigo_barco()) || tablero_player1[fil][col].contains(ship[3].get_codigo_barco()))
                System.out.println("No puedes colocar el barco " + _codigo.toUpperCase() + " en la misma posicion.");
            else if((fil < 0 || fil >= filas) || (col < 0 || col >= columnas))
                System.out.println("No puedes colocar barcos fuera del tablero.");
        }
    }

    public void posicionar_player2() {
        fn.clear();
        System.out.println("BATTLESHIP\n");

        System.out.println("Posicionamiento del PLAYER 2: " + player2);
        System.out.println("BARCOS: PA, AZ, SM, DT\n");
        
        for(int i = 1; i <= dif;) {
            System.out.print("Ingrese el codigo del barco: ");
            _codigo = input.nextLine();
            _codigo.toUpperCase();
            
            System.out.print("Ingrese el indice de la fila: ");
            fil = input.nextInt();
            input.nextLine();

            if(eval_rendirse(fil, 2))
                break;
            
            System.out.print("Ingrese el indice de la columna: ");  
            col = input.nextInt();
            input.nextLine();
            
            fil--;
            col--;

            if(eval_rendirse(col, 2))
                break;

            if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas) && tablero_player2[fil][col] == agua) {
                tablero_player2[fil][col] = _codigo.toUpperCase();
                i++;
            }else if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas) && 
            tablero_player2[fil][col].contains(ship[0].get_codigo_barco()) || tablero_player2[fil][col].contains(ship[2].get_codigo_barco()) || 
            tablero_player2[fil][col].contains(ship[1].get_codigo_barco()) || tablero_player2[fil][col].contains(ship[3].get_codigo_barco()))
                System.out.println("No puedes colocar el barco " + _codigo.toUpperCase() + " en la misma posicion.");
            else if((fil < 0 || fil >= filas) || (col < 0 || col >= columnas))
                System.out.println("No puedes colocar barcos fuera del tablero.");
        }
    }

    // public void generar_random(int _tablero) {
    //     for(int i = 1; i <= dificultad_barcos[1];) {
    //         int f = (int)(Math.random() * filas);
    //         int c = (int)(Math.random() * columnas);

    //         if((f >= 0 && f < filas) && (c >= 0 && c < columnas) && tablero_player1[f][c] == agua) {
    //             if(dificultad == "EASY")
    //                 tablero_player1[f][c] = ship[0].get_codigo_barco();

    //             i++;
    //         }
    //     }
    // }

    public void turno_player1() {
        do {
            fn.clear();

            System.out.println("TURNO - PLAYER 1\n");

            print_tablero2();

            System.out.println("\nCoordenada de bomba (Player 1): ");
            System.out.println(player2 + " tiene " + player1_barcos + " barcos aun\n");

            System.out.print("Fila: ");
            fil = input.nextInt();
            input.nextLine();

            System.out.print("Columna: ");
            col = input.nextInt();
            input.nextLine();

            fil--;
            col--;

            if ((fil >= 0 && fil < filas) && (col >= 0 && col < columnas)) {
                if (tablero_player2[fil][col].contains(ship[0].get_codigo_barco()) || tablero_player2[fil][col].contains(ship[2].get_codigo_barco()) || 
                tablero_player2[fil][col].contains(ship[1].get_codigo_barco()) || tablero_player2[fil][col].contains(ship[3].get_codigo_barco())) {
                    System.out.println("\nSE HA BOMBARDEADO UN " + get_nombre_barco(2, fil, col));
                    System.out.println("SE HUNDIO UN " + get_nombre_barco(2, fil, col) + " del Player 2");
                    tablero_player2[fil][col] = hit;
                    player2_barcos--;
                }
                else if (tablero_player2[fil][col] == agua) {
                    System.out.println("\nLA BOMBA CAYO EN EL AGUA");
                    tablero_player2[fil][col] = fail;
                }
            }
            else if ((fil < 0 || fil >= filas) || (col < 0 || col >= columnas)) {
                System.out.println("NO PUEDES BOMBARDEAR FUERA DEL TABLERO");
                fn.pause(1000);
            }
        }while((fil < 0 || fil >= filas) || (col < 0 || col >= columnas));
    }

    public void turno_player2() {
        do {
            fn.clear();

            System.out.println("TURNO - PLAYER 2\n");

            print_tablero1();

            System.out.println("\nCoordenada de bomba (Player 2): ");
            System.out.println(player.get_usuario() + " tiene " + player2_barcos + " barcos aun\n");

            System.out.print("Fila: ");
            fil = input.nextInt();
            input.nextLine();

            System.out.print("Columna: ");
            col = input.nextInt();
            input.nextLine();

            fil--;
            col--;

            if ((fil >= 0 && fil < filas) && (col >= 0 && col < columnas)) {
                if (tablero_player1[fil][col].contains(ship[0].get_codigo_barco()) || tablero_player1[fil][col].contains(ship[2].get_codigo_barco()) || 
                tablero_player1[fil][col].contains(ship[1].get_codigo_barco()) || tablero_player1[fil][col].contains(ship[3].get_codigo_barco())) {
                    System.out.println("\nSE HA BOMBARDEADO UN " + get_nombre_barco(1, fil, col));
                    System.out.println("SE HUNDIO UN " + get_nombre_barco(1, fil, col) + " del Player 1");
                    tablero_player1[fil][col] = hit;
                    player1_barcos--;
                }
                else if (tablero_player1[fil][col] == agua) {
                    System.out.println("\nLA BOMBA CAYO EN EL AGUA");
                    tablero_player1[fil][col] = fail;
                }
            }
            else if ((fil < 0 || fil >= filas) || (col < 0 || col >= columnas)) {
                System.out.println("NO PUEDES BOMBARDEAR FUERA DEL TABLERO");
                fn.pause(1000);
            }
        }while((fil < 0 || fil >= filas) || (col < 0 || col >= columnas));
    }

    public boolean eval_rendirse(int valor, int _p) {
        if(valor == -1) {
            System.out.println("\nEsta seguro de que desea retirarse del juego? (S/n)");
            opcion = input.next().charAt(0);
            input.nextLine();

            if(opcion == 'S' || opcion == 's') {
                if(_p == 1)
                    ren_1 = true;
                else if(_p == 2)
                    ren_2 = true;

                return true;
            }
        }

        return false;
    }

    public void game_over() {
        String ganador = "";
        int indice = 0;

        fn.clear();

        System.out.println(color.LIGHT_BLUE + "EL JUEGO HA TERMINADO\n");
        
        if(player2_barcos == 0 || ren_2) {
            System.out.println("El ganador ha sido " + player.get_usuario());
            ganador = player.get_usuario();

            if(ren_2)
                player.set_juego(player2 + " se retiro del juego dejando como ganador a  " + player.get_usuario());
            else
                player.set_juego(player.get_usuario() + " hundio todos los barcos de " + player2 + " en modo " + dificultad);
        }
        else if(player1_barcos == 0 || ren_1) {
            System.out.println(color.WHITE + "El ganador ha sido " + color.LIGHT_BLUE + player2);
            ganador = player2;

            if(ren_1)
                player.set_juego(player.get_usuario() + " se retiro del juego dejando como ganador a  " + player2);
            else
                player.set_juego(player2 + " hundio todos los barcos de " + player.get_usuario()  + " en modo " + dificultad);
        }

        for(String _ganador : player.usuarios) {
            if(_ganador == ganador)
                indice = player.index_usuario(ganador);
                player.set_puntaje(indice, 3);
        }

        System.out.print(color.RESET);
        fn.pause(2000);

        menu_principal();
    }

    public void set_dificultad(int n) {
        if(n == 1)
            dificultad = dificultad_nombre[0]; //easy
        else if(n == 2)
            dificultad = dificultad_nombre[1]; //normal
        else if(n == 3)
            dificultad = dificultad_nombre[2]; //expert
        else if(n == 4)
            dificultad = dificultad_nombre[3]; //genius
    }

    public void set_modo(int n) {
        if(n == 1)
            modo = modo_const[0]; //arcade
        else if(n == 2)
            modo = modo_const[1]; //tutorial
    }

    public String get_nombre_barco() {
        return Barco;
    }

    public int get_bombas_barco() {
        return Bombas;
    }

    public String get_codigo_barco() {
        return Codigo;
    }

    public String get_nombre_barco(int _tablero, int _fila, int _columna) {
        String nombre_barco = "";

        if(_tablero == 1) {
            if(tablero_player1[_fila][_columna].contains(ship[0].get_codigo_barco()))
                nombre_barco = ship[0].get_nombre_barco();
            else if(tablero_player1[_fila][_columna].contains(ship[1].get_codigo_barco()))
                nombre_barco = ship[1].get_nombre_barco();
            else if(tablero_player1[_fila][_columna].contains(ship[2].get_codigo_barco()))
                nombre_barco = ship[2].get_nombre_barco();
            else if(tablero_player1[_fila][_columna].contains(ship[3].get_codigo_barco()))
                nombre_barco = ship[3].get_nombre_barco();
        }else if(_tablero == 2) {
            if(tablero_player2[_fila][_columna].contains(ship[0].get_codigo_barco()))
                nombre_barco = ship[0].get_nombre_barco();
            else if(tablero_player2[_fila][_columna].contains(ship[1].get_codigo_barco()))
                nombre_barco = ship[1].get_nombre_barco();
            else if(tablero_player2[_fila][_columna].contains(ship[2].get_codigo_barco()))
                nombre_barco = ship[2].get_nombre_barco();
            else if(tablero_player2[_fila][_columna].contains(ship[3].get_codigo_barco()))
                nombre_barco = ship[3].get_nombre_barco();
        }

        return nombre_barco;
    }
}
