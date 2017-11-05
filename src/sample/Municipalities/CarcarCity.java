package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class CarcarCity extends Municipality {
    private static CarcarCity ourInstance = new CarcarCity();

    public static CarcarCity getInstance() {
        return ourInstance;
    }

    private CarcarCity() {
        super("Carcar City", 60, 60, null, Sibonga.getInstance(), Zamboanga.getInstance());
    }
}
