package ConnectFourNeuralNets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class data {

  /**
   * writeData plays games and then adds each game state tagged with the end 
   * result of the game
   * @param games numbers of games played
   * @throws IOException
   */
  public static void writeData(int games) throws IOException{ 
    List<int[]> data = cleanData(games);
    FileWriter csvWriter = new FileWriter(new File("Random2.csv"));
    for(int i = 1; i <= 42; i++) {
      csvWriter.append("x" + i + ",");
    }
    csvWriter.append("w,");
    csvWriter.append("l,");
    csvWriter.append("d");
    csvWriter.append("\n");
    for(int[] rowData : data) {   
      StringBuilder s = new StringBuilder();
      for(int i =0; i < rowData.length-1;i++) {
        s.append(rowData[i] + ",");
      }
      s.append(rowData[44]);
      csvWriter.append(s);
      csvWriter.append("\n");
    }
 
  }

/**
 * Plays games and coverts the gamestates into 1 x 43 
 * to be used by a neural net
 * @param games number of games
 * @return formatted data
 */
  private static List<int[]> cleanData(int games){
    List<int[]> data = new ArrayList<>();

    for(int i = 0; i < games; i++) {
        List<int[][]> history= Game.playGame();
        int[][] result = history.get(history.size()-1);

        //Look at an entire game
        for(int j = 0; j < history.size()-1; j++) {
        	int[][] gameState = history.get(j);
          int[] newInput = new int[45];
          int index = 0;
        	for(int a = 0; a < 6; a++) {
        		for(int b = 0; b < 7; b++) {
        			newInput[index] = gameState[a][b];
        			index++;
        		}
        	}
          for(int a = 0; a < 3; a++)
        	  newInput[a+42] = result[0][a];
        	data.add(newInput);
        	
      }
   
    }
    return data;

  }
  
  /**
 * Plays games and coverts the gamestates into 1 x 43 
 * to be used by a neural net
 * @param games number of games
 * @return formatted data
 */
private static List<int[]> cleanDataEndgames(int games){
  List<int[]> data = new ArrayList<>();

  for(int i = 0; i < games; i++) {
      List<int[][]> history= Game.playGame();
      int[][] result = history.get(history.size()-1);

      //Add only the second to last game
      int[][] gameState = history.get(history.size()-3);
      int[] newInput = new int[45];
      int index = 0;
      for(int a = 0; a < 6; a++) {
        for(int b = 0; b < 7; b++) {
          newInput[index] = gameState[a][b];
          index++;
        }
      }
      for(int x = 0; x < 3; x++)
        newInput[x+42] = result[0][x];
      data.add(newInput);
      
    
 
  }
  return data;

}

/**
 * getWins can be used if you want to pit two of the same engines against 
 * each other
 * @param games number of games
 */
  public static void getWins(int games){
    int player1Wins = 0;
    int player2Wins = 0;
    int draws = 0;
    for(int i = 0; i < games; i++){
      List<int[][]> history = Game.playGame();
      int[][] result = history.get(history.size()-1);
      if(result[0][0] == 1)
        player1Wins++;
      else if(result[0][1] == 1)
        player2Wins++;
      else if(result[0][2] == 1)
        draws++;
    }
   System.out.println("Player One Wins: " + player1Wins);
   System.out.println("Player Two Wins: " + player2Wins);
   System.out.println("Draws: " + draws); 


  }
  /**
   * Used for testing to play one game and then print the history 
   */
  public static void getOneGame(){
    List<int[][]> history = Game.playGame();
    for (int[][] i : history) {
          print2D(i);
          System.out.println();
	}

  }
  /**
   * Helper that pretty prints a 2D array
   * @param mat
   */
  public static void print2D(int mat[][]){
    // Loop through all rows
    for (int[] row : mat)

        // converting each row as string
        // and then printing in a separate line
        System.out.println(Arrays.toString(row));
    System.out.println();
}
public static void print2DDouble(double mat[][]){
  // Loop through all rows
  for (double[] row : mat)

      // converting each row as string
      // and then printing in a separate line
      System.out.println(Arrays.toString(row));
  System.out.println();
}

}
