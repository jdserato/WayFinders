package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Dalaguete extends Municipality {
    private static Dalaguete ourInstance = new Dalaguete();

    public static Dalaguete getInstance() {
        return ourInstance;
    }

    private Dalaguete() {
        super("Dalaguete", 100, 0, new Municipality[]{Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()}, Alcoy.getInstance(), Argao.getInstance());
    }
}
