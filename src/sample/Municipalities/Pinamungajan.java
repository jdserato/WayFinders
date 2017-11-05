package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Pinamungajan extends Municipality {
    private static Pinamungajan ourInstance = new Pinamungajan();

    public static Pinamungajan getInstance() {
        return ourInstance;
    }

    private Pinamungajan() {
        super("Pinamungajan", 80, 0, new Municipality[]{ToledoCity.getInstance()}, Aloguinsan.getInstance(), ToledoCity.getInstance());
    }
}
