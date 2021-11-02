package ConnectFourNeuralNets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
  private int[][] b;
  List<int[]> availbleMoves;

  /**
   * board creates an empty board and adds the bottom row into the list of
   * avaibleMoves
   */
  public Board() {
    b = new int[6][7];
    availbleMoves = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      int[] tup = { 5, i };
      availbleMoves.add(tup);
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
   * placeATile plays a tile and updates availbleMoves
   * 
   * @param move   the column chosen by the player to place a tile
   * @param player the player that made the move
   */
  public void placeATile(int move, int player) {
    for (int i = 5; i >= 0; i--) {
      if (b[i][move] == 0) {
        b[i][move] = player;
        int[] obj = { i, move };
        availbleMoves.remove(obj);
        if (i >= 0) {
          int[] tup = { i - 1, move };
          availbleMoves.add(tup);
        }
        break;
      }
    }
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

}