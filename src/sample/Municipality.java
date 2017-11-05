package sample;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class Municipality {
    private String name;
    private Municipality subroute;
    private int fareOrdinary;
    private int fareAircon;
    private Municipality[] encompassingMunicipality;
    private Municipality leftMun, rightMun;

    public Municipality(String name, int fareOrdinary, int fareAircon, Municipality[] encompassingMunicipality, Municipality leftMun, Municipality rightMun) {
        this.name = name;
        this.fareOrdinary = fareOrdinary;
        this.fareAircon = fareAircon;
        this.encompassingMunicipality = encompassingMunicipality;
        this.leftMun = leftMun;
        this.rightMun = rightMun;
    }

    public Municipality(String name, Municipality subroute, int fareOrdinary, int fareAircon, Municipality[] encompassingMunicipality, Municipality leftMun, Municipality rightMun) {
        this.name = name;
        this.subroute = subroute;
        this.fareOrdinary = fareOrdinary;
        this.fareAircon = fareAircon;
        this.encompassingMunicipality = encompassingMunicipality;
        this.leftMun = leftMun;
        this.rightMun = rightMun;
    }

    public Municipality getSubroute() {
        return subroute;
    }

    public void setSubroute(Municipality subroute) {
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

    public Municipality[] getEncompassingMunicipality() {
        return encompassingMunicipality;
    }

    public void setEncompassingMunicipality(Municipality[] encompassingMunicipality) {
        this.encompassingMunicipality = encompassingMunicipality;
        System.out.println(encompassingMunicipality[0] + " has been established.");
    }

    public Municipality getLeftMun() {
        return leftMun;
    }

    public void setLeftMun(Municipality leftMun) {
        this.leftMun = leftMun;
    }

    public Municipality getRightMun() {
        return rightMun;
    }

    public void setRightMun(Municipality rightMun) {
        this.rightMun = rightMun;
    }

    public String toString() {
        if (subroute != null) {
            return String.valueOf(name + " (via " + subroute + ")");
        } else {
            return name;
        }
    }
}
