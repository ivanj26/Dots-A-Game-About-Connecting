package graph;

class Node{
  private int id;
  private String color;

  public Node(int id){
    this.id = id;
    color = null;
  }

  public Node(int id, String color){
    this.id = id;
    this.color = color;
  }

  public int getId(){
    return id;
  }

  public String getColor(){
    return color;
  }

  public void setId(int id){this.id = id;}
  public void setColor(String color){this.color = color;}
}
