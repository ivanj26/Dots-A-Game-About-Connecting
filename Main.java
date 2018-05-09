import graph.*;

public class Main{
  public static void main(String[] args) {
      String[] colors = {"Yellow", "Purple", "Green", "Red",
                        "Purple", "Yellow", "Green", "Purple",
                        "Red", "Blue", "Red", "Red",
                        "Green", "Yellow", "Red", "Red"};
      Graph graph = new Graph(16, colors);
      graph.draw();
      System.out.println(graph.isCyclic());
  }
}
