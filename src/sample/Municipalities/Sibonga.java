package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Sibonga extends Municipality {
    private static Sibonga ourInstance = new Sibonga();

    public static Sibonga getInstance() {
        return ourInstance;
    }

    private Sibonga() {
        super("Sibonga", 70, 0, new Municipality[]{CarcarCity.getInstance()}, Argao.getInstance(), CarcarCity.getInstance());
    }
}
