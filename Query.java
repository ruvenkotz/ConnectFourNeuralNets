package ConnectFourNeuralNets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Query {
  
  public static QueryObj playGame(){
    Board b = new Board();
    int player = 1;
    QueryObj q = new QueryObj();
    List<int[][]> history = new LinkedList<>();
    List<String> queryList = new LinkedList<>(); 
    boolean gameFinished = false;
    int movesPlayed = 0;
    String current = "";
    while(!gameFinished){
      int move = 0;
      if(player == 1)
        move = Engines.pureRandom(b.getMoves()); 
      else
        move = Engines.pureRandom(b.getMoves()); 
      int row = b.placeATile(move, player);
      //Copy and add the new board
      int[][] board = b.getBoard();
    //   data.print2D(board);
      int [][] copy = new int[6][7];
      for(int i = 0; i < board.length; i++)
          copy[i] = board[i].clone();
      history.add(copy);
      String StringCopy = current + (move+1);
      current = StringCopy;
      queryList.add(StringCopy);
      //Check for end of game
      if(Game.checkForWin(player, row, move, board)){
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
    q.setHistory(history);
    q.setQueryList(queryList);
    return q;
  }


  private static List<int[]> cleanData(List<int[][]> history){
    List<int[]> data = new ArrayList<>();
      //Look at an entire game
      for(int j = 0; j < history.size()-1; j++) {
        int[][] gameState = history.get(j);
        int[] newInput = new int[42];
        int index = 0;
        for(int a = 0; a < 6; a++) {
          for(int b = 0; b < 7; b++) {
            newInput[index] = gameState[a][b];
            index++;
          }
        }
        data.add(newInput);
        	
      }
   
    
    return data;

  }


  public static void getResults(int games) throws IOException{
    FileWriter csvWriterHistory = new FileWriter(new File("FixedHistoryMac.csv"));
    FileWriter csvWriterQuery = new FileWriter(new File("FixedQueryMac.csv"));
    for(int i = 1; i <= 41; i++) {
      csvWriterHistory.append("x" + i + ",");
    }
    csvWriterHistory.append("x42" + "\n" );
    for(int i = 0; i <games; i++ ){
      QueryObj o = playGame();
      List<String> queries = o.getQueryList();
      List<int[]> history = cleanData(o.getHistory());
      for(String q: queries){
        csvWriterQuery.append(q);
        csvWriterQuery.append("\n");
      }
      for(int[] h: history){
        for(int x = 0; x < h.length-1; x++){
            csvWriterHistory.append(h[x] + ",");
        }
        csvWriterHistory.append(h[41] + "\n");
      } 
  }
    csvWriterHistory.flush();
    csvWriterHistory.close();
    csvWriterQuery.flush();
    csvWriterQuery.close();
  }

}
