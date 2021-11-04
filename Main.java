package ConnectFourNeuralNets;

import java.util.Arrays;
import java.util.List;

public class Main {
   public static void main(String[] args) {
     data.getWins();
  //   List<int[][]> history = game.playGame();
  //   for (int[][] i : history) {
  //         print2D(i);
  //         System.out.println();
	// }

  
  }

  
  public static void print2D(int mat[][]){
      // Loop through all rows
      for (int[] row : mat)

          // converting each row as string
          // and then printing in a separate line
          System.out.println(Arrays.toString(row));
  }

}