package sample;

/**
 * Created by Serato, Jay Vince on October 12, 2017.
 */
public class Bus {
    private String busCompany, type, destination, departure, lastTrip;
    private int trips, buses;

    public Bus(String busCompany, String type, String destination, String departure, String lastTrip, int trips, int buses) {
        this.busCompany = busCompany;
        this.type = type;
        this.destination = destination;
        this.departure = departure;
        this.lastTrip = lastTrip;
        this.trips = trips;
        this.buses = buses;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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
