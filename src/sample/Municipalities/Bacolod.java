package sample.Municipalities;

import sample.Municipality;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class Bacolod extends Municipality{
    private static Bacolod ourInstance = new Bacolod();

    public static Bacolod getInstance() {
        return ourInstance;
    }

    private Bacolod() {
        super("Bacolod", 0, 0, null);
    }
}
