package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Santander extends Municipality {
    private static Santander ourInstance = new Santander();

    public static Santander getInstance() {
        return ourInstance;
    }

    private Santander() {
        super("Santander", 0, 0, new Municipality[]{Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()}, Samboan.getInstance(), Oslob.getInstance());
    }
}
