package players;

import java.util.*;
import models.*;

public class Mp3Player implements IPlayer{
  List<Music> musics;
  int selected = 0;
  boolean playing = false;

  public Mp3Player(List<Music> musics){
    this.musics = musics;
    System.out.println("Mp3Player Created");
  }

  private boolean isInRange(int n){
    if(n < 0 || n >= this.musics.size()) return false; //out of range
    return true;
  }

  public void play(){
    if(this.playing){
      System.out.println("Already Playing!!!");
    }else{
      System.out.println("Play Music - " + this.musics.get(this.selected).getTitle() + " by " + this.musics.get(this.selected).getArtist());
      this.playing = true;
    }
  }

  public void stop(){
    if(this.playing){
      System.out.println("Stop Music");
      this.playing = false;
    }else{
      System.out.println("No Music is Playing");
    }
  }

  public boolean select(int n){
    if(!isInRange(n)) return false;

    this.selected = n;
    return true;
  }

  public boolean next(){
    return select(this.selected + 1);
  }

  public boolean previous(){
    return select(this.selected - 1);
  }
}