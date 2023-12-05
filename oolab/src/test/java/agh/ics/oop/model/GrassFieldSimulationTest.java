package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldSimulationTest {
    @Test
    public void isGoodAmountOfGrassSpawn() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();

        Simulation simulation = new Simulation(positions,moves,new GrassField(20));
        simulation.run();
        String x= simulation.toString();
        int result=0;
        for(char c : simulation.toString().toCharArray()) {
            if(c=='*') result++;
        }

        assertEquals(20, result);
    }
    @Test
    public void isAnimalCoveringGrassOnVisualizer() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();

        //for map with 20 grasses we get map size of 15x15, so we can add animal on every field and check whether every grass is covered by animal
        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                positions.add(new Vector2d(i,j));
            }
        }
        Simulation simulation = new Simulation(positions,moves,new GrassField(20));
        simulation.run();

        int result=0;
        for(char c : simulation.toString().toCharArray()) {
            if (c == '*') result++;
        }

        assertEquals(0, result);
    }

    @Test
    public void isMapExtendingProperlyWhenPlacingAnimal() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        positions.add(new Vector2d(20,15));
        GrassField map = new GrassField(20);

        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        assertEquals(new Vector2d(21,16), map.getRightUp());
    }
    @Test
    public void isMapExtendingProperlyWhenAnimalMoving() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        GrassField map = new GrassField(20);

        positions.add(new Vector2d(15,15));
        moves.add(MoveDirection.RIGHT);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.LEFT);
        moves.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions,moves,map);

        simulation.run();

        assertEquals(new Vector2d(19,17), map.getRightUp());
    }
    @Test
    public void isMapShrinkingProperly() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        positions.add(new Vector2d(20,15));
        GrassField map = new GrassField(20);

        moves.add(MoveDirection.LEFT);
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions,moves,map);

        simulation.run();

        assertEquals(new Vector2d(19,16), map.getRightUp());
    }
    @Test
    public void placingMultipleAnimalsOnSamePosition() {
        List<Vector2d> positions = new ArrayList<>();
        List<MoveDirection> moves = new ArrayList<>();
        GrassField map = new GrassField(5);

        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(0, 5));
        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        int numberOfAnimals = 0;
        for(char x:simulation.toString().toCharArray())
        {
            if(x=='^'||x=='>'||x=='<'||x=='v')
                numberOfAnimals++;
        }
        assertEquals(2, numberOfAnimals);
    }

    @Test
    void areAnimalsColliding() {
        List<Vector2d> positions = new ArrayList<>();
        String[] args = {"f", "r", "r", "r", "f", "r", "l", "r", "f", "r", "r", "r", "f", "r", "r", "r", "f"};
        List<MoveDirection> moves = OptionsParser.convertToDir(args);

        GrassField map = new GrassField(20);
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(1, 1));

        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        for(WorldElement worldElement: map.getElements()) {
            if(worldElement.getPosition().equals(new Vector2d(1,1)))
                assertEquals("^", worldElement.toString());
            if(worldElement.getPosition().equals(new Vector2d(1,2)))
                assertEquals("v", worldElement.toString());
        }
    }
}
