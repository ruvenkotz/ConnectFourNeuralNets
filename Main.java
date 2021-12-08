package ConnectFourNeuralNets;

import java.io.IOException;
import java.util.Arrays;
public class Main {
   public static void main(String[] args) {
     
    // data.getOneGame();
    // data.getWins(100000);
    double[] input = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    System.out.println(Arrays.toString(model.forward(input)));
    try {
    // data.writeData(1000000);
    model.readInput();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  
  }

  
  

}