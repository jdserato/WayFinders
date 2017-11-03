package sample;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;
import java.sql.*;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class Controller implements Initializable{

    public ImageView btnBusSchedule, btnRoutesAndFares, btnBusTerminal, btnTermArrow, imgDirectory;
    public TableView<Bus> tblSchedule;
    public TableColumn<Bus, String> tcType, tcBusCompany, tcDestination, tcDeparture, tcLastTrip, tcTrips, tcBus, tcBay;
    public Label lblLeft, lblRight, lblHeader, lblTermArrow;
    public Pane pBusSchedule, pBusRoutesAndFares, pBusTerminal;
    public ListView<String> lvDestination;
    public ListView<Company> lvCompany;
    public VBox vbAll, vbData;
    public TextField tfDestination, tfCompany, tfFare, tfType, tfWingArea, tfBayNumber;

    private boolean btnBusScheduleClicked, btnBusRoutesAndFaresClicked, btnBusTerminalClicked, btnTermArrowUp;
    private int busSelection = 2;
    private ObservableList<Bus> rightWing, centerWing, leftWing, allBuses = FXCollections.observableArrayList();
    private String destChosen = "";

    @FXML
    private void handleBtnBusScheduleClick() {
        if (!btnBusScheduleClicked) {  // if the button was not clicked, then we activate it and deactivate others
            btnBusScheduleClicked = true;
            pBusSchedule.setVisible(true);
            pBusRoutesAndFares.setPrefHeight(0);
            pBusTerminal.setPrefHeight(0);
            pBusSchedule.setPrefHeight(1100);
            btnBusSchedule.setImage(new Image("sample/res/Bus-Schedule_c.png"));
            if (btnBusRoutesAndFaresClicked) {
                btnBusRoutesAndFaresClicked = false;
                pBusRoutesAndFares.setVisible(false);
                btnRoutesAndFares.setImage(new Image("sample/res/Fares-and-Routes_uc.png"));
            } else if (btnBusTerminalClicked) {
                btnBusTerminalClicked = false;
                pBusTerminal.setVisible(false);
                btnBusTerminal.setImage(new Image("sample/res/Bus-Terminal_uc.png"));
            }
        } else { // if the button is activated, then we deactivate it
            btnBusScheduleClicked = false;
            pBusSchedule.setVisible(false);
            btnBusSchedule.setImage(new Image("sample/res/Bus-Schedule_uc.png"));
        }
    }

    @FXML
    private void handleBtnRoutesAndFaresClick() {
        if (!btnBusRoutesAndFaresClicked) { // if the button was not clicked, then we activate it and deactivate others
            btnBusRoutesAndFaresClicked = true;
            pBusRoutesAndFares.setVisible(true);
            pBusSchedule.setPrefHeight(0);
            pBusTerminal.setPrefHeight(0);
            pBusRoutesAndFares.setPrefHeight(1100);
            btnRoutesAndFares.setImage(new Image("sample/res/Fares-and-Routes_c.png"));
            if (btnBusScheduleClicked) {
                btnBusScheduleClicked = false;
                pBusSchedule.setVisible(false);
                btnBusSchedule.setImage(new Image("sample/res/Bus-Schedule_uc.png"));
            } else if (btnBusTerminalClicked) {
                btnBusTerminalClicked = false;
                pBusTerminal.setVisible(false);
                btnBusTerminal.setImage(new Image("sample/res/Bus-Terminal_uc.png"));
            }
        } else { // if Button Routes and Fares was clicked, then we deactivate it
            btnBusRoutesAndFaresClicked = false;
            pBusRoutesAndFares.setVisible(false);
            btnRoutesAndFares.setImage(new Image("sample/res/Fares-and-Routes_uc.png"));
        }
    }

    @FXML
    private void handleBtnBusTerminalClick() {
        if (!btnBusTerminalClicked) { // if Bus Terminal was not clicked before
            btnBusTerminalClicked = true;
            pBusTerminal.setVisible(true);
            pBusSchedule.setPrefHeight(0);
            pBusRoutesAndFares.setPrefHeight(0);
            pBusTerminal.setPrefHeight(1100);
            btnBusTerminal.setImage(new Image("sample/res/Bus-Terminal_c.png"));
            if (btnBusScheduleClicked) {
                btnBusScheduleClicked = false;
                pBusSchedule.setVisible(false);
                btnBusSchedule.setImage(new Image("sample/res/Bus-Schedule_uc.png"));
            } else if (btnBusRoutesAndFaresClicked) {
                btnBusRoutesAndFaresClicked = false;
                pBusRoutesAndFares.setVisible(false);
                btnRoutesAndFares.setImage(new Image("sample/res/Fares-and-Routes_uc.png"));
            }
        } else { // if Bus Terminal was clicked, then we deactivate it
            btnBusTerminalClicked = false;
            pBusTerminal.setVisible(false);
            btnBusTerminal.setImage(new Image("sample/res/Bus-Terminal_uc.png"));
        }
    }

    @FXML
    private void handleBtnLeftClicked() {
        switch (busSelection) {
            case 1: // if the currently shown table is of Left Wing, we show Right Wing
                tblSchedule.setItems(rightWing);
                busSelection = 3;
                lblLeft.setText("Center Wing");
                lblRight.setText("Left Wing");
                lblHeader.setText("BUS SCHEDULE - RIGHT WING");
                break;
            case 2: // if the currently shown table is of Center Wing, we show Left Wing
                tblSchedule.setItems(leftWing);
                busSelection = 1;
                lblLeft.setText("Right Wing");
                lblRight.setText("Center Wing");
                lblHeader.setText("BUS SCHEDULE - LEFT WING");
                break;
            default: //if the currently shown table is of Right Wing, we show Center Wing
                tblSchedule.setItems(centerWing);
                busSelection = 2;
                lblLeft.setText("Left Wing");
                lblRight.setText("Right Wing");
                lblHeader.setText("BUS SCHEDULE - CENTER WING");
        }

    }

    @FXML
    private void handleBtnRightClicked() {
        switch (busSelection) {
            case 1: // if currently shown table is of Left Wing, then we show Center Wing
                tblSchedule.setItems(centerWing);
                busSelection = 2;
                lblLeft.setText("Left Wing");
                lblRight.setText("Right Wing");
                lblHeader.setText("BUS SCHEDULE - CENTER WING");
                break;
            case 2: // if the currently shown table is of Center Wing, we show Right Wing
                tblSchedule.setItems(rightWing);
                busSelection = 3;
                lblLeft.setText("Center Wing");
                lblRight.setText("Left Wing");
                lblHeader.setText("BUS SCHEDULE - RIGHT WING");
                break;
            default: // if the currently shown table is of Right Wing, we show Left Wing
                tblSchedule.setItems(leftWing);
                busSelection = 1;
                lblLeft.setText("Right Wing");
                lblRight.setText("Center Wing");
                lblHeader.setText("BUS SCHEDULE - LEFT WING");
        }
    }

    @FXML
    public void handleTermArrowClicked() {
        if (btnTermArrowUp) {
            // TODO show second floor
            //imgDirectory.setImage();
            btnTermArrow.setRotate(180);
            btnTermArrowUp = false;
            lblTermArrow.setText("First Floor");
        } else {
            // show first floor
            imgDirectory.setImage(new Image("sample/terminal.png"));
            btnTermArrow.setRotate(0);
            btnTermArrowUp = true;
            lblTermArrow.setText("Second Floor");
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:src/sample/db/bus.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image background = new Image("sample/res/Background.png", 1380, 780, false, true);
        vbAll.setBackground(new Background(new BackgroundImage(background, NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT)));
        // BUS SCHEDULE INITIALIZATION

        ArrayList<Bus> temp = new ArrayList<>();
        String sql = "SELECT bay_num, company, type, destination, first_departure, last_trip, no_of_trips, no_of_buses, fare " +
                "FROM bus_info " +
                "WHERE wing_area = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, "Right Wing");
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Bus(rs.getInt("bay_num"), new Company(rs.getString("company")), rs.getString("type"), rs.getString("destination"),
                        rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), "Right Wing"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        rightWing = FXCollections.observableArrayList(temp);
        temp.clear();

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, "Center Wing");
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Bus(rs.getInt("bay_num"), new Company(rs.getString("company")), rs.getString("type"), rs.getString("destination"),
                        rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), "Center Wing"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        centerWing = FXCollections.observableArrayList(temp);
        temp.clear();

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, "Left Wing");
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Bus(rs.getInt("bay_num"), new Company(rs.getString("company")), rs.getString("type"), rs.getString("destination"),
                        rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), "Left Wing"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        leftWing = FXCollections.observableArrayList(temp);

        tcBay.setCellValueFactory(new PropertyValueFactory<>("bayNumber"));
        tcBusCompany.setCellValueFactory(new PropertyValueFactory<>("busCompany"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tcDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
        tcLastTrip.setCellValueFactory(new PropertyValueFactory<>("lastTrip"));
        tcTrips.setCellValueFactory(new PropertyValueFactory<>("trips"));
        tcBus.setCellValueFactory(new PropertyValueFactory<>("buses"));

        tblSchedule.setItems(centerWing);

        // ROUTES AND FARES INITIALIZATION
        ArrayList<String> destination = new ArrayList<>();
        for (Bus b : rightWing) {
            if (!destination.contains(b.getDestination())) {
                destination.add(b.getDestination());
            }
            allBuses.add(b);
        }
        for (Bus b : centerWing) {
            if (!destination.contains(b.getDestination())) {
                destination.add(b.getDestination());
            }
            allBuses.add(b);
        }
        for (Bus b : leftWing) {
            if (!destination.contains(b.getDestination())) {
                destination.add(b.getDestination());
            }
            allBuses.add(b);
        }
        lvDestination.setItems(FXCollections.observableArrayList(destination));
    }

    @FXML
    private void handleDestinationClicked() {
        ObservableList companies = FXCollections.observableArrayList();
        lvDestination.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                destChosen = newValue;
                for (Bus b : allBuses) {
                    if (b.getDestination().equalsIgnoreCase(destChosen)) {
                        companies.add(b.getBusCompany());
                    }
                }
                lvCompany.setItems(companies);
            }
        });

        tfBayNumber.setText("");
        tfCompany.setText("");
        tfDestination.setText("");
        tfFare.setText("");
        tfWingArea.setText("");
        tfType.setText("");
    }

    @FXML
    private void handleCompanyClicked() {
        lvCompany.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Company>() {
            @Override
            public void changed(ObservableValue<? extends Company> observable, Company oldValue, Company newValue) {
                for (Bus b : allBuses) {
                    if ((destChosen.equalsIgnoreCase(b.getDestination()) && b.getBusCompany().equals(newValue))) {
                        tfBayNumber.setText(b.getBayNumber() + "");
                        tfCompany.setText(b.getBusCompany().getName());
                        tfDestination.setText(b.getDestination());
                        tfFare.setText(b.getFares() + "");
                        tfWingArea.setText(b.getWingArea());
                        tfType.setText(b.getType());
                        break;
                    }
                }
            }
        });
    }
}
