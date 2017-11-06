package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class    Barili extends Municipality {
    private static Barili ourInstance = new Barili();

    public static Barili getInstance() {
        return ourInstance;
    }

    private Barili() {
        super("Barili", 0, 0, new Municipality[]{CarcarCity.getInstance()}, Aloguinsan.getInstance(), Dumanjug.getInstance());
    }
}
