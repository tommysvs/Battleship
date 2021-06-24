import java.util.Random;
import java.util.Scanner;

public class Battleship extends Main {
    Scanner input = new Scanner(System.in);

    static Colors color = new Colors();
    static Functions fn = new Functions();

    //constantes para definir la dificultad del juego y el modo de juego
    static String[] dificultad_const = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    static int[] dificultad_barcos = {5, 4, 2, 1};
    static String[] modo_const = {"ARCADE", "TUTORIAL"};

    static String dificultad = "NORMAL";
    static String modo = "TUTORIAL";

    String agua = "~";
    String hit = "X";
    String fall = "F";

    Battleship[] ship = new Battleship[4];
    String Barco;
    int Bombas;
    String Codigo;

    static String matriz[][] = new String[8][8];
    static Player p1 = new Player();
    static String player2 = "";

    int filas = 8, columnas = 8;
    int fil, col;
    String _codigo;

    int player1_barcos = 0, player2_barcos = 0;

    char opcion;
    boolean ren_1 = false;
    boolean ren_2 = false;
 
    Battleship() { }

    Battleship(String s, int b, String c) {
        Barco = s;
        Bombas = b;
        Codigo = c;
    }

    public void set_ships() {
        ship[0] = new Battleship("Portaaviones", 5, "PA");
        ship[1] = new Battleship("Acorazado", 4, "AZ");
        ship[2] = new Battleship("Submarino", 3, "SM");
        ship[3] = new Battleship("Destructor", 2, "DT");
    }

    public void jugar(String _player2) {
        player2 = _player2;
        ren_1 = false;
        ren_2 = false;

        iniciar_tablero();

        if(dificultad == "EASY") {
            player1_barcos = dificultad_barcos[0];
            player2_barcos = dificultad_barcos[0];

            do {
                easy();
            }while(player1_barcos != 0 && player2_barcos != 0);
        }
        else if(dificultad == "NORMAL") {
            player1_barcos = dificultad_barcos[1];
            player2_barcos = dificultad_barcos[1];

            do {
                normal();
            }while(player1_barcos != 0 && player2_barcos != 0);
        }
        else if(dificultad == "EXPERT") {
            player1_barcos = dificultad_barcos[2];
            player2_barcos = dificultad_barcos[2];

            do {
                expert();
            }while(player1_barcos != 0 && player2_barcos != 0);
        }
        else if(dificultad == "GENIUS") {
            player1_barcos = dificultad_barcos[3];
            player2_barcos = dificultad_barcos[3];

            do {
                genius();
            }while(player1_barcos != 0 && player2_barcos != 0);
        }

        game_over();
    }

    public void iniciar_tablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = agua;
            }
        }
    }

    public void tablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(color.CYAN + matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print(color.RESET);
    }

    public void posicionar_player1() {
        fn.clear();
        System.out.println("BATTLESHIP\n");

        System.out.println("Turno del PLAYER 1: " + p1.get_usuario());

        for(int i = 1; i <= dificultad_barcos[1];) {
            System.out.print("Ingrese el codigo del barco: ");
            _codigo = input.nextLine();
            
            System.out.print("Ingrese el indice de la fila: ");
            fil = input.nextInt();
            input.nextLine();

            if(eval_rendirse(fil, 1))
                break;

            System.out.print("Ingrese el indice de la columna: ");  
            col = input.nextInt();
            input.nextLine();

            if(eval_rendirse(col, 1))
                break;

            if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas))
            {
                matriz[fil][col] = _codigo;
                i++;
            }
        }

        game_over();
    }

    public void posicionar_player2() {
        fn.clear();
        System.out.println("BATTLESHIP\n");

        System.out.println("Turno del PLAYER 2: " + player2);
        
        for(int i = 1; i <= dificultad_barcos[1];) {
            System.out.print("Ingrese el codigo del barco: ");
            _codigo = input.nextLine();
            
            System.out.print("Ingrese el indice de la fila: ");
            fil = input.nextInt();
            input.nextLine();

            if(eval_rendirse(fil, 2))
                break;
            
            System.out.print("Ingrese el indice de la columna: ");  
            col = input.nextInt();
            input.nextLine();

            if(eval_rendirse(col, 2))
                break;

            if((fil >= 0 && fil < filas) && (col >= 0 && col < columnas)) {
                matriz[fil][col] = _codigo;
                i++;
            }
        }

        game_over();
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

    public void easy() {
        iniciar_tablero();
    }

    public void normal() {
        posicionar_player1();
        posicionar_player2();

        tablero();
    }

    public void expert() {
        iniciar_tablero();
    }

    public void genius() {
        iniciar_tablero();
    }

    public void game_over() {
        String ganador = "";
        int indice = 0;

        fn.clear();

        System.out.println("EL JUEGO HA TERMINADO\n");
        
        if(player2_barcos == 0 || ren_2 == true) {
            System.out.println("El ganador ha sido " + p1.get_usuario());
            ganador = p1.get_usuario();
        }
        else if(player1_barcos == 0 || ren_1 == true) {
            System.out.println("El ganador ha sido " + player2);
            ganador = player2;
        }

        for(String _ganador : p1.usuarios) {
            if(_ganador == ganador)
                indice = p1.index_usuario(ganador);
                p1.set_puntaje(indice, 3);
        }

        fn.pause(2000);

        menu_principal();
    }

    public void set_dificultad(int n) {
        if(n == 1)
            dificultad = dificultad_const[0]; //easy
        else if(n == 2)
            dificultad = dificultad_const[1]; //normal
        else if(n == 3)
            dificultad = dificultad_const[2]; //expert
        else if(n == 4)
            dificultad = dificultad_const[3]; //genius
    }

    public void set_modo(int n) {
        if(n == 1)
            modo = modo_const[0]; //arcade
        else if(n == 2)
            modo = modo_const[1]; //tutorial
    }
}
