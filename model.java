package ConnectFourNeuralNets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class model{
/**
 * Applies the relu function to every element in the input
 * @param input
 * @return modified input array
 */
  static File file = new File("center_model.txt");
  static double[][] inputWeights = new double[22][42];
  static double[] inputBiases = new double[22];
  static double[][] hiddenLayerWeights = new double[3][22];
  static double[] hiddenLayerBiases = new double[3];


  public static double[] relu(double[] input) {
      for(int i = 0; i < input.length; i++){
        input[i] = Math.max(input[i], 0);
      }
      return input;
  }
  /**
   * Normalizes every value in input using softMax formula
   * @param input
   * @return normalized input using softMax regression
   */
  public static double[] softMax(double[] input){
    double sum = 0;
    for(double d: input)
      sum += Math.exp(d);
    for(int i = 0; i < input.length; i++)
      input[i] = Math.exp(input[i])/sum;
    return input;
  }
  // Helper that matrix multiplies two lists
  private static double listProduct(double[] l1, double[] l2){
    double sum = 0;
    for(int i = 0; i < l1.length; i++){
      sum += l1[i] * l2[i];
    }
    return sum;
  }
  /**
   * Pass the input through the level by multitplying it by the weights 
   * and the bias
   * @param input
   * @param weights
   * @param biases
   * @return Outputs at the new layer
   */
  public static double[] 
  levelForwardPass(double[] input, double[][] weights, double[] biases){
    LinkedList<Double> nodes = new LinkedList<>();
    for(int i = 0; i < weights.length; i++){
      double transformed = listProduct(input, weights[i]) + biases[i];
      nodes.add(transformed);
    }
    double[] output = new double[nodes.size()];
    for(int i = 0; i < nodes.size(); i++){
      output[i] = nodes.get(i);
    }
    return output;
  }
  public static double[] forward(double[] inputBoard){
    try {
      readInput();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    double[] hiddenLayerInput = levelForwardPass(inputBoard, inputWeights, inputBiases);
    double[] hiddenLayerInputNew = relu(hiddenLayerInput);
    double[] output = levelForwardPass(hiddenLayerInputNew, hiddenLayerWeights, hiddenLayerBiases);
    double[] outputNew = softMax(output);
    return outputNew;
  }

  public static void readInput() throws IOException{
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      int lineNumber = 0;
      String st;
      while ((st = br.readLine()) != null){
          if(lineNumber <= 21){
            String[] line = st.split(" ");
            for(int i = 0; i < line.length; i++){
              inputWeights[lineNumber][i] = Double.parseDouble(line[i]);
            }
          }
          else if(lineNumber <= 22){
            String[] line = st.split(" ");
            for(int i = 0; i < line.length; i++){
              inputBiases[i] = Double.parseDouble(line[i]);
            }
          }
          else if(lineNumber <= 25){
            String[] line = st.split(" ");
            for(int i = 0; i < line.length; i++){
              hiddenLayerWeights[lineNumber-23][i] = Double.parseDouble(line[i]);
            }
          }
          else{
            String[] line = st.split(" ");
            for(int i = 0; i < line.length; i++){
              hiddenLayerBiases[i] = Double.parseDouble(line[i]);
            }
          }
          lineNumber++;
      }

      // System.out.println((inputWeights[13].length));
      // data.print2DDouble(inputWeights);
    }


  }






}