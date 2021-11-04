package ConnectFourNeuralNets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
  private int[][] b;
  private List<Integer> availbleMoves;
  private int[] nextMove;
  /**
   * board creates an empty board and adds the bottom row into the list of
   * avaibleMoves
   */
  public Board() {
    b = new int[6][7];
    availbleMoves = new ArrayList<>();
    nextMove = new int[7];
    for (int i = 0; i <= 6; i++) {
      availbleMoves.add(i);
      nextMove[i] = 5;
    }
  }

  /**
   * getBoard returns the board
   * 
   * @return board
   */
  public int[][] getBoard() {
    return b;
  }

  /**
   * getMoves returns the availble columns to place pieces into
   */
  public List<Integer> getMoves(){
    return availbleMoves;
  }

  public int getRow(int col){
    return nextMove[col];
  }

  /**
   * placeATile plays a tile and updates availbleMoves
   * 
   * @param move   the column chosen by the player to place a tile
   * @param player the player that made the move
   * @return the row that the tile was placed in
   */
  public int placeATile(int move, int player) {
      int row = nextMove[move];
      if (b[row][move] == 0) {
        b[row][move] = player;
        if (row > 0) {
          nextMove[move] = row-1;
        }
        else{
          availbleMoves.remove((Integer) move);
        }
      }
      return nextMove[move] +1;
    
  }

  /**
   * printBoard prints out the board
   */
  public void printBoard() {
    for (int[] i : b) {
      System.out.println(Arrays.toString(i));
    }
    System.out.println();
  }

  /**
   * resets the board, sets every position to 0
   */
  public void resetBoard(){
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++)
       b[i][j] = 0;
    }
  }


}