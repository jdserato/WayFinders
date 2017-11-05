package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Moalboal extends Municipality {
    private static Moalboal ourInstance = new Moalboal();

    public static Moalboal getInstance() {
        return ourInstance;
    }

    private Moalboal() {
        super("Moalboal", 107, 120, new Municipality[]{Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()}, Badian.getInstance(), Alcantara.getInstance());
    }
}
