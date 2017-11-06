package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Ginatilan extends Municipality {
    private static Ginatilan ourInstance = new Ginatilan();

    public static Ginatilan getInstance() {
        return ourInstance;
    }

    private Ginatilan() {
        super("Ginatilan", 0, 0, new Municipality[]{Malabuyoc.getInstance(), Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()}, Samboan.getInstance(), Malabuyoc.getInstance());
    }
}