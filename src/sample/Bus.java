package sample;

/**
 * Created by Serato, Jay Vince on October 12, 2017.
 */
public class Bus {
    private String busCompany;
    private String type;
    private Municipality destination;
    private String departure;
    private String lastTrip;

    private String wingArea;
    private int trips, buses, bayNumber, fares;

    public Bus(int bayNumber, String busCompany, String type, Municipality destination, String departure, String lastTrip, int trips, int buses, int fares, String wingArea) {
        this.busCompany = busCompany;
        this.bayNumber = bayNumber;
        this.fares = fares;
        this.wingArea = wingArea;
        this.type = type;
        this.destination = destination;
        this.departure = departure;
        this.lastTrip = lastTrip;
        this.trips = trips;
        this.buses = buses;
    }

    public String getWingArea() {
        return wingArea;
    }

    public void setWingArea(String wingArea) {
        this.wingArea = wingArea;
    }

    public int getBayNumber() {
        return bayNumber;
    }

    public void setBayNumber(int bayNumber) {
        this.bayNumber = bayNumber;
    }

    public int getFares() {
        return fares;
    }

    public void setFares(int fares) {
        this.fares = fares;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Municipality getDestination() {
        return destination;
    }

    public void setDestination(Municipality destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getLastTrip() {
        return lastTrip;
    }

    public void setLastTrip(String lastTrip) {
        this.lastTrip = lastTrip;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public int getBuses() {
        return buses;
    }

    public void setBuses(int buses) {
        this.buses = buses;
    }
}
