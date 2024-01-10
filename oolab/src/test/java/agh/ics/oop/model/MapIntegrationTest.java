package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MapIntegrationTest {

    @Test
    public void canAnimalBePlacedOutOfMap() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(new Vector2d(5, 5));

        boolean res = true;

        try {
            map.place(animal);
        }
        catch(PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
            res = false;
        }

        assertFalse(res);
    }

    @Test
    public void canAnimalBePlacedOnTopOfOtherAnimal() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        boolean res1 = true;
        boolean res2 = true;

        try {
            map.place(animal1);
        }
        catch(PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
            res1 = false;
        }

        try {
            map.place(animal2);
        }
        catch(PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
            res2 = false;
        }

        Optional<WorldElement> placedAnimal = map.objectAt(new Vector2d(2, 2));

        assertTrue(res1);
        assertFalse(res2);
        assertTrue(placedAnimal.isPresent());
        assertEquals(animal1, placedAnimal.get());
    }

    @Test
    public void canAnimalBePlacedOnTopOfOtherAnimalSimulationVersion() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        RectangularMap map = new RectangularMap(5, 5);
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 5));
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.BACKWARD);
        moves.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();
        String ExpectedMap = """
                 y\\x  0 1 2 3 4 5\r
                  6: -------------\r
                  5: | | | | | | |\r
                  4: |^| | | | | |\r
                  3: | | | | | | |\r
                  2: |^| | | | | |\r
                  1: | | | | | | |\r
                  0: | | | | | | |\r
                 -1: -------------\r
                """;
        assertEquals(ExpectedMap, simulation.toString());
    }

    @Test
    public void isSimulationWorkingWithoutAnimals() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        RectangularMap map = new RectangularMap(5, 5);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.BACKWARD);
        moves.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();
        String ExpectedMap = """
                 y\\x  0 1 2 3 4 5\r
                  6: -------------\r
                  5: | | | | | | |\r
                  4: | | | | | | |\r
                  3: | | | | | | |\r
                  2: | | | | | | |\r
                  1: | | | | | | |\r
                  0: | | | | | | |\r
                 -1: -------------\r
                """;
        assertEquals(simulation.toString(), ExpectedMap);
    }

    @Test
    public void canAnimalWalkIntoAnotherAnimal() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));

        try {
            map.place(animal1);
            map.place(animal2);
            map.move(animal1, MoveDirection.FORWARD);
        }
        catch(PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(new Vector2d(2, 2), animal1.getPosition());
    }

    @Test
    public void canAnimalWalkOutOfMap() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal);
            map.move(animal, MoveDirection.FORWARD);
            map.move(animal, MoveDirection.FORWARD);
            map.move(animal, MoveDirection.FORWARD);
            map.move(animal, MoveDirection.FORWARD);
        }
        catch(PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
        Optional<WorldElement> elem = map.objectAt(new Vector2d(2, 4));
        assertTrue(elem.isPresent());
        assertEquals(animal, elem.get());
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    void isAnimalWalkingOutOfMapSimulationVersion() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        RectangularMap map = new RectangularMap(4, 4);
        positions.add(new Vector2d(2, 2));

        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        String ExpectedMap = """
                y\\x  0 1 2 3 4\r
                 5: -----------\r
                 4: | | |^| | |\r
                 3: | | | | | |\r
                 2: | | | | | |\r
                 1: | | | | | |\r
                 0: | | | | | |\r
                -1: -----------\r
               """;
        assertEquals(ExpectedMap, simulation.toString());
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
