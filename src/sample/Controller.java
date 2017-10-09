package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button btnBusSchedule, btnRoutesAndFares, btnBusTerminal;
    private boolean btnBusScheduleClicked, btnBusRoutesAndFaresclicked, btnBusTerminalClicked;

    @FXML
    private void handleBtnBusScheduleClick() {
        if (btnBusScheduleClicked) {
            // TODO show image
        } else {
            // TODO show background image
        }
    }

    @FXML
    private void handleBtnRoutesAndFaresClick() {

    }

    @FXML
    private void handleBtnBusTerminalClick() {

    }
}
