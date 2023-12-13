package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final HashMap<Vector2d, Animal> animals;

    protected final List<MapChangeListener> listeners;
    private final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        animals = new HashMap<>();
        listeners = new ArrayList<>();
        mapVisualizer = new MapVisualizer(this);
    }

    public void addListener(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MapChangeListener listener) {
        listeners.remove(listener);
    }

    public void mapChanged(String msg) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this, msg);
        }
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (!canMoveTo(animal.getPosition())) throw new PositionAlreadyOccupiedException(animal.getPosition());

        animals.put(animal.getPosition(), animal);
        mapChanged("New animal placed on position: " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) throws PositionAlreadyOccupiedException  {
        if (!isOccupied(animal.getPosition())) throw new PositionAlreadyOccupiedException(animal.getPosition());

        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal moved to position: " + animal.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getCurrentBounds().lowerLeft(), getCurrentBounds().upperRight());
    }
}
