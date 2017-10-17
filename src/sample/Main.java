package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SBT Directory System");
        primaryStage.setFullScreen(true);
        primaryStage.setAlwaysOnTop(true);
        Scene scene = new Scene(root, 768, 617);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
