import java.util.*;
import models.*;
import players.*;

public class Main{
  public static void main(String[] args){
    var player = new Mp3Player(Getpa.getMusicLibrary());
    Getpa.doSomething(player);
  }
}
