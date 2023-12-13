package agh.ics.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SimulationApp extends Application implements Runnable {
    String args = "";

    private void configureStage(Stage primaryStage, VBox viewRoot) {
        var scene = new Scene(viewRoot);
        if (!Objects.equals(args, "")) {
            TextField movesTBox = (TextField) scene.lookup("#movesTBox");
            movesTBox.setText(args);
            Button startBtn = (Button) scene.lookup("#startBtn");
            startBtn.fire();
            Button newSimBtn = (Button) scene.lookup("#newSimBtn");
            startBtn.setVisible(false);
            newSimBtn.setVisible(false);
            movesTBox.setDisable(true);
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        VBox viewRoot;
        try {
            viewRoot = loader.load();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        configureStage(primaryStage, viewRoot);

        primaryStage.show();
    }

    @Override
    public void run() {
        start(new Stage());
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
