public class Battleship {
    static char matriz[][] = new char[8][8];

    public void tablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matriz[i][j] = '~';
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void normal() {
        tablero();
    }
}
