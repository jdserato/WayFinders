package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        primaryStage.setAlwaysOnTop(true);/*
        Parent newStage = FXMLLoader.load(getClass().getResource("Management.fxml"));
        Scene newScene = new Scene(newStage, 800, 400);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(newScene);
        secondaryStage.show();*/
        primaryStage.show();

        StackPane rooter = new StackPane();

    }

    public class IdleMonitor {
        private final Timeline idleTimeline;
        private final EventHandler<Event> userEventHandler;

        public IdleMonitor(Duration idleTime, Runnable notifier, boolean startMonitoring) {
            idleTimeline = new Timeline(new KeyFrame(idleTime, e -> notifier.run()));
            idleTimeline.setCycleCount(Animation.INDEFINITE);

            userEventHandler = e -> notIdle();

            if (startMonitoring) {
                startMonitoring();
            }
        }

        public void notIdle() {
            if (idleTimeline.getStatus() == Animation.Status.RUNNING) {
                idleTimeline.playFromStart();
            }
        }

        public void startMonitoring() {
            idleTimeline.playFromStart();
        }
    }
}