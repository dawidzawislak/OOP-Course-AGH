package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> convertToDir(String[] directions) throws IllegalArgumentException {
        return Stream.of(directions).map(dir -> {return switch (dir) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "r" -> MoveDirection.RIGHT;
            case "l" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(dir + " is not legal move specification");
        };}).collect(Collectors.toList());
    }
}
