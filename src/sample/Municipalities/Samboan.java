package sample.Municipalities;/**
 * Created by Serato, Jay Vince on November 02, 2017.
 */

import sample.Municipality;

public class Samboan extends Municipality {
    private static Samboan ourInstance = new Samboan();

    public static Samboan getInstance() {
        return ourInstance;
    }

    private Samboan() {
        super("Samboan", 138, 145, SamboanVB.getInstance().getEncompassingMunicipality(), Santander.getInstance(), Ginatilan.getInstance());
    }
}
