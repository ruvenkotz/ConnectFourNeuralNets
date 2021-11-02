package ConnectFourNeuralNets;


public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    board.printBoard();
    board.placeATile(4, 1);
    board.printBoard();

  }

}