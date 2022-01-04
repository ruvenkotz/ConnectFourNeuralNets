package ConnectFourNeuralNets;

import java.util.List;

/**
 * This class contains all the engines takes in a board and a list of 
 * avaible moves and outputs a move
 */
public class Engines {
  /**
   * 
   * @param avaibleMoves
   * @return a purely random moves
   */
  public static int pureRandom(List<Integer> avaibleMoves){
    int randomChoice = (int) (Math.random() * avaibleMoves.size());
    return avaibleMoves.get(randomChoice);
  }
  
  /**
   * Plays a winning move, blocks a winning move, or plays a random move
   * @param avaibleMoves
   * @param b
   * @param player
   * @return
   */
  public static int winOrBlock(List<Integer> avaibleMoves, Board b, int player){
    int res = winOrBlockHelper(avaibleMoves, b, player);
    //If those moves don't exist, return a random move
    if( res == -1)
      return pureRandom(avaibleMoves);
    else
      return res;
  }

  public static int winOrBlockHelper(List<Integer> avaibleMoves, Board b, int player){
    //Check for a move that will win you the game
    for(int i = 0; i < avaibleMoves.size(); i++){
      int col = avaibleMoves.get(i);
      if(Game.checkForWin(player, b.getRow(col), col, b.getBoard())){
          return col;
      }
    }
    if(player == 1)
      player = 2;
    else
      player = 1;
    //Check for a move that will win the game for you opponent
    for(int i = 0; i < avaibleMoves.size(); i++){
      int col = avaibleMoves.get(i);
      if(Game.checkForWin(player, b.getRow(col), col, b.getBoard())){
          return col;
      }
    }
    return -1;

  
  }
  /**
   * centerPlay uses WinOrBlock but if that's not avaible, plays in the 
   * center of the board. The center square is (4,3).
   * @param avaibleMoves
   * @param b
   * @param player
   * @return
   */
  public static int centerPlay(List<Integer> avaibleMoves, Board b, int player){
    int res = winOrBlockHelper(avaibleMoves, b, player);
    //If those moves don't exist, return a random move
    if( res == -1){
      int bestMove = -1;
      int bestMoveValue = 100;
      for(int i = 0; i < avaibleMoves.size(); i++){
        int col = avaibleMoves.get(i);
        int row = b.getRow(col);
        int value = Math.abs(row-4) + Math.abs(col-3);
        if(value < bestMoveValue || (value == bestMoveValue && Math.random() > .5)){
          bestMove = col;
          bestMoveValue = value;
        }
      }
      return bestMove;
    }
    else
      return res;
  }
  
  public static int threeInARows(List<Integer> avaibleMoves, Board b, int player){
    int res = winOrBlockHelper(avaibleMoves, b, player);
    if(res != -1) return res;
    int[][] board = b.getBoard();
    int bestMove = -1;
    int bestMoveValue = 0;

    for(int i = 0; i < avaibleMoves.size(); i++){
      int col = avaibleMoves.get(i);
      int row = b.getRow(col);
      int [][] copy = new int[6][7];
      for(int j = 0; j < board.length; j++)
          copy[j] = board[j].clone();
      copy[row][col] = player;
      int value = threeInARowsHelper(copy, player);
      if(value > bestMoveValue || (value > 0 && value == bestMoveValue && Math.random() > .5)){
        bestMove = col;
        bestMoveValue = value;
      }
    }
    if(bestMove == -1)
      return centerPlay(avaibleMoves, b, player);
    else
      return bestMove;    
  }

  public static int threeInARowsHelper(int[][] board, int player){
    int playerScore = 0;
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++){
        if(board[i][j] == 0){
          if(Game.checkForWin(player, i, j, board) == true)
            playerScore++;
        }
      }
    }
    return playerScore;
  }

  public static int neuralNetwork(List<Integer> avaibleMoves, Board b, int player, Model m){
    int[][] board = b.getBoard();
    int bestMove = -1;
    double bestMoveValue = 0;

    for(int i = 0; i < avaibleMoves.size(); i++){
      int col = avaibleMoves.get(i);
      int row = b.getRow(col);

      //Reshape the board
      double[] reshaped = new double[42];
      int index = 0;
      for(int x = 0; x < 6; x++){
        for(int y = 0; y<7;y++){
          if(x == row && y == col) 
            reshaped[index] = (double) player;
          else
            reshaped[index] = (double) board[x][y];
          index++;
        }
      }
      double value = m.forward(reshaped)[player-1];
      if(value > bestMoveValue){
        bestMove = col;
        bestMoveValue = value;
      }
    }
    return bestMove;
  }

  public static int neuralNetworkNew(List<Integer> avaibleMoves, Board b, int player, Model m){
    int bestMove = -1;
    double bestMoveValue = 0;

    for(int i = 0; i < avaibleMoves.size(); i++){
      int[][] board = b.getBoard();
      int col = avaibleMoves.get(i);
      int row = b.getRow(col);
      board[row][col] = player;
      double value = m.forward(Position.getPosition(board, player, row, col))[player-1];
      board[row][col] = 0;
      if(value > bestMoveValue){
        bestMove = col;
        bestMoveValue = value;
      }
    }
    return bestMove;
  }



}
