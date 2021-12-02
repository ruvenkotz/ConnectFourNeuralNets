package ConnectFourNeuralNets;


public class model{
/**
 * Applies the relu function to every element in the input
 * @param input
 * @return modified input array
 */
  public double[] relu(double[] input) {
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
  public double[] softMax(double[] input){
    int sum = 0;
    for(double d: input)
      sum += Math.exp(d);
    for(int i = 0; i < input.length; i++)
      input[i] = Math.exp(input[i])/sum;
    return input;
  }
  // Helper that matrix multiplies two lists
  private double listProduct(double[] l1, double[] l2){
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
  public double[] 
  levelForwardPass(double[] input, double[][] weights, double[] biases){
    double[] output = new double[input.length];
    for(int i = 0; i < weights.length; i++){
      double transformed = listProduct(input, weights[i]) + biases[i];
      output[i] = transformed;
    }
    return output;
  }






}