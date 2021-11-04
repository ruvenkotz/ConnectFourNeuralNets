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
    FileWriter csvWriter = new FileWriter(new File("data.txt"));
    for(int i = 1; i <= 42; i++) {
      csvWriter.append("x" + i + ",");
    }
    csvWriter.append("r");
    csvWriter.append("\n");
    for(int[] rowData : data) {   
      StringBuilder s = new StringBuilder();
      for(int i =0; i < rowData.length-1;i++) {
        s.append(rowData[i] + ",");
      }
      s.append(rowData[42]);
      csvWriter.append(s);
      csvWriter.append("\n");
    }
    csvWriter.flush();
    csvWriter.close();
  }


  private static List<int[]> cleanData(int games){
    List<int[]> data = new ArrayList<>();

    for(int i = 0; i < games; i++) {
        List<int[][]> history= Game.playGame();
        int result = history.get(history.size()-1)[0][0];

        //Look at an entire game
        for(int j = 0; j < history.size()-1; j++) {
        	int[][] gameState = history.get(j);
     
          int[] newInput = new int[43];
          int index = 0;
        	for(int a = 0; a < 6; a++) {
        		for(int b = 0; b < 7; b++) {
        			newInput[index] = gameState[a][b];
        			index++;
        		}
        	}
        	newInput[42] = result;
        	data.add(newInput);
        	
      }
   
    }
    return data;

  }



  public static void getWins(int games){
    int player1Wins = 0;
    int player2Wins = 0;
    int draws = 0;
    for(int i = 0; i < games; i++){
      List<int[][]> history = Game.playGame();
      int result = history.get(history.size()-1)[0][0];
      if(result == 1)
        player1Wins++;
      else if(result == -1)
        player2Wins++;
      else if(result == 0)
        draws++;
    }
   System.out.println("Player One Wins: " + player1Wins);
   System.out.println("Player Two Wins: " + player2Wins);
   System.out.println("Draws: " + draws); 


  }
  public static void getOneGame(){
       List<int[][]> history = Game.playGame();
    for (int[][] i : history) {
          print2D(i);
          System.out.println();
	}

  }
  
  public static void print2D(int mat[][]){
    // Loop through all rows
    for (int[] row : mat)

        // converting each row as string
        // and then printing in a separate line
        System.out.println(Arrays.toString(row));
}
}
