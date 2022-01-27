package ConnectFourNeuralNets;

import java.io.IOException;
import java.util.Arrays;
public class Main {
   public static void main(String[] args) throws IOException {
    //  System.out.println(Query.playGame());
    // data.getOneGame();
    // data.getWins(10000);
    // Position.FixData();
    // gameTest.playGame();

    Board b = new Board();
    Board b1 = new Board(b.getBoard(),b.getMoves(), b.getNextMove());
    b1.placeATile(4, 1);
    data.print2D(b.getBoard());
    data.print2D(b1.getBoard());
    // int[][] board =  {{0,0,0,0,0,0,0},
    //                   {0,0,0,0,0,0,0},
    //                   {0,0,0,0,0,0,0},
    //                   {0,0,0,0,0,0,0},
    //                   {0,0,2,1,2,2,0},
    //                   {0,0,2,1,1,1,0}};
    // double[] input  = Position.getPosition(board, 2, 4, 2);

    // Model model = new Model("positions.txt");
    // System.out.println(Arrays.toString(model.forward(input)));

    // try {
    //   // Query.getResults(5000);
    //   data.writeData(2000);
    // } catch (IOException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
  
  }

  
  

}