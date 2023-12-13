package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
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
        Simulation simulation = new Simulation(positions, directions, grassField);
        simulation.run();

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
