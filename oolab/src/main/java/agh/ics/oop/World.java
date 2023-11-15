package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        List<MoveDirection> directions = OptionsParser.convertToDir(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
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
