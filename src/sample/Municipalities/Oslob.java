package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Oslob extends Municipality {
    private static Oslob ourInstance = new Oslob();

    public static Oslob getInstance() {
        return ourInstance;
    }

    private Oslob() {
        super("Oslob", 0, 0, null);
    }
}
