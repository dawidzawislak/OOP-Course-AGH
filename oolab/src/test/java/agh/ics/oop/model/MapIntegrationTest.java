package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapIntegrationTest {

    @Test
    public void canAnimalBePlacedOutOfMap() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(new Vector2d(5, 5));

        boolean res = map.place(animal);

        assertFalse(res);
    }

    @Test
    public void canAnimalBePlacedOnTopOfOtherAnimal() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        boolean res1 = map.place(animal1);
        boolean res2 = map.place(animal2);

        Animal placedAnimal = map.objectAt(new Vector2d(2, 2));

        assertTrue(res1);
        assertFalse(res2);
        assertEquals(animal1, placedAnimal);
    }

    @Test
    public void canAnimalWalkIntoAnotherAnimal() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));

        map.place(animal1);
        map.place(animal2);

        map.move(animal1, MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 2), animal1.getPosition());
    }

    @Test
    public void canAnimalWalkOutOfMap() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(new Vector2d(2, 2));

        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);

        assertEquals(animal, map.objectAt(new Vector2d(2, 4)));
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    public void integrationTest() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(new Vector2d(4, 2));

        String[] args = {"l", "f", "b", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> moves = OptionsParser.convertToDir(args);
        Vector2d result = new Vector2d(0, 2);

        for(MoveDirection move : moves) {
            animal.move(move);
        }

        assertTrue(animal.isAt(result));
    }

}
