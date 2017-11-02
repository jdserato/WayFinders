package sample.Municipalities;

import sample.Municipality;

/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */
public class Asturias extends Municipality {
    private static Asturias ourInstance = new Asturias();

    public static Asturias getInstance() {
        return ourInstance;
    }

    private Asturias() {
        super("Asturias", 0, 0, new Municipality[]{CebuCity.getInstance()});
    }
}
