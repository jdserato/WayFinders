package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class SamboanVB extends Municipality {
    private static SamboanVB ourInstance = new SamboanVB();

    public static SamboanVB getInstance() {
        return ourInstance;
    }

    private SamboanVB() {
        super("Samboan (Bato)", Barili.getInstance(), 72, 85, new Municipality[]{Ginatilan.getInstance(), Malabuyoc.getInstance(), Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()}, Santander.getInstance(), Ginatilan.getInstance());
    }
}
