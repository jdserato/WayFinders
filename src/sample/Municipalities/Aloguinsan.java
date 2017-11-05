package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Aloguinsan extends Municipality {
    private static Aloguinsan ourInstance = new Aloguinsan();

    public static Aloguinsan getInstance() {
        return ourInstance;
    }

    private Aloguinsan() {
        super("Aloguinsan", 80, 0, new Municipality[]{CarcarCity.getInstance()}, Pinamungajan.getInstance(), Barili.getInstance());
    }
}
