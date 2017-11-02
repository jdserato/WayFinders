package sample.Municipalities;

import sample.Municipality;

/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */
public class CebuCity extends Municipality{
    private static CebuCity ourInstance = new CebuCity();

    public static CebuCity getInstance() {
        return ourInstance;
    }

    private CebuCity() {
        super("Cebu City", 0, 0, null);
    }
}
