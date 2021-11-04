package ConnectFourNeuralNets;

import java.io.IOException;

public class Main {
   public static void main(String[] args) {
    //  data.getWins();
    // data.getOneGame();
    try {
      data.writeData(100000);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  
  }

  
  

}