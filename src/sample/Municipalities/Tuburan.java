package sample.Municipalities;

import sample.Municipality;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class Tuburan extends Municipality {
    private static Tuburan ourInstance = new Tuburan();

    public static Tuburan getInstance() {
        return ourInstance;
    }

    private Tuburan() {
        super("Tuburan", 108, 0, new Municipality[]{Asturias.getInstance(), Balamban.getInstance()}, Asturias.getInstance(), Bacolod.getInstance());
    }
}
