package ConnectFourNeuralNets;

import java.util.Scanner;

public class gameTest{
public static void playGame(){
  Board b = new Board();
  Model m1 = new Model("rand_model_init.txt");
  Model m2 = new Model("rand_model_2.txt");
  Model m3 = new Model("rand_model_2_new.txt");
  Model m4 = new Model("positions.txt");

  int player = 1;
  boolean gameFinished = false;
  int movesPlayed = 0;
  Scanner scanner = new Scanner(System.in);
  while(!gameFinished){
    int move = 0;
    int[][] board = b.getBoard();
    data.print2D(board);
    if(player == 1){
        System.out.println("Make a move: (1-7) ");
        move = Integer.parseInt(scanner.nextLine())-1;  // Read user input
      }
    else
      move = Engines.neuralNetworkNew(b.getMoves(), b, player, m4); 
    int row = b.placeATile(move, player);
    //Copy and add the new board
    //Check for end of game
    if(Game.checkForWin(player, row, move, board)){
       int[][] result = new int[1][3]; 
       result[0][player-1] = 1;
       gameFinished = true;
       data.print2D(board);
       System.out.println("Player " + player + " wins!");
    }
    else if(movesPlayed == 41){
      int[][] result = new int[1][3]; 
      result[0][2] = 1;
      gameFinished = true;
      System.out.println("Draw!");
    }
    movesPlayed++;
    player = Board.getOpp(player);
  }
}
}