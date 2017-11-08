package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    public Button btnSearch, btnSave, btnAdd;
    public ImageView ivAddBus;
    public TextField tfCompany, tfType, tfDestination, tfFirstDep, tfLastDep, tfTrips, tfBuses, tfFare, tfWingArea, tfBayNumber;
    public VBox vbMainPane, vbEditBuses;
    public HBox hbDetail, hbEditBus;

    private ObservableList<Bus> buses = FXCollections.observableArrayList();
    private ObservableList<Municipality> municipalities = FXCollections.observableArrayList(AloguinsanVP.getInstance(), DumanjugVB.getInstance(), PinamungajanVA.getInstance(), SamboanVO.getInstance(), SamboanVB.getInstance(), Bacolod.getInstance(), Zamboanga.getInstance(), Dumaguete.getInstance(), Tuburan.getInstance(), Balamban.getInstance(), Asturias.getInstance(), Balamban.getInstance(), ToledoCity.getInstance(), Pinamungajan.getInstance(), Aloguinsan.getInstance(), Barili.getInstance(), Dumanjug.getInstance(), Ronda.getInstance(), Alcantara.getInstance(), Moalboal.getInstance(), Badian.getInstance(), Alegria.getInstance(), Malabuyoc.getInstance(), Ginatilan.getInstance(), Samboan.getInstance(), Santander.getInstance(), Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance());
    private Bus busQueriest;

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
                    } catch (StringIndexOutOfBoundsException | NullPointerException e) {
                        break;
                    }
                }
                buses.add(new Bus(rs.getInt("bay_num"), rs.getString("company"), rs.getString("type"), thisMunicipality, rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), rs.getString("wing_area"), timeLine, rs.getInt("bus_id")));
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
        ObservableList<Bus> busQuery = FXCollections.observableArrayList();
        cbBus.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                busQuery.clear();
                if (newValue == null) {
                    newValue = oldValue;
                }
                cbDestination.setDisable(false);
                cbType.setDisable(true);
                btnSearch.setDisable(true);

                ObservableList<String> dest = FXCollections.observableArrayList();
                for (Bus bus : buses) {
                    if (bus.getBusCompany().equalsIgnoreCase(newValue) && !dest.contains(bus.getDestination().toString())) {
                        dest.add(bus.getDestination().toString());
                        busQuery.add(bus);
                    }
                }
                cbDestination.setItems(dest);
            }
        });
        ObservableList<Bus> busQuerier = FXCollections.observableArrayList();
        cbDestination.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                busQuerier.clear();
                if (newValue == null) {
                    newValue = oldValue;
                }
                cbType.setDisable(false);
                btnSearch.setDisable(true);
                ObservableList<String> ty = FXCollections.observableArrayList();
                for (Bus bus : busQuery) {
                    if (bus.getDestination().toString().equalsIgnoreCase(newValue) && !ty.contains(bus.getType())) {
                        ty.add(bus.getType());
                        busQuerier.add(bus);
                    }
                }
                cbType.setItems(ty);
            }
        });
        cbType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    newValue = oldValue;
                }
                for (Bus bus : busQuerier) {
                    if(bus.getType().equalsIgnoreCase(newValue)) {
                        busQueriest = bus;
                    }
                }
                btnSearch.setDisable(false);
            }
        });
    }

    @FXML
    private void handleAddBusClicked() {
        vbMainPane.setVisible(false);
        vbEditBuses.setVisible(true);
        hbEditBus.setVisible(false);
        hbDetail.setVisible(true);
        btnAdd.setVisible(true);
        tfCompany.setEditable(true);
    }

    @FXML
    private void handleAddClicked() {
        String query = "INSERT INTO bus_info(destination, type, first_departure, last_trip, no_of_trips, no_of_buses, fare, wing_area, bay_num, company) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tfDestination.getText());
            pstmt.setString(2, tfType.getText());
            pstmt.setString(3, tfFirstDep.getText());
            pstmt.setString(4, tfLastDep.getText());
            pstmt.setInt(5, Integer.parseInt(tfTrips.getText()));
            pstmt.setInt(6, Integer.parseInt(tfBuses.getText()));
            pstmt.setInt(7, Integer.parseInt(tfFare.getText()));
            pstmt.setString(8, tfWingArea.getText());
            pstmt.setInt(9, Integer.parseInt(tfBayNumber.getText()));
            pstmt.setString(10, tfCompany.getText());

            if (pstmt.executeUpdate() == 1) {
                Municipality sel = null;
                for (Municipality m : municipalities) {
                    if (tfDestination.getText().equalsIgnoreCase(m.toString())) {
                        sel = m;
                        break;
                    }
                }
                buses.add(new Bus(Integer.parseInt(tfBayNumber.getText()),tfCompany.getText(), tfType.getText(), sel, tfFirstDep.getText(), tfLastDep.getText(), Integer.parseInt(tfTrips.getText()), Integer.parseInt(tfBuses.getText()), Integer.parseInt(tfFare.getText()), tfWingArea.getText() , new Date[5], 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditClicked() {
        tfCompany.setText(cbBus.getValue());
        tfDestination.setText(cbDestination.getValue());
        tfType.setText(cbType.getValue());
        tfFirstDep.setText(busQueriest.getDeparture());
        tfLastDep.setText(busQueriest.getLastTrip());
        tfTrips.setText(busQueriest.getTrips() + "");
        tfBuses.setText(busQueriest.getBuses() + "");
        tfFare.setText(busQueriest.getFares() + "");
        tfWingArea.setText(busQueriest.getWingArea());
        tfBayNumber.setText(busQueriest.getBayNumber() + "");
        btnSave.setVisible(true);
        hbDetail.setVisible(true);
    }

    @FXML
    private void handleSaveClicked() {
        String query = "UPDATE bus_info " +
                "SET destination = ? , " +
                "type = ? ," +
                " first_departure = ? ," +
                " last_trip = ? ," +
                " no_of_trips = ? ," +
                " no_of_buses = ? ," +
                " fare = ? ," +
                " wing_area = ? ," +
                " bay_num = ? " +
                "WHERE bus_id = ?";
        try (Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tfDestination.getText());
            pstmt.setString(2, tfType.getText());
            pstmt.setString(3, tfFirstDep.getText());
            pstmt.setString(4, tfLastDep.getText());
            pstmt.setInt(5, Integer.parseInt(tfTrips.getText()));
            pstmt.setInt(6, Integer.parseInt(tfBuses.getText()));
            pstmt.setInt(7, Integer.parseInt(tfFare.getText()));
            pstmt.setString(8, tfWingArea.getText());
            pstmt.setInt(9, Integer.parseInt(tfBayNumber.getText()));
            pstmt.setInt(10, busQueriest.getId());

            System.out.println(pstmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
