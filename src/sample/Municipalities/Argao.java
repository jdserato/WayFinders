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
        super("Argao", 79, 83, new Municipality[]{Sibonga.getInstance(), CarcarCity.getInstance()}, Dalaguete.getInstance(), Sibonga.getInstance());
    }
}
