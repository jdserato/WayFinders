package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Ronda extends Municipality {
    private static Ronda ourInstance = new Ronda();

    public static Ronda getInstance() {
        return ourInstance;
    }

    private Ronda() {
        super("Ronda", 0, 0, null);
    }
}
