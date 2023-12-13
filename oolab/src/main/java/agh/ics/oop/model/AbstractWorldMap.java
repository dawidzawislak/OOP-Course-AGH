package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;

public abstract class AbstractWorldMap implements WorldMap {
    protected final HashMap<Vector2d, Animal> animals;
    private final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        animals = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!isOccupied(animal.getPosition())) return;

        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getLeftDown(), getRightUp());
    }

    protected abstract Vector2d getLeftDown();
    protected abstract Vector2d getRightUp();
}
