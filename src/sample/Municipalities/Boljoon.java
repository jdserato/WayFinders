package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Boljoon extends Municipality {
    private static Boljoon ourInstance = new Boljoon();

    public static Boljoon getInstance() {
        return ourInstance;
    }

    private Boljoon() {
        super("Boljoon", 0, 0, null);
    }
}
