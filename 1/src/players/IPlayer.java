package players;

public interface IPlayer {
  public void play();
  public void stop();

  public boolean select(int n);
  public boolean next();
  public boolean previous();
}