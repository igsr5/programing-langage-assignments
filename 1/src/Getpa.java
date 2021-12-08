import java.util.ArrayList;
import java.util.List;
import models.*;
import players.*;

public class Getpa {
    public Getpa() {
    }

    public static List<Music> getMusicLibrary() {
        ArrayList var0 = new ArrayList();
        var0.add(new Music("Ao", "Mature Lady"));
        var0.add(new Music("Citrus", "Mature Lady"));
        var0.add(new Music("トゥモローランド", "リーマンZOO"));
        var0.add(new Music("解放区より", "リーマンZOO"));
        var0.add(new Music("Dear", "ラノマインド"));
        var0.add(new Music("GRAP", "SAUL"));
        return var0;
    }

    public static List<Movie> getMovieLibrary() {
        ArrayList var0 = new ArrayList();
        var0.add(new Movie("Ao [Lylic Video]", "Mature Lady"));
        var0.add(new Movie("解放区より [MV]", "リーマンZOO"));
        return var0;
    }

    public static List<IContent> getContentLibrary() {
        ArrayList var0 = new ArrayList();
        var0.add(new Music("Ao", "Mature Lady"));
        var0.add(new Music("Citrus", "Mature Lady"));
        var0.add(new Music("トゥモローランド", "リーマンZOO"));
        var0.add(new Music("解放区より", "リーマンZOO"));
        var0.add(new Music("Dear", "ラノマインド"));
        var0.add(new Music("GRAP", "SAUL"));
        var0.add(new Movie("Ao [Lylic Video]", "Mature Lady"));
        var0.add(new Movie("解放区より [MV]", "リーマンZOO"));
        var0.add(new Movie("光 [MV]", "ラノマインド"));
        var0.add(new Movie("GRAP [MV]", "SAUL"));
        return var0;
    }

    public static void doSomething(IPlayer var0) {
        int var1;
        for(var1 = 0; var1 < 5; ++var1) {
            var0.play();
            var0.stop();
            var0.next();
        }

        var0.play();
        var0.play();
        var0.stop();
        var0.stop();
        if (!var0.select(9)) {
            System.out.println("Could not select 9!");
        }

        for(var1 = 0; var1 < 5; ++var1) {
            var0.play();
            var0.stop();
            var0.previous();
        }

    }
}
