package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class PinamungajanVA extends Municipality {
    private static PinamungajanVA ourInstance = new PinamungajanVA();

    public static PinamungajanVA getInstance() {
        return ourInstance;
    }

    private PinamungajanVA() {
        super("Pinamungajan", Aloguinsan.getInstance(), 0, 0, new Municipality[]{Aloguinsan.getInstance(), CarcarCity.getInstance()}, Aloguinsan.getInstance(), ToledoCity.getInstance());
    }
}
