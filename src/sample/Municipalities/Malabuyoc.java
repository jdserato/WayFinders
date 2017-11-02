package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Malabuyoc extends Municipality {
    private static Malabuyoc ourInstance = new Malabuyoc();

    public static Malabuyoc getInstance() {
        return ourInstance;
    }

    private Malabuyoc() {
        super("Malabuyoc", 0, 0, null);
    }
}
