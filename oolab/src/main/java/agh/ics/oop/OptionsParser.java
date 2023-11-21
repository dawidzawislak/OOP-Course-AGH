package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] convertToDir(String[] directions) {
        ArrayList<MoveDirection> dirs = new ArrayList<MoveDirection>();

        for (String dir : directions) {
            switch (dir) {
                case "f" -> dirs.add(MoveDirection.FORWARD);
                case "b" -> dirs.add(MoveDirection.BACKWARD);
                case "r" -> dirs.add(MoveDirection.RIGHT);
                case "l" -> dirs.add(MoveDirection.LEFT);
            }
        }
        return dirs.toArray(new MoveDirection[0]);
    }
}
