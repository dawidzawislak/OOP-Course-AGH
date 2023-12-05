package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d leftDown;
    private final Vector2d rightUp;
    public RectangularMap(int width, int height) {
        super();
        leftDown = new Vector2d(0, 0);
        rightUp = new Vector2d(width, height);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    protected Vector2d getRightUp() {
        return rightUp;
    }

    @Override
    protected Vector2d getLeftDown() {
        return leftDown;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (0 <= position.getX() && position.getX() <= getRightUp().getX() && 0 <= position.getY() && position.getY() <= getRightUp().getY()) && !animals.containsKey(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }
}
