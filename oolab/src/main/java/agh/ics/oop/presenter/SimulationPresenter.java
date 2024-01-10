package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;

    @FXML
    private TextField movesTBox;

    @FXML
    private Label currMoveLbl;

    @FXML
    private GridPane grid;

    public void setWorldMap(WorldMap map) {
        worldMap = map;
    }

    public void drawMap() {
        clearGrid();
        int width =  worldMap.getCurrentBounds().upperRight().getX() - worldMap.getCurrentBounds().lowerLeft().getX() + 2;
        int height =  worldMap.getCurrentBounds().upperRight().getY() - worldMap.getCurrentBounds().lowerLeft().getY() + 2;
        int CELL = min(500/width, 500/height);

        int x = worldMap.getCurrentBounds().lowerLeft().getX();
        int y = worldMap.getCurrentBounds().upperRight().getY();

        int limit = max(height, width);

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                Label label = new Label();

                if (i == 0 && j == 0) {
                    label.setText("y/x");
                    grid.getRowConstraints().add(new RowConstraints(CELL));
                }
                else if (i == 0) {
                    label.setText(""+y);
                    y--;
                }
                else if (j == 0) {
                    label.setText(""+x);
                    x++;
                    grid.getRowConstraints().add(new RowConstraints(CELL));
                }
                else {
                    Optional<WorldElement> object = worldMap.objectAt(new Vector2d(i+worldMap.getCurrentBounds().lowerLeft().getX()-1, worldMap.getCurrentBounds().upperRight().getY()-j+1));
                    object.ifPresent(elem -> label.setText(elem.toString()));
                }
                grid.add(label, i, j);
                GridPane.setHalignment(label, HPos.CENTER);
            }
            grid.getColumnConstraints().add(new ColumnConstraints(CELL));
        }

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            drawMap();
            currMoveLbl.setText(message);
        });
    }

    private void clearGrid() {
        grid.getChildren().retainAll(grid.getChildren().get(0)); // hack to retain visible grid lines
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        String[] args = movesTBox.getText().split(" ");
        currMoveLbl.setText(args[0]);

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<MoveDirection> directions = OptionsParser.convertToDir(args);
        GrassField grassField = new GrassField(10);

        clearGrid();

        int width =  grassField.getCurrentBounds().upperRight().getX() - grassField.getCurrentBounds().lowerLeft().getX();
        int height =  grassField.getCurrentBounds().upperRight().getY() - grassField.getCurrentBounds().lowerLeft().getY();
        int CELL = min(550/width, 550/height);

        grid.getColumnConstraints().add(new ColumnConstraints(CELL));
        grid.getRowConstraints().add(new RowConstraints(CELL));

        grassField.addListener(this);
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new java.util.Date());
        grassField.addListener((worldMap, mes) -> System.out.println(timeStamp + " | " + worldMap.getId() + ": " + mes));
        Simulation simulation = new Simulation(positions, directions, grassField);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsync();
    }

    public void onNewSimulationClicked(ActionEvent actionEvent) {
        SimulationApp app = new SimulationApp(movesTBox.getText());
        Thread appThread = new Thread(app);
        app.run();
    }
}
