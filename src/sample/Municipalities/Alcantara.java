package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Alcantara extends Municipality {
    private static Alcantara ourInstance = new Alcantara();

    public static Alcantara getInstance() {
        return ourInstance;
    }

    private Alcantara() {
        super("Alcantara", 0, 0, null);
    }
}
