package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class DumanjugVB extends Municipality {
    private static DumanjugVB ourInstance = new DumanjugVB();

    public static DumanjugVB getInstance() {
        return ourInstance;
    }

    private DumanjugVB() {
        super("Dumanjug", Barili.getInstance(),0, 0, new Municipality[]{Barili.getInstance(), CarcarCity.getInstance()}, Ronda.getInstance(), Dumanjug.getInstance());
    }
}
