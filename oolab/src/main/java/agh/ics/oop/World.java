package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        MoveDirection[] dirs = OptionsParser.convertToDir(args);
        run(dirs);

        System.out.println("system zakończył działanie");
    }

    private static void run(MoveDirection[] dirs) {
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
