package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Zamboanga extends Municipality {
    private static Zamboanga ourInstance = new Zamboanga();

    public static Zamboanga getInstance() {
        return ourInstance;
    }

    private Zamboanga() {
        super("Zamboanga", 0, 560, null, CarcarCity.getInstance(), Dumaguete.getInstance());
    }
}
