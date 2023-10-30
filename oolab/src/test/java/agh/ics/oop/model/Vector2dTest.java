package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void toString_test() {
        Vector2d vec1 = new Vector2d(-2, 5);
        String expectedResult = "(-2, 5)";

        assertEquals(expectedResult, vec1.toString());
    }

    @Test
    void equals_test() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(1, 2);
        Vector2d vec3 = new Vector2d(1, -2);
        Vector2d vec4 = null;
        Vector2d vec5 = null;

        assertEquals(vec1, vec1);
        assertEquals(vec1, vec2);
        assertNotEquals(vec1, vec3);
        assertNotEquals(vec1, vec4);
        assertNotEquals(vec4, vec1);
        assertEquals(vec4, vec5);
    }

    @Test
    void precedes() {
        // same point
        Vector2d vec1_1 = new Vector2d(2, 5);
        Vector2d vec2_1 = new Vector2d(2, 5);
        boolean expectedResult1 = true;

        // precedes on 2 coords
        Vector2d vec1_2 = new Vector2d(1, 3);
        Vector2d vec2_2 = new Vector2d(2, 5);
        boolean expectedResult2 = true;

        // precedes on 1 coord
        Vector2d vec1_3 = new Vector2d(0, 5);
        Vector2d vec2_3 = new Vector2d(1, 3);
        boolean expectedResult3 = false;

        // doesn't precede on any coord
        Vector2d vec1_4 = new Vector2d(2, 5);
        Vector2d vec2_4 = new Vector2d(1, 3);
        boolean expectedResult4 = false;

        assertEquals(expectedResult1, vec1_1.precedes(vec2_1));
        assertEquals(expectedResult2, vec1_2.precedes(vec2_2));
        assertEquals(expectedResult3, vec1_3.precedes(vec2_3));
        assertEquals(expectedResult4, vec1_4.precedes(vec2_4));
    }

    @Test
    void follows() {
        // same point
        Vector2d vec1_1 = new Vector2d(2, 5);
        Vector2d vec2_1 = new Vector2d(2, 5);
        boolean expectedResult1 = true;

        // follows on 2 coords
        Vector2d vec1_2 = new Vector2d(2, 5);
        Vector2d vec2_2 = new Vector2d(1, 3);
        boolean expectedResult2 = true;

        // follows on 1 coord
        Vector2d vec1_3 = new Vector2d(0, 5);
        Vector2d vec2_3 = new Vector2d(1, 3);
        boolean expectedResult3 = false;

        // doesn't follow on any coord
        Vector2d vec1_4 = new Vector2d(1, 3);
        Vector2d vec2_4 = new Vector2d(2, 5);
        boolean expectedResult4 = false;

        assertEquals(expectedResult1, vec1_1.follows(vec2_1));
        assertEquals(expectedResult2, vec1_2.follows(vec2_2));
        assertEquals(expectedResult3, vec1_3.follows(vec2_3));
        assertEquals(expectedResult4, vec1_4.follows(vec2_4));
    }

    @Test
    void add() {
        Vector2d vec1 = new Vector2d(2, 5);
        Vector2d vec2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(3, 2);

        Vector2d result = vec1.add(vec2);

        assertEquals(expectedResult, result);
    }

    @Test
    void subtract() {
        Vector2d vec1 = new Vector2d(2, 5);
        Vector2d vec2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(1, 8);

        Vector2d result = vec1.subtract(vec2);

        assertEquals(expectedResult, result);
    }

    @Test
    void upperRight() {
        Vector2d vec1_1 = new Vector2d(2, 5);
        Vector2d vec2_1 = new Vector2d(1, -3);
        Vector2d expectedResult1 = new Vector2d(2, 5);

        Vector2d vec1_2 = new Vector2d(2, 5);
        Vector2d vec2_2 = new Vector2d(1, 10);
        Vector2d expectedResult2 = new Vector2d(2, 10);

        Vector2d result1 = vec1_1.upperRight(vec2_1);
        Vector2d result2 = vec1_2.upperRight(vec2_2);

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }

    @Test
    void lowerLeft() {
        Vector2d vec1_1 = new Vector2d(2, 5);
        Vector2d vec2_1 = new Vector2d(1, -3);
        Vector2d expectedResult1 = new Vector2d(1, -3);

        Vector2d vec1_2 = new Vector2d(2, 5);
        Vector2d vec2_2 = new Vector2d(1, 10);
        Vector2d expectedResult2 = new Vector2d(1, 5);

        Vector2d result1 = vec1_1.lowerLeft(vec2_1);
        Vector2d result2 = vec1_2.lowerLeft(vec2_2);

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }

    @Test
    void opposite() {
        Vector2d vec1 = new Vector2d(2, 5);
        Vector2d expectedResult1 = new Vector2d(-2, -5);
        Vector2d vec2 = new Vector2d(0, 0);
        Vector2d expectedResult2 = new Vector2d(0, 0);

        Vector2d result1 = vec1.opposite();
        Vector2d result2 = vec2.opposite();

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }
}