package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import sample.Municipalities.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

/**
 * Created by Serato, Jay Vince on November 01, 2017.
 */
public class WayFinder_Controller implements Initializable{
    public Pane pMap;
    public VBox vbMain, vbDetails;
    public TableView<Bus> tvBusDetails;
    public TableColumn<Bus, String> tcBusCompany, tcBusType, tccLocation, tcWingArea, tcBayNumber, tcDestination, tcLastTrip, tcFirstTrip, tcBuses, tcMaxFare;
    public TextField taNumberOfBusCompanies, taNumberOfBuses, taFareAircon, taFareOrdinary;
    public ImageView ivTuburan, ivAsturias, ivBalamban, ivToledoCity, ivPinamungajan, ivAloguinsan, ivBarili, ivDumanjug, ivRonda, ivAlcantara, ivMoalboal, ivBadian, ivAlegria, ivMalabuyoc, ivGinatilan, ivSamboan, ivOslob, ivBoljoon, ivAlcoy, ivDalaguete, ivArgao, ivSibonga, ivCarcarCity, ivZamboanga, ivBacolod, ivSantander, ivDumaguete;
    public Label lTuburan, lAsturias, lBalamban, lToledoCity, lPinamungajan, lAloguinsan, lBarili, lDumanjug, lRonda, lAlcantara, lMoalboal, lBadian, lAlegria, lMalabuyoc, lGinatilan, lSamboan, lOslob, lBoljoon, lAlcoy, lDalaguete, lArgao, lSibonga, lCarcarCity, lZamboanga, lBacolod, lSantander, lDumaguete;
    public ImageView ivBackToMap, ivPrev, ivNext, ivTerminalPath;
    public Label lBackToMap, lDestination, lPrev, lNext;

    private ObservableList<Municipality> municipalities = FXCollections.observableArrayList(AloguinsanVP.getInstance(), DumanjugVB.getInstance(), PinamungajanVA.getInstance(), SamboanVO.getInstance(), SamboanVB.getInstance(), Bacolod.getInstance(), Zamboanga.getInstance(), Dumaguete.getInstance(), Tuburan.getInstance(), Balamban.getInstance(), Asturias.getInstance(), Balamban.getInstance(), ToledoCity.getInstance(), Pinamungajan.getInstance(), Aloguinsan.getInstance(), Barili.getInstance(), Dumanjug.getInstance(), Ronda.getInstance(), Alcantara.getInstance(), Moalboal.getInstance(), Badian.getInstance(), Alegria.getInstance(), Malabuyoc.getInstance(), Ginatilan.getInstance(), Samboan.getInstance(), Santander.getInstance(), Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance());

