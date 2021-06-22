import java.util.Random;
import java.util.Scanner;

public class Battleship {
    //constantes para definir la dificultad del juego y el modo de juego
    static String[] dificultad_const = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    static String[] modo_const = {"ARCADE", "TUTORIAL"};

    static String dificultad = "NORMAL";
    static String modo = "TUTORIAL";

    static char matriz[][] = new char[8][8];
    static Player pl = new Player();
    static String player2 = "";

    static Colors color = new Colors();

    String Barco;
    int Bombas;
    String Codigo;

    Battleship() { }

    Battleship(String s, int b, String c) {
        Barco = s;
        Bombas = b;
        Codigo = c;
    }

    public void set_ships() {
        Battleship[] ship = new Battleship[4];
    
        ship[0] = new Battleship("Portaaviones", 5, "PA");
        ship[1] = new Battleship("Acorazado", 4, "AZ");
        ship[2] = new Battleship("Submarino", 3, "SM");
        ship[3] = new Battleship("Destructor", 2, "DT");
    }

    public void tablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matriz[i][j] = '~';
                System.out.print(color.CYAN + matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print(color.RESET);
    }

    public void easy() {

    }

    public void normal() {
        tablero();
    }

    public void expert() {

    }

    public void genius() {
        
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
