package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Dumaguete extends Municipality {
    private static Dumaguete ourInstance = new Dumaguete();

    public static Dumaguete getInstance() {
        return ourInstance;
    }

    private Dumaguete() {
        super("Dumaguete", 0, 275, null, Zamboanga.getInstance(), Bacolod.getInstance());
    }
}
