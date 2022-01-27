package ConnectFourNeuralNets;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {
    public static List<int[][]> playGameNew(){
        Board b = new Board();
        Model m1 = new Model("ConnectFourNeuralNets\\Model3\\random.txt");    
        int player = 1;
        List<int[][]> history = new LinkedList<>();
        boolean gameFinished = false;
        int movesPlayed = 0;
        while(!gameFinished){
          int move = 0;
          if(player == 1)
            move = Engines.pureRandom(b.getMoves()); 
          else
            move = Engines.pureRandom(b.getMoves()); 
          int row = b.placeATile(move, player);

          //Copy and add the new board
          int[][] board = b.getBoard();
          data.print2D(board);
          double [] position = Position.getPosition(board, player, row, move);
          System.out.println(Arrays.toString(position) + "\n");
          //Check for end of game
          if(checkForWin(player, row, move, board)){
             int[][] result = new int[1][3]; 
             result[0][player-1] = 1;
             history.add(result);
             gameFinished = true;
          }
          else if(movesPlayed == 41){
            int[][] result = new int[1][3]; 
            result[0][2] = 1;
            history.add(result);
            gameFinished = true;
          }
          movesPlayed++;
          player = Board.getOpp(player);
        }
        // b.resetBoard();
        return history;
      }

/**
 * Plays a game and returns the history
 * @return
 */
  public static List<int[][]> playGame(){
    Board b = new Board();
    Model m1 = new Model("positions.txt");
    // Model m2 = new Model("ConnectFourNeuralNets\\Alex'sNewModel\\Model2.txt");
    // Model m3 = new Model("ConnectFourNeuralNets\\Alex'sNewModel\\Model3.txt");



    int player = 1;
    List<int[][]> history = new LinkedList<>();
    boolean gameFinished = false;
    int movesPlayed = 0;
    while(!gameFinished){
      int move = 0;
      if(player == 2)
       move = Engines.winOrBlock(b.getMoves(), b, player); 
        // move = Engines.pureRandom(b.getMoves()); 
      else
        // move = Engines.pureRandom(b.getMoves()); 
        move = Engines.neuralNetworkNew(b.getMoves(), b, player, m1); 
        // move = Engines.threeInARows(b.getMoves(), b, player); 
      int row = b.placeATile(move, player);
      //Copy and add the new board
      int[][] board = b.getBoard();
    //   data.print2D(board);
      int [][] copy = new int[6][7];
      for(int i = 0; i < board.length; i++)
          copy[i] = board[i].clone();
      history.add(copy);
      //Check for end of game
      if(checkForWin(player, row, move, board)){
         int[][] result = new int[1][3]; 
         result[0][player-1] = 1;
         history.add(result);
         gameFinished = true;
      }
      else if(movesPlayed == 41){
        int[][] result = new int[1][3]; 
        result[0][2] = 1;
        history.add(result);
        gameFinished = true;
      }
      movesPlayed++;
      player = Board.getOpp(player);
    }
    // b.resetBoard();
    return history;
  }

  /**
   * checkForWin checks if the move played makes a four in a row
   * @param player who played the move
   * @param r row 
   * @param c column
   * @param board board after move was played
   * @return true if the player won the game
   */
  public static boolean checkForWin(int player, int r, int c, int[][] board){
    if(r == -1 && c == -1) 
      return false;
    int depth = 1;
    int row = r;
    int col = c;

    // Checking downward
    while (r != 0 && board[r - 1][c] == player){ 
        r = r - 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking upward
    while (r != 5 && board[r + 1][c] == player){
        r = r + 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking to the left
    while (c != 0 && board[r][c - 1] == player || (depth == 3 && c <=3 && board[r][c+3] == player)){
        c = c - 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking to the right
  while (c != 6 && board[r][c + 1] == player || (depth == 3 && c >=3 && board[r][c-3] == player)){
        c = c + 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking diagonally down and to the left
    while (r != 0 && c != 0 && board[r - 1][c - 1] == player || (depth == 3 && r <= 2 && c <=3 && board[r+3][c+3] == player)){
        r = r - 1;
        c = c - 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking diagonally up and to the left
    while (r != 5 && c != 0 && board[r + 1][c - 1] == player || (depth == 3 && r >= 3 && c <=3 && board[r-3][c+3] == player)){
        r = r + 1;
        c = c - 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking diagonally down and to the right
    while (r != 0 && c != 6 && board[r - 1][c + 1] == player || (depth == 3 && r <= 2  && c >=3  && board[r+3][c-3] == player)){
        r = r - 1;
        c = c + 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }
    depth = 1;
    r = row;
    c = col;

    //Checking diagonally up and to the right
    while (r != 5 && c != 6 && board[r + 1][c + 1] == player || (depth == 3 && r >= 3 && c >=3 && board[r-3][c-3] == player)){
        r = r + 1;
        c = c + 1;
        depth = depth + 1;
        if (depth == 4){
            return true;
        }
    }


    return false;
  
  }




}
