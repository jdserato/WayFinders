package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class Controller implements Initializable{

    public ImageView btnBusSchedule, btnRoutesAndFares, btnBusTerminal;
    public TableView<Bus> tblSchedule;
    public TableColumn<Bus, String> tcType, tcBusCompany, tcDestination, tcDeparture, tcLastTrip, tcTrips, tcBus, tcBay;
    public Label lblLeft, lblRight, lblHeader;
    public Pane pBusSchedule, pBusRoutesAndFares, pBusTerminal;
    public ListView<String> lvDestination;
    public ListView<Company> lvCompany;
    public VBox vbAll, vbData;
    public TextField tfDestination, tfCompany, tfFare, tfType, tfWingArea, tfBayNumber;

    private boolean btnBusScheduleClicked, btnBusRoutesAndFaresClicked, btnBusTerminalClicked;
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
            btnBusSchedule.setImage(new Image("sample/Bus-Schedule.png"));
            if (btnBusRoutesAndFaresClicked) {
                btnBusRoutesAndFaresClicked = false;
                pBusRoutesAndFares.setVisible(false);
                btnRoutesAndFares.setImage(new Image("sample/Fares-and-Routes_GS.png"));
            } else if (btnBusTerminalClicked) {
                btnBusTerminalClicked = false;
                pBusTerminal.setVisible(false);
                btnBusTerminal.setImage(new Image("sample/Bus-Terminal_GS.png"));
            }
        } else { // if the button is activated, then we deactivate it
            btnBusScheduleClicked = false;
            pBusSchedule.setVisible(false);
            btnBusSchedule.setImage(new Image("sample/Bus-Schedule_GS.png"));
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
            btnRoutesAndFares.setImage(new Image("sample/Fares-and-Routes.png"));
            if (btnBusScheduleClicked) {
                btnBusScheduleClicked = false;
                pBusSchedule.setVisible(false);
                btnBusSchedule.setImage(new Image("sample/Bus-Schedule_GS.png"));
            } else if (btnBusTerminalClicked) {
                btnBusTerminalClicked = false;
                pBusTerminal.setVisible(false);
                btnBusTerminal.setImage(new Image("sample/Bus-Terminal_GS.png"));
            }
        } else { // if Button Routes and Fares was clicked, then we deactivate it
            btnBusRoutesAndFaresClicked = false;
            pBusRoutesAndFares.setVisible(false);
            btnRoutesAndFares.setImage(new Image("sample/Fares-and-Routes_GS.png"));
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
            btnBusTerminal.setImage(new Image("sample/Bus-Terminal.png"));
            if (btnBusScheduleClicked) {
                btnBusScheduleClicked = false;
                pBusSchedule.setVisible(false);
                btnBusSchedule.setImage(new Image("sample/Bus-Schedule_GS.png"));
            } else if (btnBusRoutesAndFaresClicked) {
                btnBusRoutesAndFaresClicked = false;
                pBusRoutesAndFares.setVisible(false);
                btnRoutesAndFares.setImage(new Image("sample/Fares-and-Routes_GS.png"));
            }
        } else { // if Bus Terminal was clicked, then we deactivate it
            btnBusTerminalClicked = false;
            pBusTerminal.setVisible(false);
            btnBusTerminal.setImage(new Image("sample/Bus-Terminal_GS.png"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image background = new Image("sample/Background.png", 1380, 780, false, true);
        vbAll.setBackground(new Background(new BackgroundImage(background, NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT)));
        // BUS SCHEDULE INITIALIZATION
        rightWing = FXCollections.observableArrayList(
                new Bus(1, new Company("JEGANS"), "Ordinary", "Toledo/Pinamungajan", "4:00 am", "4:00 pm", 4, 5, 70, "Right Wing"),
                new Bus(2, new Company("CALVO"), "Ordinary", "Toledo/Balamban", "3:00 am", "9:00 pm", 4, 32, 70, "Right Wing"),
                new Bus(3, new Company("COROMINAS"), "Ordinary", "Balamban/Asturian/Tuburan/Toledo", "3:00 am", "6:00 pm", 3, 20, 90, "Right Wing"),
                new Bus(4, new Company("CERES LINER"), "Aircon/Ordinary", "Balamban/Pinamungahan", "4:00 am", "7:00 pm", 4, 7, 75, "Right Wing"),
                new Bus(4, new Company("CERES LINER"), "Aircon/Ordinary", "Toledo", "4:00 am", "7:00 pm", 4, 2, 60, "Right Wing"),
                new Bus(5, new Company("GABE TRANSIT"), "Ordinary", "Toledo/Balamban", "5:00 am", "9:00 pm", 4, 8, 60, "Right Wing"),
                new Bus(6, new Company("CANONEO"), "Ordinary", "Toledo", "9:00 am", "8:00 pm", 4, 5, 60, "Right Wing"));

        centerWing = FXCollections.observableArrayList(
                new Bus(1, new Company("JRK / SEPO"), "Ordinary", "Pinamungajan via Aloguinsan", "9:00 am", "6:00 pm", 1, 8, 70, "Center Wing"),
                new Bus(2, new Company("CERES LINER"), "Aircon", "Bato via Barili", "4:00 am", "6:00 pm", 3, 12, 85, "Center Wing"),
                new Bus(3, new Company("CERES LINER"), "Ordinary", "Bato via Barili", "1:00 am", "10:00 pm", 3, 38, 72, "Center Wing"),
                new Bus(4, new Company("CERES LINER"), "Ordinary", "Moalboal", "-", "-", 4, 4, 107, "Center Wing"),
                new Bus(4, new Company("CERES LINER"), "Ordinary", "Carcar", "-", "-", 7, 13, 40, "Center Wing"),
                new Bus(5, new Company("CERES LINER"), "Ordinary", "Argao", "-", "-", 6, 15, 79, "Center Wing"),
                new Bus(6, new Company("CERES LINER"), "Ordinary", "Alcoy", "-", "-", 4, 4, 100, "Center Wing"),
                new Bus(7, new Company("CERES LINER"), "Ordinary", "Bato via Oslob", "-", "-", 3, 27, 138, "Center Wing"),
                new Bus(8, new Company("CERES LINER"), "Aircon", "Argao", "7:00 am", "12:00 am", 5, 5, 83, "Center Wing"),
                new Bus(9, new Company("CERES LINER"), "Aircon", "Bato/Oslob/Alcoy", "1:00 am", "12:00 am", 5, 5, 145, "Center Wing"),
                new Bus(10, new Company("CERES LINER"), "Aircon", "Dumaguete", "6:00 am", "6:00 pm", 1, 9, 275, "Center Wing"),
                new Bus(11, new Company("CERES LINER"), "Aircon", "Bacolod", "1:00 am", "-", 1, 2, 560, "Center Wing"),
                new Bus(11, new Company("CERES LINER"), "Aircon", "Zamboanga", "2:00 pm", "-", 1, 1, 560, "Center Wing"),
                new Bus(12, new Company("JHADE"), "Ordinary", "Aloguinsan via Pinamungajan", "4:30 am", "5:00 pm", 2, 6, 80, "Center Wing")
        );
        leftWing = FXCollections.observableArrayList(
                new Bus(2, new Company("METROLINK [Weekdays]"), "Ordinary", "Bato/Barili", "4:00 am", "10:00 pm", 1, 10, 65, "Left Wing"),
                new Bus(3, new Company("METROLINK [Weekends]"), "Ordinary", "Bato/Barili", "4:00 am", "10:00 pm", 2, 10, 65, "Left Wing"),
                new Bus(4, new Company("SUNRAYS"), "Aircon/Ordinary", "Samboan/Oslob", "4:00 am", "8:30 pm", 2, 34, 143, "Left Wing"),
                new Bus(5, new Company("SUNRAYS"), "Aircon/Ordinary", "Samboan Liloan Port", "4:00 am", "8:30 pm", 2, 34, 143, "Left Wing"),
                new Bus(6, new Company("CHAN TRANSIT"), "Ordinary", "Dumanjug", "5:00 am", "11:00 pm", 2, 22, 75, "Left Wing"),
                new Bus(7, new Company("JBH/BRITT"), "Ordinary", "Dumanjug/Barili", "6:00 am", "10:00 pm", 2, 9, 75, "Left Wing"),
                new Bus(7, new Company("SOCORRO"), "Ordinary", "Dumanjug", "6:00 am", "6:00 pm", 3, 5, 75, "Left Wing"),
                new Bus(8, new Company("BEFEL LINER"), "Ordinary", "Carcar", "6:00 am", "6:00 pm", 6, 1, 60, "Left Wing"),
                new Bus(8, new Company("DOS HERMANOS"), "Ordinary", "Carcar", "6:00 am", "8:00 pm", 6, 3, 60, "Left Wing"),
                new Bus(8, new Company("INDAY JEAN"), "Ordinary", "Carcar", "5:00 am", "7:00 pm", 4, 1, 60, "Left Wing"),
                new Bus(9, new Company("ALLOWIE"), "Ordinary", "Sibonga", "6:00 am", "8:00 pm", 4, 5, 70, "Left Wing"),
                new Bus(9, new Company("ANDREW LINER"), "Ordinary", "Sibonga", "5:00 am", "6:00 pm", 4, 2, 70, "Left Wing"),
                new Bus(9, new Company("EDC"), "Ordinary", "Sibonga", "7:00 am", "7:00 pm", 2, 9, 70, "Left Wing"),
                new Bus(9, new Company("JRK/SEPO"), "Ordinary", "Sibonga", "5:00 am", "8:00 pm", 2, 14, 70, "Left Wing"),
                new Bus(9, new Company("PIONEER"), "Ordinary", "Sibonga", "4:00 am", "8:00 pm", 4, 10, 70, "Left Wing"),
                new Bus(9, new Company("SKIPPER"), "Ordinary", "Sibonga", "5:00 am", "6:00 pm", 4, 3, 70, "Left Wing"),
                new Bus(9, new Company("YEOMAN"), "Ordinary", "Sibonga", "5:00 am", "7:00 pm", 4, 10, 70, "Left Wing")
        );

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
