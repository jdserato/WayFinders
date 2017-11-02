package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Moalboal extends Municipality {
    private static Moalboal ourInstance = new Moalboal();

    public static Moalboal getInstance() {
        return ourInstance;
    }

    private Moalboal() {
        super("Moalboal", 0, 0, null);
    }
}
