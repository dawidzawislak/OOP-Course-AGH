package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AnimalIntegrationTest {
    @Test
    void isAnimalAtPosition() {
        Vector2d position = new Vector2d(2,2);
        Animal animal = new Animal();

        boolean result = animal.isAt(position);

        assertTrue(result);
    }

    @Test
    void isAnimalAtPosition2() {
        Vector2d position = new Vector2d(1,3);
        Animal animal = new Animal(new Vector2d(1,3));

        boolean result = animal.isAt(position);

        assertTrue(result);
    }

    @Test
    void isAnimalLookAtGoodDirection() {
        Animal animal = new Animal(new Vector2d(0,0));
        for(int i = 0; i < 5; i++) {
            animal.move(MoveDirection.LEFT);
        }
        assertEquals(MapDirection.WEST, animal.getOrientation());
    }

    @Test
    void isAnimalLookAtGoodDirection2() {
        Animal animal = new Animal(new Vector2d(0,0));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }

    @Test
    void isAnimalGoingToProperPosition() {
        Animal animal = new Animal(new Vector2d(3,3));
        Vector2d result = new Vector2d(2,4);

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
    }

    @Test
    void isInputGoodInterpreted() {
        String[] args = {"f","f","l","f","sadsa","sdafsd","f","sdafds","f","f","r","b","b","r","f","f","f","l","f","f"};
        Vector2d result = new Vector2d(3, 2);

        List<MoveDirection> moves = OptionsParser.convertToDir(args);
        Animal animal = new Animal(new Vector2d(0,0));
        for(MoveDirection move : moves) {
            animal.move(move);
        }

        assertTrue(animal.isAt(result));
    }

    @Test
    void optionsParserTest() {
        String[] params1 = {"f", "b", "r", "l"};
        MoveDirection[] expectedResult1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        String[] params2 = {"f", "asda", "b", "r", "", "l", "asd"};
        MoveDirection[] expectedResult2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        List<MoveDirection> result1 = OptionsParser.convertToDir(params1);
        List<MoveDirection> result2 = OptionsParser.convertToDir(params2);

        assertArrayEquals(expectedResult1, result1.toArray());
        assertArrayEquals(expectedResult2, result2.toArray());
    }

    @Test
    void integrationTest() {
        Animal animal = new Animal(new Vector2d(0,0));
        Vector2d result = new Vector2d(1,0);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.BACKWARD);
        result = new Vector2d(0,0);
        assertTrue(animal.isAt(result));

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        result = new Vector2d(0,2);
        assertTrue(animal.isAt(result));

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.NORTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.WEST, animal.getOrientation());

        animal.move(MoveDirection.FORWARD);

        result = new Vector2d(0,2);
        assertTrue(animal.isAt(result));

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        result = new Vector2d(2,2);
        assertTrue(animal.isAt(result));

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        result = new Vector2d(4,   2);
        assertTrue(animal.isAt(result));

        String[] args = {"f", "b", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> moves = OptionsParser.convertToDir(args);
        result = new Vector2d(0,   2);

        for(MoveDirection move : moves) {
            animal.move(move);
        }

        assertTrue(animal.isAt(result));
    }
}
