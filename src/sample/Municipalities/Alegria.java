package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Alegria extends Municipality {
    private static Alegria ourInstance = new Alegria();

    public static Alegria getInstance() {
        return ourInstance;
    }

    private Alegria() {
        super("Alegria", 0, 0, new Municipality[]{Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()}, Badian.getInstance(), Malabuyoc.getInstance());
    }
}
