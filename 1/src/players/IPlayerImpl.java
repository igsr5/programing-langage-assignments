package players;

import models.IContent;
import java.util.List;

public class IPlayerImpl implements IPlayer {
    List<IContent> iContents;
    int selected = 0;
    boolean playing = false;

    public IPlayerImpl(List<IContent> iContents) {
        this.iContents = iContents;
        System.out.println("IPlayerImpl Created");
    }

    private boolean isInRange(int n){
        if(n < 0 || n >= this.iContents.size()) return false; //out of range
        return true;
    }

    @Override
    public void play(){
        if(this.playing){
            System.out.println("Already Playing!!!");
        }else{
            System.out.println("Play " + this.iContents.get(this.selected).getContentType() + " - " + this.iContents.get(this.selected).getTitle() + " by " + this.iContents.get(this.selected).getArtist());
            this.playing = true;
        }
    }

    @Override
    public void stop(){
        if(this.playing){
            System.out.println("Stop " + this.iContents.get(this.selected).getContentType());
            this.playing = false;
        }else{
            System.out.println("No IContent is Playing");
        }
    }

    @Override
    public boolean select(int n){
        if(!isInRange(n)) return false;

        this.selected = n;
        return true;
    }

    @Override
    public boolean next(){
        return select(this.selected + 1);
    }

    @Override
    public boolean previous(){
        return select(this.selected - 1);
    }
}