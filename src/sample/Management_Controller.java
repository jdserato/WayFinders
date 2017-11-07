package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import sample.Municipalities.*;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Serato, Jay Vince on November 07, 2017.
 */
public class Management_Controller implements Initializable{
    public ComboBox<String> cbBus, cbDestination, cbType;

    private ObservableList<Bus> buses = FXCollections.observableArrayList();
    private ObservableList<Municipality> municipalities = FXCollections.observableArrayList(AloguinsanVP.getInstance(), DumanjugVB.getInstance(), PinamungajanVA.getInstance(), SamboanVO.getInstance(), SamboanVB.getInstance(), Bacolod.getInstance(), Zamboanga.getInstance(), Dumaguete.getInstance(), Tuburan.getInstance(), Balamban.getInstance(), Asturias.getInstance(), Balamban.getInstance(), ToledoCity.getInstance(), Pinamungajan.getInstance(), Aloguinsan.getInstance(), Barili.getInstance(), Dumanjug.getInstance(), Ronda.getInstance(), Alcantara.getInstance(), Moalboal.getInstance(), Badian.getInstance(), Alegria.getInstance(), Malabuyoc.getInstance(), Ginatilan.getInstance(), Samboan.getInstance(), Santander.getInstance(), Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT *" +
                "FROM bus_info";

        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Municipality thisMunicipality = null;
                for (Municipality m : municipalities) {
                    if (rs.getString("destination").equalsIgnoreCase(m.toString())) {
                        thisMunicipality = m;
                        break;
                    }
                }
                String times = rs.getString("times");
                Date[] timeLine = new Date[50];
                int j = 0;
                while (true){
                    int hr = 0, min = 0;
                    int offset = 10;
                    boolean colonDone = false;
                    try {
                        while (times.charAt(0) != ',') {
                            if (times.charAt(0) != ' ' && times.charAt(0) != ':') {
                                if (!colonDone) {
                                    hr = hr + (Integer.parseInt(times.charAt(0) + "") * offset);
                                    offset = 1;
                                } else {
                                    min = min + (Integer.parseInt(times.charAt(0) + "") * offset);
                                    offset = 1;
                                }
                            } else if (times.charAt(0) == ':') {
                                colonDone = true;
                                offset = 10;
                            }
                            times = times.substring(1);
                        }
                        times = times.substring(1);
                        timeLine[j++] = new Date(0, 0, 0, hr, min);
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                }
                buses.add(new Bus(rs.getInt("bay_num"), rs.getString("company"), rs.getString("type"), thisMunicipality, rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), rs.getString("wing_area"), timeLine));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ObservableList<String> busCompanies = FXCollections.observableArrayList();
        for (Bus bus : buses) {
            if (!busCompanies.contains(bus.getBusCompany())) {
                busCompanies.add(bus.getBusCompany());
            }
        }

        cbBus.setItems(busCompanies);
    }

    private Connection connect() {
        String url = "jdbc:sqlite:src/sample/bus.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
