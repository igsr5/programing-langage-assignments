package models;

public class Music extends AbstractContent{
  public Music(String title, String artist){
    super(title, artist);
  }

  public String getContentType(){
    return "Music";
  }
}