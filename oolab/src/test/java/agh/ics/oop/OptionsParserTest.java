package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void convertToDir() {
        String[] params1 = {"f", "b", "r", "l"};
        MoveDirection[] expectedResult1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        String[] params2 = {"f", "asda", "b", "r", "", "l", "asd"};
        MoveDirection[] expectedResult2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        List<MoveDirection> result1 = OptionsParser.convertToDir(params1);
        List<MoveDirection> result2 = OptionsParser.convertToDir(params2);

        assertArrayEquals(expectedResult1, result1.toArray());
        assertArrayEquals(expectedResult2, result2.toArray());
    }
}