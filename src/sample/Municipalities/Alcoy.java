package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Alcoy extends Municipality {
    private static Alcoy ourInstance = new Alcoy();

    public static Alcoy getInstance() {
        return ourInstance;
    }

    private Alcoy() {
        super("Alcoy", 100, 0, new Municipality[]{Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()}, Boljoon.getInstance(), Dalaguete.getInstance());
    }
}
