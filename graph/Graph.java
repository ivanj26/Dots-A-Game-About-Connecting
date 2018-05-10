package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class Graph{
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  private List<Set<Node>> listAdjacency;
  private final String[] colors;
  private int nbNodes;

  public Graph(int initialNode, String[] colors) throws IllegalArgumentException{
    if (colors.length == initialNode * initialNode){
      listAdjacency = new ArrayList<Set<Node>>(initialNode * initialNode);
      nbNodes = initialNode * initialNode;
      this.colors = colors;
      for (int i = 0; i < nbNodes; i++){
        Set<Node> setNodes = new HashSet<>();
        listAdjacency.add(setNodes);
      }
      for (int i = 0; i < nbNodes; i++){
          addEdge(new Node(i), new Node(i+1));
          if (i+initialNode < nbNodes){
            addEdge(new Node(i), new Node(i+initialNode));
          }
      }
    } else {
      throw new IllegalArgumentException("Array String colors is not allowed!");
    }
  }

  public void addEdge(Node node1, Node node2){
    if (node1.getId() < nbNodes && node2.getId() < nbNodes){
      listAdjacency.get(node1.getId()).add(node2);
      listAdjacency.get(node2.getId()).add(node1);
    }
  }

  public Set<Node> getAdjacencyAt(int id){
    return listAdjacency.get(id);
  }

  public int getNbNode(){return nbNodes;}

  public boolean isCyclicRec(int i, boolean[] visited, int parent, Stack stack){
    // System.out.println("Nilai visited[] = ");
    // for (boolean visit : visited){
    //   System.out.println(visit);
    // }

    visited[i] = true;

    for (Iterator<Node> it = listAdjacency.get(i).iterator(); it.hasNext();){
      Node node = it.next();
      // System.out.println("V = " + i + ", U = " + node.getId());
      if (!visited[node.getId()] && colors[i].equals(node.getColor())){
        if (isCyclicRec(node.getId(), visited, i,stack)){
          stack.push(node.getId());
          return true;
        }
      }
      else if (parent != -1)
        if (node.getId() != parent && colors[parent].equals(colors[node.getId()])){
          stack.push(node.getId());
          return true;
        }
    }

    return false;
  }

  public boolean isCyclic(){
    Stack stack = new Stack();
    setColors(colors);
    boolean[] visited = new boolean[nbNodes];
    for (boolean visit : visited){
      visit = false;
    }

    for(int i = 0; i < nbNodes; i++){
      if (!visited[i]){
        if (isCyclicRec(i, visited, -1, stack)){
          System.out.println("Cycle at = " + stack);
          return true;
        }
      }
    }

    System.out.println();
    return false;
  }

  private void setColors(String[] colors){
    for (int i = 0; i < nbNodes; i++){
      for (Node node: listAdjacency.get(i)){
        node.setColor(colors[node.getId()]);
      }
    }
  }

  public void draw(){
    for (int i = 0; i < nbNodes; i++){
      switch(colors[i]){
        case "Red":
          System.out.print(ANSI_RED + colors[i].charAt(0) + ANSI_RESET);
          break;
        case "Purple":
          System.out.print(ANSI_PURPLE + colors[i].charAt(0) + ANSI_RESET);
          break;
        case "Yellow":
          System.out.print(ANSI_YELLOW + colors[i].charAt(0) + ANSI_RESET);
          break;
        case "Green":
          System.out.print(ANSI_GREEN + colors[i].charAt(0) + ANSI_RESET);
          break;
        case "Blue":
          System.out.print(ANSI_BLUE + colors[i].charAt(0) + ANSI_RESET);
          break;
        case "Cyan":
          System.out.print(ANSI_CYAN + colors[i].charAt(0) + ANSI_RESET);
          break;
        default:
          System.out.print(ANSI_BLACK + colors[i].charAt(0) + ANSI_RESET);
      }
      if ((i + 1) % Math.sqrt(nbNodes) != 0){
        System.out.print("---");
      } else if (i + Math.sqrt(nbNodes) < nbNodes){
        System.out.println();
        for (int j = 0; j < Math.sqrt(nbNodes); j++)
          System.out.print("|   ");
        System.out.println();
      }
    }
    System.out.println();
  }
}
