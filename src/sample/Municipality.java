package sample;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public abstract class Municipality {
    private String name;
    private String subroute;
    private int fareOrdinary;
    private int fareAircon;
    private static Municipality[] encompassingMunicipality;

    public Municipality(String name, int fareOrdinary, int fareAircon, Municipality[] encompassingMunicipality) {
        this.name = name;
        this.fareOrdinary = fareOrdinary;
        this.fareAircon = fareAircon;
        this.encompassingMunicipality = encompassingMunicipality;
    }

    public Municipality(String name, String subroute, int fareOrdinary, int fareAircon, Municipality[] encompassingMunicipality) {
        this.name = name;
        this.subroute = subroute;
        this.fareOrdinary = fareOrdinary;
        this.fareAircon = fareAircon;
        this.encompassingMunicipality = encompassingMunicipality;
    }

    public String getSubroute() {
        return subroute;
    }

    public void setSubroute(String subroute) {
        this.subroute = subroute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFareOrdinary() {
        return fareOrdinary;
    }

    public void setFareOrdinary(int fareOrdinary) {
        this.fareOrdinary = fareOrdinary;
    }

    public int getFareAircon() {
        return fareAircon;
    }

    public void setFareAircon(int fareAircon) {
        this.fareAircon = fareAircon;
    }

    public static Municipality[] getEncompassingMunicipality() {
        return encompassingMunicipality;
    }

    public void setEncompassingMunicipality(Municipality[] encompassingMunicipality) {
        this.encompassingMunicipality = encompassingMunicipality;
    }

    public String toString() {
        if (subroute != null) {
            return String.valueOf(name + " (via " + subroute + ")");
        } else {
            return name;
        }
    }
}
