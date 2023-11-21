package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int height;
    private final int width;

    private final MapVisualizer mapVisualizer;

    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public String toString() {
        Vector2d leftDownBound = new Vector2d(0,0);
        return mapVisualizer.draw(leftDownBound, leftDownBound.add(new Vector2d(width, height)));
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
    public boolean canMoveTo(Vector2d position) {
        return (0 <= position.getX() && position.getX() <= width && 0 <= position.getY() && position.getY() <= height) && !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (!isOccupied(position)) return null;
        return animals.get(position);
    }
}
