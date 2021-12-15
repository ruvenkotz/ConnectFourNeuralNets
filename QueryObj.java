package ConnectFourNeuralNets;

import java.util.LinkedList;
import java.util.List;

public class QueryObj{
  List<int[][]> history = new LinkedList<>();
  List<String> queryList = new LinkedList<>(); 
  public List<int[][]> getHistory(){
    return history;
  }
  public List<String> getQueryList(){
    return queryList;
  }
  public void setHistory(List<int[][]> h){
    history = h;
  }
  public void setQueryList( List<String> q){
    queryList = q;
  }




}