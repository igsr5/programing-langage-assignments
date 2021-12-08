package models;

public class Movie extends AbstractContent{
  public Movie(String title, String artist){
    super(title, artist);
  }

  public String getContentType(){
    return "Movie";
  }
}