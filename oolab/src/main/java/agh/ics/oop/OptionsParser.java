package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> convertToDir(String[] directions) throws IllegalArgumentException {
        List<MoveDirection> dirs = new LinkedList<>();

        for (String dir : directions) {
            switch (dir) {
                case "f" -> dirs.add(MoveDirection.FORWARD);
                case "b" -> dirs.add(MoveDirection.BACKWARD);
                case "r" -> dirs.add(MoveDirection.RIGHT);
                case "l" -> dirs.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(dir + " is not legal move specification");
            }
        }
        return dirs;
    }
}
