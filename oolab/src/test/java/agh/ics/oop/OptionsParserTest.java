package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void isWorkingProperly() {
        String[] params1 = {"f", "b", "r", "l"};
        MoveDirection[] expectedResult1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        List<MoveDirection> result1;
        try {
            result1 = OptionsParser.convertToDir(params1);
            assertArrayEquals(expectedResult1, result1.toArray());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Test
    void checkIfItTrowsException() {
        String[] params2 = {"f", "asda", "b", "r", "", "l", "asd"};
        MoveDirection[] expectedResult2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        String msg = "";
        List<MoveDirection> result2;
        try {
            result2 = OptionsParser.convertToDir(params2);
        }
        catch (IllegalArgumentException e) {
            msg = e.getMessage();
            assertEquals("asda is not legal move specification", msg);
            return;
        }

        assertArrayEquals(expectedResult2, result2.toArray());
    }
}