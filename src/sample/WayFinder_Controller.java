package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class WayFinder_Controller implements Initializable {
    public Pane pMap, pTapToStart;
    public VBox vbMain, vbDetails;
    public HBox hbSubMap, hbTap;
    public TableView<Bus> tvBusDetails;
    public TableColumn<Bus, String> tcBusCompany, tcBusType, tccLocation, tcWingArea, tcBayNumber, tcDestination, tcLastTrip, tcFirstTrip, tcTime, tcMaxFare;
    public TextField taTravelDistance, taTravelTime, taFareAircon, taFareOrdinary;
    public ImageView ivTuburan, ivAsturias, ivBalamban, ivToledoCity, ivPinamungajan, ivAloguinsan, ivBarili, ivDumanjug, ivRonda, ivAlcantara, ivMoalboal, ivBadian, ivAlegria, ivMalabuyoc, ivGinatilan, ivSamboan, ivOslob, ivBoljoon, ivAlcoy, ivDalaguete, ivArgao, ivSibonga, ivCarcarCity, ivZamboanga, ivBacolod, ivSantander, ivDumaguete;
    public Label lTuburan, lAsturias, lBalamban, lToledoCity, lPinamungajan, lAloguinsan, lBarili, lDumanjug, lRonda, lAlcantara, lMoalboal, lBadian, lAlegria, lMalabuyoc, lGinatilan, lSamboan, lOslob, lBoljoon, lAlcoy, lDalaguete, lArgao, lSibonga, lCarcarCity, lZamboanga, lBacolod, lSantander, lDumaguete;
    public ImageView ivBackToMap, ivPrev, ivNext, ivTerminalPath;
    public Label lBackToMap, lDestination, lPrev, lNext;
    public ListView<TextArea> lvAnnouncements;
    public ListView<MenuButton> lvFAQ;

    private ObservableList<Bus> qualifier = FXCollections.observableArrayList();
    private ObservableList<Bus> buses = FXCollections.observableArrayList();
    private ObservableList<Municipality> municipalities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Image background = new Image("sample/res/Background.png", screen.getWidth(), screen.getHeight(), false, true);
        vbMain.setBackground(new Background(new BackgroundImage(background, NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT)));
        vbMain.setPrefHeight(screen.getHeight());
        vbMain.setPrefWidth(screen.getWidth());
        hbSubMap.setPrefHeight(screen.getHeight());
        hbSubMap.setPrefWidth(screen.getWidth());
        hbTap.setPrefHeight(screen.getHeight());
        hbTap.setPrefWidth(screen.getWidth());

        IdleMonitor idleMonitor = new IdleMonitor(Duration.seconds(60), () -> {
            vbDetails.setVisible(false);
            pMap.setVisible(true);
            pMap.setOpacity(0.25);
            pTapToStart.setVisible(true);
        }, true);
        idleMonitor.register(vbDetails, Event.ANY);

        updateDatabase();

        tcBusCompany.setCellValueFactory(new PropertyValueFactory<>("busCompany"));
        tcBusType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcWingArea.setCellValueFactory(new PropertyValueFactory<>("wingArea"));
        tcBayNumber.setCellValueFactory(new PropertyValueFactory<>("bayNumber"));
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tcFirstTrip.setCellValueFactory(new PropertyValueFactory<>("departure"));
        tcLastTrip.setCellValueFactory(new PropertyValueFactory<>("lastTrip"));
        tcTime.setCellValueFactory(new PropertyValueFactory<>("nextTime"));
        tcMaxFare.setCellValueFactory(new PropertyValueFactory<>("fares"));

        tvBusDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                newValue = oldValue;
            }
            String ivPath = "sample/res/Terminal Map/GIF/";
            switch (newValue.getWingArea()) {
                case "Left Wing":
                    ivPath = ivPath.concat("LW-");
                    break;
                case "Center Wing":
                    ivPath = ivPath.concat("CW-");
                    break;
                default:
                    ivPath = ivPath.concat("RW-");
            }
            ivPath = ivPath.concat(newValue.getBayNumber() + ".gif");
            ivTerminalPath.setImage(new Image(ivPath));
        });
    }

    private void updateDatabase() {
        String sql = "SELECT *" +
                "FROM municipality_info";

        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            municipalities.clear();
            while (rs.next()) {
                municipalities.add(new Municipality(rs.getString("name"), rs.getInt("fare_ordinary"), rs.getInt("fare_aircon"), null, null, null, rs.getString("travel_time"), rs.getString("travel_distance")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            for (int i = 0; i < municipalities.size(); i++) {
                rs.next();
                Municipality leftMun = null, rightMun = null, subroute = null;
                Municipality[] encompassingMunicipality = new Municipality[15];

                String left = rs.getString("left_mun");
                String right = rs.getString("right_mun");
                String subR = rs.getString("subroute");
                boolean subrouteFound = false;
                for (Municipality m : municipalities) {
                    if (m.toString().equalsIgnoreCase(left)) {
                        leftMun = m;
                    } else if (m.toString().equalsIgnoreCase(right)) {
                        rightMun = m;
                    }
                    if (!subrouteFound && m.getName().equalsIgnoreCase(subR)) {
                        subroute = m;
                        subrouteFound = true;
                    }
                }
                ObservableList<Municipality> encompassed = FXCollections.observableArrayList();
                String enc = rs.getString("encompassed_mun");
                String mun = "";
                for (int j = 0; j < enc.length(); j++) {

                    if (enc.charAt(j) == ',' || j == enc.length() - 1) {
                        if (j == enc.length() - 1) {
                            mun = mun.concat(enc.charAt(j) + "");
                        }
                        for (Municipality m : municipalities) {
                            if (mun.equalsIgnoreCase(m.getName())) {
                                encompassed.add(m);
                                break;
                            }
                        }
                        mun = "";
                        j++;
                    } else {
                        mun = mun.concat(enc.charAt(j) + "");
                    }
                }
                municipalities.get(i).setLeftMun(leftMun);
                municipalities.get(i).setRightMun(rightMun);
                municipalities.get(i).setSubroute(subroute);

                int j = 0;
                for (Municipality m : encompassed) {
                    encompassingMunicipality[j++] = m;
                }
                municipalities.get(i).setEncompassingMunicipality(encompassingMunicipality);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        sql = "SELECT *" +
                "FROM bus_info";

        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            buses.clear();
            while (rs.next()) {
                Municipality thisMunicipality = null;
                for (Municipality m : municipalities) {
                    if (rs.getString("destination").equalsIgnoreCase(m.toString())) {
                        thisMunicipality = m;
                        break;
                    }
                }
                String times = rs.getString("times");
                java.util.Date[] timeLine = new java.util.Date[50];
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

        sql = "SELECT *" +
                "FROM faq";
        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ObservableList<MenuButton> mbs = FXCollections.observableArrayList();
            ImageView comfortRoom = new ImageView("sample/res/Terminal Map/GIF/Comfort_Room.gif");
            comfortRoom.setFitWidth(270);
            comfortRoom.setFitHeight(270);
            ImageView diningArea = new ImageView("sample/res/Terminal Map/GIF/Dining_Area.jpg");
            diningArea.setFitHeight(270);
            diningArea.setFitWidth(270);
            ImageView chargingStation = new ImageView("sample/res/Terminal Map/GIF/Charging_Station.gif");
            chargingStation.setFitHeight(270);
            chargingStation.setFitWidth(270);
            while (rs.next()) {
                ImageView help = new ImageView("sample/res/help.png");
                help.setFitHeight(10.5);
                help.setFitWidth(10.5);
                MenuItem menuItem;
                if (rs.getString("answer").contains("Comfort_Room")) {
                    menuItem = new MenuItem("", comfortRoom);
                } else if (rs.getString("answer").contains("Dining_Area")) {
                    menuItem = new MenuItem("", diningArea);
                } else if (rs.getString("answer").contains("Charging_Station")) {
                    menuItem = new MenuItem("", chargingStation);
                } else {
                    menuItem = new MenuItem(rs.getString("answer"));
                }
                MenuButton mb = new MenuButton(rs.getString("question"), help, menuItem);
                mb.setFocusTraversable(false);
                mb.setPrefSize(270, 20);
                mbs.add(mb);
            }
            lvFAQ.setItems(mbs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        sql = "SELECT *" +
                "FROM announcements";
        try (Connection conn = this.connect()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ObservableList<TextArea> ann = FXCollections.observableArrayList();
            while (rs.next()) {
                TextArea ta = new TextArea(rs.getString("announcement"));
                ta.setEditable(false);
                ta.setWrapText(true);
                ta.setPrefWidth(270);
                ta.setPrefHeight(Math.ceil(rs.getString("announcement").length() / 45) * 30);
                ta.setFocusTraversable(false);
                ann.add(ta);
            }
            if (ann.size() == 0) {
                TextArea ta = new TextArea("No announcements for today.");
                ta.setEditable(false);
                ta.setWrapText(true);
                ta.setPrefWidth(270);
                ta.setPrefHeight(30);
                ta.setFocusTraversable(false);
                ann.add(ta);
            }
            lvAnnouncements.setItems(ann);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBacolodSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Bacolod")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleTuburanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Tuburan")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleAsturiasSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Asturias")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleBalambanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Balamban")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleToledoCitySelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Toledo City")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handlePinamungajanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Pinamungajan")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleAloguinsanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Aloguinsan")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleBariliSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Barili")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleDumanjugSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Dumanjug")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleRondaSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Ronda")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleAlcantaraSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Alcantara")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleMoalboalSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Moalboal")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleBadianSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Badian")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleAlegriaSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Alegria")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleMalabuyocSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Malabuyoc")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleGinatilanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Ginatilan")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleSamboanSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Samboan")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleSantanderSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Santander")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleOslobSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Oslob")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleBoljoonSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Boljoon")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleAlcoySelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Alcoy")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleDalagueteSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Dalaguete")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleArgaoSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Argao")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleSibongaSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Sibonga")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleCarcarCitySelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Carcar City")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleDumagueteSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Dumaguete")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    @FXML
    public void handleZamboangaSelected() {
        for (Municipality m : municipalities) {
            if (m.getName().equalsIgnoreCase("Zamboanga")) {
                handleMunicipalitySelected(m);
                break;
            }
        }
    }

    private void handleMunicipalitySelected(Municipality municipality) {
        pMap.setOpacity(0.25);
        vbDetails.setVisible(true);

        lDestination.setText(municipality.toString());
        lPrev.setText(municipality.getLeftMun().getName());
        lNext.setText(municipality.getRightMun().getName());

        qualifier.clear();
        for (Bus bus : buses) {
            Municipality destination = bus.getDestination();
            if (destination != null && destination.toString().contains(municipality.toString())) {
                qualifier.add(bus);
            } else if (destination != null && destination.getEncompassingMunicipality()[0] != null) {
                for (Municipality m : destination.getEncompassingMunicipality()) {
                    if (m != null && m.toString().equalsIgnoreCase(municipality.toString())) {
                        qualifier.add(bus);
                    }
                }
            }
        }
        qualifier = bubbleSort(qualifier);
        tvBusDetails.setItems(qualifier);
        tvBusDetails.setFocusTraversable(true);

        String ivPath = "sample/res/Terminal Map/GIF/";
        switch (qualifier.get(0).getWingArea()) {
            case "Left Wing":
                ivPath = ivPath.concat("LW-");
                break;
            case "Center Wing":
                ivPath = ivPath.concat("CW-");
                break;
            default:
                ivPath = ivPath.concat("RW-");
        }
        ivPath = ivPath.concat(qualifier.get(0).getBayNumber() + ".gif");
        ivTerminalPath.setImage(new Image(ivPath));

        taTravelDistance.setText(municipality.getTravelDistance());
        taTravelTime.setText(municipality.getTravelTime());
        if (municipality.getFareAircon() == 0) {
            taFareAircon.setText("-");
        } else {
            taFareAircon.setText(municipality.getFareAircon() + "");
        }
        if (municipality.getFareOrdinary() == 0) {
            taFareOrdinary.setText("-");
        } else {
            taFareOrdinary.setText(municipality.getFareOrdinary() + "");
        }
    }

    @FXML
    private void handleBackToMap() {
        vbDetails.setVisible(false);
        pMap.setVisible(true);
        pMap.setOpacity(1.0);
        updateDatabase();
    }

    @FXML
    private void handlePrevClicked() {
        handleNaviClicked(lPrev.getText());
    }

    @FXML
    private void handleNextClicked() {
        handleNaviClicked(lNext.getText());
    }

    private void handleNaviClicked(String muni) {
        for (Municipality m : municipalities) {
            if (muni.equalsIgnoreCase(m.toString())) {
                handleMunicipalitySelected(m);
            }
        }
    }

    @FXML
    private void handleStartClicked() {
        pTapToStart.setVisible(false);
        pMap.setOpacity(1.0);
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

    private ObservableList<Bus> bubbleSort(ObservableList<Bus> buses) {
        int n = buses.size();
        Bus temp;
        Bus[] arr = new Bus[50];
        for (int i = 0; i < buses.size(); i++) {
            arr[i] = buses.get(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                double left, right;
                if (arr[j - 1].getNextTime().equalsIgnoreCase("Arrived")) {
                    left = 0;
                } else {
                    left = arr[j-1].nearestTime();
                }
                if (arr[j].getNextTime().equalsIgnoreCase("Arrived")) {
                    right = 0;
                } else {
                    right = arr[j].nearestTime();
                }
                if (left > right) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        buses.clear();
        for (Bus b : arr) {
            if (b != null) {
                buses.add(b);
            }
        }
        return buses;
    }
}
