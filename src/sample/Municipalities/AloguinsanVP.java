package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class AloguinsanVP extends Municipality {
    private static AloguinsanVP ourInstance = new AloguinsanVP();

    public static AloguinsanVP getInstance() {
        return ourInstance;
    }

    private AloguinsanVP() {
        super("Aloguinsan", Pinamungajan.getInstance(),0, 0, new Municipality[]{ToledoCity.getInstance(), Pinamungajan.getInstance()}, Pinamungajan.getInstance(), Barili.getInstance());
    }
}
