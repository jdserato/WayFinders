package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Badian extends Municipality {
    private static Badian ourInstance = new Badian();

    public static Badian getInstance() {
        return ourInstance;
    }

    private Badian() {
        super("Badian", 0, 0, new Municipality[]{Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()}, Moalboal.getInstance(), Alegria.getInstance());
    }
}
