package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class SamboanVO extends Municipality {
    private static SamboanVO ourInstance = new SamboanVO();

    public static SamboanVO getInstance() {
        return ourInstance;
    }

    private SamboanVO() {
        super("Samboan (Bato)", Oslob.getInstance(), 138, 145, new Municipality[]{Santander.getInstance(), Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()}, Santander.getInstance(), Ginatilan.getInstance());
    }
}
