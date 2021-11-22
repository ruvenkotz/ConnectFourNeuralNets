package ConnectFourNeuralNets;

import java.util.List;
import java.util.Random;

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
  




}
