import players.*;

public class Main{
  public static void main(String[] args){
    var player = new IPlayerImpl(Getpa.getContentLibrary());
    Getpa.doSomething(player);
  }
}
