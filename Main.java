import java.util.Scanner;
import graph.*;

public class Main{
  public static void main(String[] args) {
      String[] colors = {"Green", "Green", "Red", //"Green",
                        "Green", "Green", "Green", //"Green",
                        "Red", "Purple", "Green"//, "Green",
                        // "Green", "Green", "Green", "Green",
                        };
      Graph graph = new Graph(3, colors);
      graph.draw();

      System.out.print("Apakah anda ingin mencari siklus yang mungkin dipapan permainan? = ");
      Scanner scan = new Scanner(System.in);
      String input = scan.next();

      if (input.equalsIgnoreCase("ya")){
        System.out.println();
          if (graph.isCyclic())
            System.out.println("Papan permainan punya siklus dengan dot berwarna sama");
          else
            System.out.println("Papan permainan tidak punya siklus dengan dot berwarna sama");
      }
  }
}
