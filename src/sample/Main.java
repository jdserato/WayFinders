package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("WayFinders.fxml"));
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screen.getWidth(), screen.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("SBT Directory System");
        primaryStage.setFullScreen(true);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();/*
        Parent newStage = FXMLLoader.load(getClass().getResource("Management.fxml"));
        Scene newScene = new Scene(newStage, 400, 400);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(newScene);
        secondaryStage.show();*/
    }
}