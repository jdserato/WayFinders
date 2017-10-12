package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public ImageView btnBusSchedule, btnRoutesAndFares, btnBusTerminal;
    private boolean btnBusScheduleClicked, btnBusRoutesAndFaresclicked, btnBusTerminalClicked;
    public TableView tblSchedule;

    @FXML
    private void handleBtnBusScheduleClick() {
        if (!btnBusScheduleClicked) {
            // TODO show image
            btnBusScheduleClicked = true;
            // insert Table editing here
            //tblSchedule.setItems();
        } else {
            // TODO show background image
            btnBusScheduleClicked = false;
        }
    }

    @FXML
    private void handleBtnRoutesAndFaresClick() {

    }

    @FXML
    private void handleBtnBusTerminalClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tblSchedule.setItems();
    }
}
