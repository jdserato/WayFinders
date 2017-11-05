package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class ToledoCity extends Municipality {
    private static ToledoCity ourInstance = new ToledoCity();

    public static ToledoCity getInstance() {
        return ourInstance;
    }

    private ToledoCity() {
        super("Toledo City", 60, 0, null, Pinamungajan.getInstance(), Balamban.getInstance());
    }
}
