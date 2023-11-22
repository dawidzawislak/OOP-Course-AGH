package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    private final WorldMap worldMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap worldMap) {
        animals = new ArrayList<>();

        for(Vector2d pos : positions) {
            Animal animal = new Animal(pos);
            if (worldMap.place(animal))
                animals.add(animal);
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

            worldMap.move(animal, move);

            System.out.println(worldMap);

            animalIndex = (animalIndex + 1) % animalCount;
        }
    }

    @Override
    public String toString() {
        return worldMap.toString();
    }
}
