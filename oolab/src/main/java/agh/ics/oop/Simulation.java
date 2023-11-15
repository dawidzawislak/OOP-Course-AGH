package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        animals = new ArrayList<>();

        for(Vector2d pos : positions) {
            animals.add(new Animal(pos));
        }

        this.moves = moves;
    }

    public void run() {
        int animalIndex = 0;
        int animalCount = animals.size();
        for (MoveDirection move : moves) {
            Animal animal = animals.get(animalIndex);

            animal.move(move);

            System.out.printf("Zwierzę %d: %s%n", animalIndex, animal.getPosition());

            animalIndex = (animalIndex + 1) % animalCount;
        }
    }
}
