package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Argao extends Municipality {
    private static Argao ourInstance = new Argao();

    public static Argao getInstance() {
        return ourInstance;
    }

    private Argao() {
        super("Argao", 0, 0, null);
    }
}
