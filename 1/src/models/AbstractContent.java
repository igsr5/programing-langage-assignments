package models;

abstract public class AbstractContent implements IContent{
  protected String title;
  protected String artist;
  public AbstractContent(String title, String artist){
    this.title = title;
    this.artist = artist;
  }

  abstract public String getContentType();

  public String getTitle(){
    return this.title;
  }
  public String getArtist(){
    return this.artist;
  }
}