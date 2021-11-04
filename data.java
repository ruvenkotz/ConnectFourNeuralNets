package ConnectFourNeuralNets;

import java.util.List;

public class data {
  public static void getWins(){
    int player1Wins = 0;
    int player2Wins = 0;
    int draws = 0;
    for(int i = 0; i < 100000; i++){
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
}
