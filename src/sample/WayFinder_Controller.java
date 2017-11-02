package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import sample.Municipalities.*;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class WayFinder_Controller implements Initializable{
    public Pane pMap;
    public VBox vbMain, vbDetails;
    public ImageView ivTuburan, ivAsturias, ivBalamban, ivToledoCity, ivPinamungajan, ivAloguinsan, ivBarili, ivDumanjug, ivRonda, ivAlcantara, ivMoalboal, ivBadian, ivAlegria, ivMalabuyoc, ivGinatilan, ivSamboan, ivOslob, ivBoljoon, ivAlcoy, ivDalaguete, ivArgao, ivSibonga, ivCarcarCity, ivZamboanga, ivBacolod, ivSantander;
    public Label lTuburan, lAsturias, lBalamban, lToledoCity, lPinamungajan, lAloguinsan, lBarili, lDumanjug, lRonda, lAlcantara, lMoalboal, lBadian, lAlegria, lMalabuyoc, lGinatilan, lSamboan, lOslob, lBoljoon, lAlcoy, lDalaguete, lArgao, lSibonga, lCarcarCity, lZamboanga, lBacolod, lSantander;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Image background = new Image("sample/res/Background.png", screen.getWidth(), screen.getHeight(), false, true);
        vbMain.setBackground(new Background(new BackgroundImage(background, NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT)));
        vbMain.setPrefHeight(screen.getHeight());
        vbMain.setPrefWidth(screen.getWidth());
    }

    @FXML
    public void handleBacolodSelected() {
        handleMunicipalitySelected(Bacolod.getInstance());
    }

    @FXML
    public void handleTuburanSelected() {
        handleMunicipalitySelected(Tuburan.getInstance());
    }

    @FXML
    public void handleAsturiasSelected() {
        handleMunicipalitySelected(Asturias.getInstance());
    }

    @FXML
    public void handleBalambanSelected() {
        handleMunicipalitySelected(Balamban.getInstance());
    }

    public void handleMunicipalitySelected(Municipality municipality) {
        System.out.println(municipality + " selected.");
    }
}
