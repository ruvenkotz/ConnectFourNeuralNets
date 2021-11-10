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
  public static int winOrBlock(List<Integer> avaibleMoves, Board b, int player){
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
    //If those moves don't exist, return a random move
    return pureRandom(avaibleMoves);

  }



}
