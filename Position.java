package ConnectFourNeuralNets;
/*
  Features for the Neural Network:
  0-6 Tiles in each Column for player 1
  7-13 Tiles in each Column for player 2
  14-19 Tiles in each Row for player 1
  20-25 Tiles in each Row for player 2
  26-27 Four in a rows for player 1 and 2
  28-29 Three in a rows for player 1 and 2
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Position {
  static double[] position = new double[30];
  static int index = 0;

  private static void countColumn(int[][] board){
    for(int c = 0; c < 7; c++){
      int count1 = 0;
      int count2 = 0;
      for(int r = 0; r < 6; r++){
        if(board[r][c] == 1)
          count1++;
        else if(board[r][c] == 2)
          count2++;
      }
      position[index] = count1;
      position[index+7] = count2;
      index++;
    }
    index+=7;
  }

  private static void countRow(int[][] board){
    for(int r = 0; r < 6; r++){
      int count1 = 0;
      int count2 = 0;
      for(int c = 0; c < 7; c++){
        if(board[r][c] == 1)
          count1++;
        else if(board[r][c] == 2)
          count2++;
      }
      position[index] = count1;
      position[index+6] = count2;
      index++;
    }
    index+=6;
  }
  private static void countInARows(int player, int[][] board, int r, int c){
    //Checking for a four-in-a-row
    if(Game.checkForWin(player, r, c, board) == true)
      position[index+player-1] = 1;
    else
      position[index+player-1] = 0;
    position[index+Board.getOpp(player)-1] = 0;
    index+=2;
    //Checking for three-in-a-row
    position[index] = Engines.threeInARowsHelper(board, 1);
    index++;
    position[index] = Engines.threeInARowsHelper(board, 2);
    index++;

  }
  public static double[] getPosition(int[][] board, int player, int r, int c){
    countColumn(board);
    countRow(board);
    countInARows(player, board, r, c);
    index = 0;
    return position;
  }

  public static void FixData() throws IOException{
    FileWriter csvWriter = new FileWriter(new File("Position.csv"));
    for(int i = 1; i <= 30; i++) {
      csvWriter.append("x" + i + ",");
    }
    csvWriter.append("w,");
    csvWriter.append("l,");
    csvWriter.append("d");
    csvWriter.append("\n");

    File history = new File("NewStuff\\FinalHistoryNew.csv");
    File queried = new File("NewStuff\\FixedQueryNew.csv");


    try (BufferedReader hReader = new BufferedReader(new FileReader(history));
         BufferedReader qReader = new BufferedReader(new FileReader(queried)) ) {
      String st;
      Board b = new Board();
      int player = 1;
      int count = 0;
      while ((st = hReader.readLine()) != null){
        count++;
        String query = qReader.readLine();
        if(query.length() == 1){
          b = new Board();
          player = 1;
        }
        // data.print2D(b.getBoard());
        int col = Character.getNumericValue(query.charAt(query.length()-1))-1;
        int row = b.placeATile(col, player);
        double[] pos = getPosition(b.getBoard(), player, row, col);
        csvWriter.append(Arrays.toString(pos).replace(" ", "").replace("[","").replace("]",""));
        String[] outcome = st.split(",");
        csvWriter.append("," + outcome[42]);
        csvWriter.append("," + outcome[43]);
        csvWriter.append("," + outcome[44] + "\n");

        player = Board.getOpp(player);
        System.out.println(count);
      }
    }


  }



  
}