    private ObservableList<Bus> qualifier = FXCollections.observableArrayList();
    private ObservableList<Bus> buses = FXCollections.observableArrayList();/*FXCollections.observableArrayList(
            new Bus(1, "JEGANS", "Ordinary", Pinamungajan.getInstance(), "4:00 am", "4:00 pm", 4, 5, 70, "Right Wing"), // TODO assure, deleted Toledo
            new Bus(2, "CALVO", "Ordinary", ToledoCity.getInstance(), "3:00 am", "9:00 pm", 4, 32, 70, "Right Wing"), // TODO assure, deleted Balamban
            new Bus(3, "COROMINAS", "Ordinary", Tuburan.getInstance(), "3:00 am", "6:00 pm", 3, 20, 90, "Right Wing"), // TODO assure, deleted Balamban/Asturias/Toledo
            new Bus(4, "CERES LINER", "Aircon/Ordinary", Pinamungajan.getInstance(), "4:00 am", "7:00 pm", 4, 7, 75, "Right Wing"), // TODO assure, deleted Balamban
            new Bus(4, "CERES LINER", "Aircon/Ordinary", ToledoCity.getInstance(), "4:00 am", "7:00 pm", 4, 2, 60, "Right Wing"),
            new Bus(5, "GABE TRANSIT", "Ordinary", ToledoCity.getInstance(), "5:00 am", "9:00 pm", 4, 8, 60, "Right Wing"), // TODO assure, deleted Balamban
            new Bus(6, "CANONEO", "Ordinary", ToledoCity.getInstance(), "9:00 am", "8:00 pm", 4, 5, 60, "Right Wing"),
            new Bus(1, "JRK / SEPO", "Ordinary", PinamungajanVA.getInstance(), "9:00 am", "6:00 pm", 1, 8, 70, "Center Wing"),
            new Bus(2, "CERES LINER", "Aircon", SamboanVB.getInstance(), "4:00 am", "6:00 pm", 3, 12, 85, "Center Wing"),
            new Bus(3, "CERES LINER", "Ordinary", SamboanVB.getInstance(), "1:00 am", "10:00 pm", 3, 38, 72, "Center Wing"),
            new Bus(4, "CERES LINER", "Ordinary", Moalboal.getInstance(), "-", "-", 4, 4, 107, "Center Wing"),
            new Bus(4, "CERES LINER", "Ordinary", CarcarCity.getInstance(), "-", "-", 7, 13, 40, "Center Wing"),
            new Bus(5, "CERES LINER", "Ordinary", Argao.getInstance(), "-", "-", 6, 15, 79, "Center Wing"),
            new Bus(6, "CERES LINER", "Ordinary", Alcoy.getInstance(), "-", "-", 4, 4, 100, "Center Wing"),
            new Bus(7, "CERES LINER", "Ordinary", SamboanVO.getInstance(), "-", "-", 3, 27, 138, "Center Wing"),
            new Bus(8, "CERES LINER", "Aircon", Argao.getInstance(), "7:00 am", "12:00 am", 5, 5, 83, "Center Wing"),
            new Bus(9, "CERES LINER", "Aircon", SamboanVO.getInstance(), "1:00 am", "12:00 am", 5, 5, 145, "Center Wing"), // TODO assure, deleted Oslob, Alcoy
            new Bus(10, "CERES LINER", "Aircon", Dumaguete.getInstance(), "6:00 am", "6:00 pm", 1, 9, 275, "Center Wing"),
            new Bus(11, "CERES LINER", "Aircon", Bacolod.getInstance(), "1:00 am", "-", 1, 2, 560, "Center Wing"),
            new Bus(11, "CERES LINER", "Aircon", Zamboanga.getInstance(), "2:00 pm", "-", 1, 1, 560, "Center Wing"),
            new Bus(12, "JHADE", "Ordinary", AloguinsanVP.getInstance(), "4:30 am", "5:00 pm", 2, 6, 80, "Center Wing"),
            new Bus(2, "METROLINK [Weekdays]", "Ordinary", SamboanVB.getInstance(), "4:00 am", "10:00 pm", 1, 10, 65, "Left Wing"), // TODO assure, deleted Barili
            new Bus(3, "METROLINK [Weekends]", "Ordinary", SamboanVB.getInstance(), "4:00 am", "10:00 pm", 2, 10, 65, "Left Wing"),
            new Bus(4, "SUNRAYS", "Aircon/Ordinary", SamboanVO.getInstance(), "4:00 am", "8:30 pm", 2, 34, 143, "Left Wing"), // TODO assure, deleted Oslob
            new Bus(5, "SUNRAYS", "Aircon/Ordinary", Samboan.getInstance(), "4:00 am", "8:30 pm", 2, 34, 143, "Left Wing"),
            new Bus(6, "CHAN TRANSIT", "Ordinary", Dumanjug.getInstance(), "5:00 am", "11:00 pm", 2, 22, 75, "Left Wing"),
            new Bus(7, "JBH/BRITT", "Ordinary", DumanjugVB.getInstance(), "6:00 am", "10:00 pm", 2, 9, 75, "Left Wing"), // TODO assure, deleted Barili
            new Bus(7, "SOCORRO", "Ordinary", Dumanjug.getInstance(), "6:00 am", "6:00 pm", 3, 5, 75, "Left Wing"),
            new Bus(8, "BEFEL LINER", "Ordinary", CarcarCity.getInstance(), "6:00 am", "6:00 pm", 6, 1, 60, "Left Wing"),
            new Bus(8, "DOS HERMANOS", "Ordinary", CarcarCity.getInstance(), "6:00 am", "8:00 pm", 6, 3, 60, "Left Wing"),
            new Bus(8, "INDAY JEAN", "Ordinary", CarcarCity.getInstance(), "5:00 am", "7:00 pm", 4, 1, 60, "Left Wing"),
            new Bus(9, "ALLOWIE", "Ordinary", Sibonga.getInstance(), "6:00 am", "8:00 pm", 4, 5, 70, "Left Wing"),
            new Bus(9, "ANDREW LINER", "Ordinary", Sibonga.getInstance(), "5:00 am", "6:00 pm", 4, 2, 70, "Left Wing"),
            new Bus(9, "EDC", "Ordinary", Sibonga.getInstance(), "7:00 am", "7:00 pm", 2, 9, 70, "Left Wing"),
            new Bus(9, "JRK/SEPO", "Ordinary", Sibonga.getInstance(), "5:00 am", "8:00 pm", 2, 14, 70, "Left Wing"),
            new Bus(9, "PIONEER", "Ordinary", Sibonga.getInstance(), "4:00 am", "8:00 pm", 4, 10, 70, "Left Wing"),
            new Bus(9, "SKIPPER", "Ordinary", Sibonga.getInstance(), "5:00 am", "6:00 pm", 4, 3, 70, "Left Wing"),
            new Bus(9, "YEOMAN", "Ordinary", Sibonga.getInstance(), "5:00 am", "7:00 pm", 4, 10, 70, "Left Wing")
    );*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO must initialize things here

        Alcantara.getInstance().setEncompassingMunicipality(new Municipality[]{CarcarCity.getInstance(), Sibonga.getInstance(), Argao.getInstance()});
        Alcantara.getInstance().setLeftMun(Ronda.getInstance());
        Alcantara.getInstance().setRightMun(Moalboal.getInstance());
        Alcoy.getInstance().setEncompassingMunicipality(new Municipality[]{Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Alcoy.getInstance().setLeftMun(Boljoon.getInstance());
        Alcoy.getInstance().setRightMun(Dalaguete.getInstance());
        Alegria.getInstance().setEncompassingMunicipality(new Municipality[]{Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Alegria.getInstance().setLeftMun(Badian.getInstance());
        Alegria.getInstance().setRightMun(Malabuyoc.getInstance());
        Aloguinsan.getInstance().setEncompassingMunicipality(new Municipality[]{CarcarCity.getInstance()});
        Aloguinsan.getInstance().setLeftMun(Pinamungajan.getInstance());
        Aloguinsan.getInstance().setRightMun(Barili.getInstance());
        AloguinsanVP.getInstance().setEncompassingMunicipality(new Municipality[]{ToledoCity.getInstance(), Pinamungajan.getInstance()});
        AloguinsanVP.getInstance().setLeftMun(Pinamungajan.getInstance());
        AloguinsanVP.getInstance().setRightMun(Barili.getInstance());
        Argao.getInstance().setEncompassingMunicipality(new Municipality[]{Sibonga.getInstance(), CarcarCity.getInstance()});
        Argao.getInstance().setLeftMun(Dalaguete.getInstance());
        Argao.getInstance().setRightMun(Sibonga.getInstance());
        Asturias.getInstance().setEncompassingMunicipality(new Municipality[]{Balamban.getInstance()});
        Asturias.getInstance().setLeftMun(Tuburan.getInstance());
        Asturias.getInstance().setRightMun(Balamban.getInstance());
        Bacolod.getInstance().setLeftMun(Dumaguete.getInstance());
        Bacolod.getInstance().setRightMun(Tuburan.getInstance());
        Badian.getInstance().setEncompassingMunicipality(new Municipality[]{Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Badian.getInstance().setLeftMun(Moalboal.getInstance());
        Badian.getInstance().setRightMun(Alegria.getInstance());
        Balamban.getInstance().setLeftMun(Asturias.getInstance());
        Balamban.getInstance().setRightMun(ToledoCity.getInstance());
        Barili.getInstance().setEncompassingMunicipality(new Municipality[]{CarcarCity.getInstance()});
        Barili.getInstance().setLeftMun(Aloguinsan.getInstance());
        Barili.getInstance().setRightMun(Dumanjug.getInstance());
        Boljoon.getInstance().setEncompassingMunicipality(new Municipality[]{Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Boljoon.getInstance().setLeftMun(Oslob.getInstance());
        Boljoon.getInstance().setRightMun(Alcoy.getInstance());
        CarcarCity.getInstance().setLeftMun(Sibonga.getInstance());
        CarcarCity.getInstance().setRightMun(Zamboanga.getInstance());
        Dalaguete.getInstance().setEncompassingMunicipality(new Municipality[]{Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Dalaguete.getInstance().setLeftMun(Alcoy.getInstance());
        Dalaguete.getInstance().setRightMun(Argao.getInstance());
        Dumaguete.getInstance().setLeftMun(Zamboanga.getInstance());
        Dumaguete.getInstance().setRightMun(Bacolod.getInstance());
        Dumanjug.getInstance().setEncompassingMunicipality(new Municipality[]{Sibonga.getInstance(), CarcarCity.getInstance()});
        Dumanjug.getInstance().setLeftMun(Barili.getInstance());
        Dumanjug.getInstance().setRightMun(Ronda.getInstance());
        DumanjugVB.getInstance().setEncompassingMunicipality(new Municipality[]{Barili.getInstance(), CarcarCity.getInstance()});
        Ginatilan.getInstance().setEncompassingMunicipality(new Municipality[]{Malabuyoc.getInstance(), Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Ginatilan.getInstance().setLeftMun(Malabuyoc.getInstance());
        Ginatilan.getInstance().setRightMun(Samboan.getInstance());
        Malabuyoc.getInstance().setEncompassingMunicipality(new Municipality[]{Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Malabuyoc.getInstance().setLeftMun(Alegria.getInstance());
        Malabuyoc.getInstance().setRightMun(Ginatilan.getInstance());
        Moalboal.getInstance().setEncompassingMunicipality(new Municipality[]{Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Moalboal.getInstance().setLeftMun(Alcantara.getInstance());
        Moalboal.getInstance().setRightMun(Badian.getInstance());
        Oslob.getInstance().setEncompassingMunicipality(new Municipality[]{Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Oslob.getInstance().setLeftMun(Santander.getInstance());
        Oslob.getInstance().setRightMun(Boljoon.getInstance());
        Pinamungajan.getInstance().setEncompassingMunicipality(new Municipality[]{ToledoCity.getInstance()});
        Pinamungajan.getInstance().setLeftMun(ToledoCity.getInstance());
        Pinamungajan.getInstance().setRightMun(Aloguinsan.getInstance());
        PinamungajanVA.getInstance().setEncompassingMunicipality(new Municipality[]{Aloguinsan.getInstance(), CarcarCity.getInstance()});
        Ronda.getInstance().setEncompassingMunicipality(new Municipality[]{Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Ronda.getInstance().setLeftMun(Dumanjug.getInstance());
        Ronda.getInstance().setRightMun(Moalboal.getInstance());
        Samboan.getInstance().setEncompassingMunicipality(new Municipality[]{Ginatilan.getInstance(), Malabuyoc.getInstance(), Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        Samboan.getInstance().setLeftMun(Ginatilan.getInstance());
        Samboan.getInstance().setRightMun(Santander.getInstance());
        SamboanVB.getInstance().setEncompassingMunicipality(new Municipality[]{Ginatilan.getInstance(), Malabuyoc.getInstance(), Alegria.getInstance(), Badian.getInstance(), Moalboal.getInstance(), Alcantara.getInstance(), Ronda.getInstance(), Dumanjug.getInstance(), Barili.getInstance(), CarcarCity.getInstance()});
        SamboanVO.getInstance().setEncompassingMunicipality(new Municipality[]{Santander.getInstance(), Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Santander.getInstance().setEncompassingMunicipality(new Municipality[]{Oslob.getInstance(), Boljoon.getInstance(), Alcoy.getInstance(), Dalaguete.getInstance(), Argao.getInstance(), Sibonga.getInstance(), CarcarCity.getInstance()});
        Santander.getInstance().setLeftMun(Samboan.getInstance());
        Santander.getInstance().setRightMun(Oslob.getInstance());
        Sibonga.getInstance().setEncompassingMunicipality(new Municipality[]{CarcarCity.getInstance()});
        Sibonga.getInstance().setLeftMun(Argao.getInstance());
        Sibonga.getInstance().setRightMun(CarcarCity.getInstance());
        ToledoCity.getInstance().setLeftMun(Balamban.getInstance());
        ToledoCity.getInstance().setRightMun(Pinamungajan.getInstance());
        Tuburan.getInstance().setEncompassingMunicipality(new Municipality[]{Asturias.getInstance(), Balamban.getInstance()});
        Tuburan.getInstance().setLeftMun(Bacolod.getInstance());
        Tuburan.getInstance().setRightMun(Asturias.getInstance());
        Zamboanga.getInstance().setLeftMun(CarcarCity.getInstance());
        Zamboanga.getInstance().setRightMun(Dumaguete.getInstance());

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Image background = new Image("sample/res/Background.png", screen.getWidth(), screen.getHeight(), false, true);
        vbMain.setBackground(new Background(new BackgroundImage(background, NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT)));
        vbMain.setPrefHeight(screen.getHeight());
        vbMain.setPrefWidth(screen.getWidth());
        
        String sql = "SELECT *" +
                     "FROM bus_info";/* +
                     "WHERE wing_area=?";*/

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
                buses.add(new Bus(rs.getInt("bay_num"), rs.getString("company"), rs.getString("type"), thisMunicipality, rs.getString("first_departure"), rs.getString("last_trip"), rs.getInt("no_of_trips"), rs.getInt("no_of_buses"), rs.getInt("fare"), rs.getString("wing_area")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        tcBusCompany.setCellValueFactory(new PropertyValueFactory<>("busCompany"));
        tcBusType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcWingArea.setCellValueFactory(new PropertyValueFactory<>("wingArea"));
        tcBayNumber.setCellValueFactory(new PropertyValueFactory<>("bayNumber"));
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tcFirstTrip.setCellValueFactory(new PropertyValueFactory<>("departure"));
        tcLastTrip.setCellValueFactory(new PropertyValueFactory<>("lastTrip"));
        tcBuses.setCellValueFactory(new PropertyValueFactory<>("buses"));
        tcMaxFare.setCellValueFactory(new PropertyValueFactory<>("fares"));

        tvBusDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bus>() {
            @Override
            public void changed(ObservableValue<? extends Bus> observable, Bus oldValue, Bus newValue) {
                String ivPath = "sample/res/Terminal Map/";
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
                ivPath = ivPath.concat(newValue.getBayNumber() + ".png");
                ivTerminalPath.setImage(new Image(ivPath));
            }
        });
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

    @FXML
    public void handleToledoCitySelected() {
        handleMunicipalitySelected(ToledoCity.getInstance());
    }

    @FXML
    public void handlePinamungajanSelected() {
        handleMunicipalitySelected(Pinamungajan.getInstance());
    }

    @FXML
    public void handleAloguinsanSelected() {
        handleMunicipalitySelected(Aloguinsan.getInstance());
    }

    @FXML
    public void handleBariliSelected() {
        handleMunicipalitySelected(Barili.getInstance());
    }

    @FXML
    public void handleDumanjugSelected() {
        handleMunicipalitySelected(Dumanjug.getInstance());
    }

    @FXML
    public void handleRondaSelected() {
        handleMunicipalitySelected(Ronda.getInstance());
    }

    @FXML
    public void handleAlcantaraSelected() {
        handleMunicipalitySelected(Alcantara.getInstance());
    }

    @FXML
    public void handleMoalboalSelected() {
        handleMunicipalitySelected(Moalboal.getInstance());
    }

    @FXML
    public void handleBadianSelected() {
        handleMunicipalitySelected(Badian.getInstance());
    }

    @FXML
    public void handleAlegriaSelected() {
        handleMunicipalitySelected(Alegria.getInstance());
    }

    @FXML
    public void handleMalabuyocSelected() {
        handleMunicipalitySelected(Malabuyoc.getInstance());
    }

    @FXML
    public void handleGinatilanSelected() {
        handleMunicipalitySelected(Ginatilan.getInstance());
    }

    @FXML
    public void handleSamboanSelected() {
        handleMunicipalitySelected(Samboan.getInstance());
    }

    @FXML
    public void handleSantanderSelected() {
        handleMunicipalitySelected(Santander.getInstance());
    }

    @FXML
    public void handleOslobSelected() {
        handleMunicipalitySelected(Oslob.getInstance());
    }

    @FXML
    public void handleBoljoonSelected() {
        handleMunicipalitySelected(Boljoon.getInstance());
    }

    @FXML
    public void handleAlcoySelected() {
        handleMunicipalitySelected(Alcoy.getInstance());
    }

    @FXML
    public void handleDalagueteSelected() {
        handleMunicipalitySelected(Dalaguete.getInstance());
    }

    @FXML
    public void handleArgaoSelected() {
        handleMunicipalitySelected(Argao.getInstance());
    }

    @FXML
    public void handleSibongaSelected() {
        handleMunicipalitySelected(Sibonga.getInstance());
    }

    @FXML
    public void handleCarcarCitySelected() {
        handleMunicipalitySelected(CarcarCity.getInstance());
    }

    @FXML
    public void handleDumagueteSelected() {
        handleMunicipalitySelected(Dumaguete.getInstance());
    }

    @FXML
    public void handleZamboangaSelected() {
        handleMunicipalitySelected(Zamboanga.getInstance());
    }

    private void handleMunicipalitySelected(Municipality municipality) {
        pMap.setVisible(false);
        vbDetails.setVisible(true);

        lDestination.setText(municipality.toString());
        lPrev.setText(municipality.getLeftMun().toString());
        lNext.setText(municipality.getRightMun().toString());

        qualifier.clear();
        for (Bus bus : buses) {
            Municipality destination = bus.getDestination();
            System.out.println(bus.getDestination());
            if (destination.toString().equalsIgnoreCase(municipality.toString())) {
                qualifier.add(bus);
            } else if (destination.getEncompassingMunicipality() != null) {
                for (Municipality m : destination.getEncompassingMunicipality()) {
                    if (m.toString().equalsIgnoreCase(municipality.toString())) {
                        qualifier.add(bus);
                    }
                }
            }
        }
        tvBusDetails.setItems(qualifier);

        String ivPath = "sample/res/Terminal Map/";
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
        ivPath = ivPath.concat(qualifier.get(0).getBayNumber() + ".png");
        ivTerminalPath.setImage(new Image(ivPath));

        taNumberOfBusCompanies.setText(qualifier.size() + "");
        int busNumber = 0;
        for (Bus bus : qualifier) {
            busNumber += bus.getBuses();
        }
        taNumberOfBuses.setText(busNumber + "");
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