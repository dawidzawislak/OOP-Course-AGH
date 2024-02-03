package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    private final WorldMap worldMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap worldMap) {
        animals = new ArrayList<>();

        for(Vector2d pos : positions) {
            Animal animal = new Animal(pos);
            try {
                worldMap.place(animal);
                animals.add(animal);
            }
            catch(PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());
            }
        }
        this.worldMap = worldMap;
        this.moves = moves;
    }

    public void run() {
        int animalIndex = 0;
        int animalCount = animals.size();
        for (MoveDirection move : moves) {
            if (animalIndex > animalCount - 1) break;
            Animal animal = animals.get(animalIndex);

            try {
                worldMap.move(animal, move);
            }
            catch (PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());
            }

            animalIndex = (animalIndex + 1) % animalCount;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return worldMap.toString();
    }
}
