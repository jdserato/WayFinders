package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public ImageView btnBusSchedule, btnRoutesAndFares, btnBusTerminal;
    public TableView<Bus> tblSchedule;
    public TableColumn<Bus, String> tcType, tcBusCompany, tcDestination, tcDeparture, tcLastTrip, tcTrips, tcBus;
    public Label lblLeft, lblRight, lblHeader;
    public Pane pBusSchedule, pBusRoutesAndFares, pBusTerminal;
    public ListView<String> lvDestination;

    private boolean btnBusScheduleClicked, btnBusRoutesAndFaresClicked, btnBusTerminalClicked;
    private int busSelection = 2;
    private ObservableList<Bus> rightWing, centerWing, leftWing;
    private ObservableList<String> destinations;

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
        // BUS SCHEDULE INITIALIZATION
        rightWing = FXCollections.observableArrayList(
                new Bus("Bay 01 - JEGANS", "Ordinary", "Toledo/Pinamungajan", "4:00 am", "4:00 pm", 4, 5),
                new Bus("Bay 02 - CALVO", "Ordinary", "Toledo/Balamban", "3:00 am", "9:00 pm", 4, 32),
                new Bus("Bay 03 - COROMINAS", "Ordinary", "Balamban/Asturian/Tuburan/Toledo", "3:00 am", "6:00 pm", 3, 20),
                new Bus("Bay 04 - CERES", "Aircon/Ordinary", "Balamban/Pinamungahan", "4:00 am", "7:00 pm", 4, 7),
                new Bus("Bay 04 - CERES", "Aircon/Ordinary", "Toledo", "4:00 am", "7:00 pm", 4, 2),
                new Bus("Bay 05 - GABE TRANSIT", "Ordinary", "Toedo/Balamban", "5:00 am", "9:00 pm", 4, 8),
                new Bus("Bay 06 - CANONEO", "Ordinary", "Toledo", "9:00 am", "8:00 pm", 4, 5)
        );

        centerWing = FXCollections.observableArrayList(
                new Bus("Bay 01 - JRK / SEPO", "Ordinary", "Pinamungajan via Aloguinsan", "9:00 am", "6:00 pm", 1, 8),
                new Bus("Bay 02 - CERES LINER", "Aircon", "Bato via Barili", "4:00 am", "6:00 pm", 3, 12),
                new Bus("Bay 03 - CERES LINER", "Ordinary", "Bato via Barili", "1:00 am", "10:00 pm", 3, 38),
                new Bus("Bay 04 - CERES LINER", "Ordinary", "Moalboal", "-", "-", 4, 4),
                new Bus("Bay 04 - CERES LINER", "Ordinary", "Carcar", "-", "-", 7, 13),
                new Bus("Bay 05 - CERES LINER", "Ordinary", "Argao", "-", "-", 6, 15),
                new Bus("Bay 06 - CERES LINER", "Ordinary", "Alcoy", "-", "-", 4, 4),
                new Bus("Bay 07 - CERES LINER", "Ordinary", "Bato via Oslob", "-", "-", 3, 27),
                new Bus("Bay 08 - CERES LINER", "Aircon", "Argao", "7:00 am", "12:00 am", 5, 5),
                new Bus("Bay 09 - CERES LINER", "Aircon", "Bato/Oslob/Alcoy", "1:00 am", "12:00 am", 5, 5),
                new Bus("Bay 10 - CERES LINER", "Aircon", "Dumaguete", "6:00 am", "6:00 pm", 1, 9),
                new Bus("Bay 11 - CERES LINER", "Aircon", "Bacolod", "1:00 am", "-", 1, 2),
                new Bus("Bay 11 - CERES LINER", "Aircon", "Zamboanga", "2:00 pm", "-", 1, 1),
                new Bus("Bay 12 - JHADE", "Ordinary", "Aloguinsan via Pinamungajan", "4:30 am", "5:00 pm", 2, 6)
        );
        leftWing = FXCollections.observableArrayList(
                new Bus("Bay 02 - METROLINK [Weekdays]", "Ordinary", "Bato/Barili", "4:00 am", "10:00 pm", 1, 10),
                new Bus("Bay 03 - METROLINK [Weekends]", "Ordinary", "Bato/Barili", "4:00 am", "10:00 pm", 2, 10),
                new Bus("Bay 04 - SUNRAYS", "Aircon/Ordinary", "Samboan/Oslob", "4:00 am", "8:30 pm", 2, 34),
                new Bus("Bay 05 - SUNRAYS", "Aircon/Ordinary", "Samboan Liloan Port", "4:00 am", "8:30 pm", 2, 34),
                new Bus("Bay 06 - CHAN TRANSIT", "Ordinary", "Dumanjug", "5:00 am", "11:00 pm", 2, 22),
                new Bus("Bay 07 - JBH/BRITT", "Ordinary", "Dumanjug/Barili", "6:00 am", "10:00 pm", 2, 9),
                new Bus("Bay 07 - SOCORRO", "Ordinary", "Dumanjug", "6:00 am", "6:00 pm", 3, 5),
                new Bus("Bay 08 - BEFEL LINER", "Ordinary", "Carcar", "6:00 am", "6:00 pm", 6, 1),
                new Bus("Bay 08 - DOS HERMANOS", "Ordinary", "Carcar", "6:00 am", "8:00 pm", 6, 3),
                new Bus("Bay 08 - INDAY JEAN", "Ordinary", "Carcar", "5:00 am", "7:00 pm", 4, 1),
                new Bus("Bay 09 - ALLOWIE", "Ordinary", "Sibonga", "6:00 am", "8:00 pm", 4, 5),
                new Bus("Bay 09 - ANDREW LINER", "Ordinary", "Sibonga", "5:00 am", "6:00 pm", 4, 2),
                new Bus("Bay 09 - EDC", "Ordinary", "Sibonga", "7:00 am", "7:00 pm", 2, 9),
                new Bus("Bay 09 - JRK/SEPO", "Ordinary", "Sibonga", "5:00 am", "8:00 pm", 2, 14),
                new Bus("Bay 09 - PIONEER", "Ordinary", "Sibonga", "4:00 am", "8:00 pm", 4, 10),
                new Bus("Bay 09 - SKIPPER", "Ordinary", "Sibonga", "5:00 am", "6:00 pm", 4, 3),
                new Bus("Bay 09 - YEOMAN", "Ordinary", "Sibonga", "5:00 am", "7:00 pm", 4, 10)
        );

        tcBusCompany.setCellValueFactory(new PropertyValueFactory<>("busCompany"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tcDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
        tcLastTrip.setCellValueFactory(new PropertyValueFactory<>("lastTrip"));
        tcTrips.setCellValueFactory(new PropertyValueFactory<>("trips"));
        tcBus.setCellValueFactory(new PropertyValueFactory<>("buses"));

        tblSchedule.setItems(centerWing);

        // ROUTES AND FARES INITIALIZATION

        destinations = FXCollections.observableArrayList("Argao", "Carcar");
        lvDestination.setItems(destinations);
    }
}
