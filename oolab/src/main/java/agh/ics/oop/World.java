package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        run(args);

        System.out.println("system zakończył działanie");
    }

    private static void run(String[] directions) {
        MoveDirection[] dirs = OptionsParser.convertToDir(directions);

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
