package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Dumanjug extends Municipality {
    private static Dumanjug ourInstance = new Dumanjug();

    public static Dumanjug getInstance() {
        return ourInstance;
    }

    private Dumanjug() {
        super("Dumanjug", 75, 0, new Municipality[]{Sibonga.getInstance(), CarcarCity.getInstance()}, Ronda.getInstance(), Dumanjug.getInstance());
    }
}
