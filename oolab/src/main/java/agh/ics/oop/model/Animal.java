package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        orientation = MapDirection.NORTH;
        this.position = position;
    }

    @Override
    public String toString() {
        return switch(orientation) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> newPosition = position.add(orientation.toUnitVector());
            case BACKWARD -> newPosition = position.add(orientation.toUnitVector().opposite());
        }

        if (newPosition != position)
            if (0 <= newPosition.getX() && newPosition.getX() <= 4 && 0 <= newPosition.getY() && newPosition.getY() <= 4)
                position = newPosition;
    }

    public void move(MoveDirection direction, MoveValidator moveValidator) {
        Vector2d newPosition = position;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> newPosition = position.add(orientation.toUnitVector());
            case BACKWARD -> newPosition = position.add(orientation.toUnitVector().opposite());
        }

        if (newPosition != position && moveValidator.canMoveTo(newPosition))
            position = newPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
