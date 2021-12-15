package ConnectFourNeuralNets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.lang.model.util.ElementScanner14;

public class Model{
/**
 * Applies the relu function to every element in the input
 * @param input
 * @return modified input array
 */

  ArrayList<double[][]> weights = new ArrayList<>();
  ArrayList<double[]> biases = new ArrayList<>();
  ArrayList<Integer> layerSizes = new ArrayList<>();
  int activation = 0;

  public Model(String path) {
    File file = new File(path);

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      int lineNumber = 1;
      int layerNumber = 1;
      
      String st;
      boolean start = true;
      while ((st = br.readLine()) != null){
        // System.out.println(st);
          //Get sizes
          if(lineNumber == 1 && start){
            String[] line = st.split(" ");
            for(int i = 0; i < line.length-1; i++){
              layerSizes.add(Integer.parseInt(line[i]));
              int line1 = Integer.parseInt(line[i]);
              int line2 = Integer.parseInt(line[i+1]);
              double[][] w = new double[line2][line1];
              double[] bias = new double[line2];
              weights.add(w);
              biases.add(bias);
            }
            layerSizes.add(Integer.parseInt(line[line.length-1]));
          }
          //Get activation function
          else if(lineNumber == 2 && start){
            // 0 for relu, 1 for sigmoid
            activation = Integer.parseInt(st);
            start = false;
            lineNumber = -1;
          }
          //Get biases and weights
          else {
            int s = layerSizes.get(layerNumber);
            String[] line = st.split(" ");
            if(lineNumber == s){
                for(int i = 0; i < line.length; i++){
                  biases.get(layerNumber-1)[i] =  Double.parseDouble(line[i]);
              }
              layerNumber++;
              lineNumber = -1;
            }
            else{
              for(int i = 0; i < line.length; i++){
                weights.get(layerNumber-1)[lineNumber][i] = Double.parseDouble(line[i]);
              }
            }
          
            }
          lineNumber++;
          }
          
      // System.out.println(layerSizes);
      // System.out.println(biases.get(1).length);
      // System.out.println(biases.size());
      for(int i = 0; i < weights.size(); i++){
        // data.print2DDouble(weights.get(i));
        // System.out.println(Arrays.toString(biases.get(i)));
      }
    } catch (NumberFormatException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  //relu activation function
  private static double[] relu(double[] input) {
      for(int i = 0; i < input.length; i++){
        input[i] = Math.max(input[i], 0);
      }
      return input;
  }
  //sigmoid activation function
  private static double[] sigmoid(double[] input){
    for(int i = 0; i < input.length; i++){
      double x = Math.exp(-input[i]);
      input[i] = 1 / (1 + x) ;
    }
    return input;
  }
  /**
   * Normalizes every value in input using softMax formula
   * @param input
   * @return normalized input using softMax regression
   */
  private static double[] softMax(double[] input){
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
  private static double[] 
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
  public double[] forward(double[] input){
    for(int i = 0; i < layerSizes.size()-1; i++){
      if(activation == 0 && i != layerSizes.size()-2 || layerSizes.size() == 2)
        input = relu(levelForwardPass(input, weights.get(i), biases.get(i)));
      else if (activation == 1 && i != layerSizes.size()-2)
        input = sigmoid(levelForwardPass(input, weights.get(i), biases.get(i)));
      else
        input = levelForwardPass(input, weights.get(i), biases.get(i));
    }
    return softMax(input);
  }







}