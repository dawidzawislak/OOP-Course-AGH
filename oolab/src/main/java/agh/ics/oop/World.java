package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("system wystartował");

        List<MoveDirection> directions;
        try {
            directions = OptionsParser.convertToDir(args);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField grassField = new GrassField(10);
        grassField.addListener(new ConsoleMapDisplay());
        RectangularMap rectangularMap = new RectangularMap(10, 10);
        rectangularMap.addListener(new ConsoleMapDisplay());
        Simulation simulation1 = new Simulation(positions, directions, rectangularMap);
        Simulation simulation2 = new Simulation(positions, directions, grassField);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation1, simulation2));
        simulationEngine.runAsyncInThreadPool();


        // Race condition testing code
        /*
        List<Simulation> simulations = new LinkedList<>();

        for (int i = 0; i < 10000; i++) {
            GrassField gf = new GrassField(10);
            gf.addListener(new ConsoleMapDisplay());
            RectangularMap rm = new RectangularMap(10, 10);
            rm.addListener(new ConsoleMapDisplay());
            simulations.add(new Simulation(positions, directions, rm));
            simulations.add(new Simulation(positions, directions, gf));
        }
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
        */
        System.out.println("system zakończył działanie");
    }

    private static void run(String[] directions) {
        List<MoveDirection> dirs = OptionsParser.convertToDir(directions);

        for (MoveDirection dir : dirs) {
            switch (dir) {
                case FORWARD -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case LEFT -> System.out.println("zwierzak idzie w lewo");
                case RIGHT -> System.out.println("zwierzak idzie w prawo");
            }
        }
    }
}
