package sample.Municipalities;

import sample.Municipality;

/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */
public class Balamban extends Municipality {
    private static Balamban ourInstance = new Balamban();

    public static Balamban getInstance() {
        return ourInstance;
    }

    private Balamban() {
        super("Balamban", 80, 85, null, Asturias.getInstance(), ToledoCity.getInstance());
    }
}
