package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {
    @Test
    public void emptyStringTest() {
        TextMap textMap = new TextMap();

        assertEquals("", textMap.toString());
    }

    @Test
    public void doDirectionsOtherThenForwardAndBackwardAffectMap() {
        TextMap textMap = new TextMap();
        String expectedResult = "str1 str2 str3";

        textMap.place("str1");
        textMap.place("str2");
        textMap.place("str3");

        textMap.move("str1", MoveDirection.LEFT);
        textMap.move("str1", MoveDirection.LEFT);
        textMap.move("str2", MoveDirection.LEFT);
        textMap.move("str3", MoveDirection.RIGHT);

        assertEquals(expectedResult, textMap.toString());
    }

    @Test
    public void isTextMovingLeftRightAndNotExceedingBounds() {
        TextMap textMap = new TextMap();
        String expectedResult = "str2 str3 test str1";

        textMap.place("str1");
        textMap.place("str2");
        textMap.place("str3");
        textMap.move("str1", MoveDirection.FORWARD);
        textMap.move("str1", MoveDirection.FORWARD);
        textMap.move("str1", MoveDirection.FORWARD);
        textMap.move("str1", MoveDirection.FORWARD);
        textMap.move("str2", MoveDirection.FORWARD);
        textMap.place("test");
        textMap.move("test", MoveDirection.BACKWARD);
        textMap.move("str3", MoveDirection.BACKWARD);
        textMap.move("str2", MoveDirection.BACKWARD);

        assertEquals(expectedResult, textMap.toString());
    }
}