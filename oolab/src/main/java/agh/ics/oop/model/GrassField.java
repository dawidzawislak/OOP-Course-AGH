package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GrassField extends AbstractWorldMap {
    private final HashMap<Vector2d, Grass> grass;

    static private int idCounter = 0;

    public GrassField(int grassCount) {
        super();
        Vector2d rightUp = new Vector2d((int)Math.sqrt(grassCount*10), (int)Math.sqrt(grassCount*10));
        grass = new HashMap<>();

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(rightUp.getX(), rightUp.getY(), grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
        id = "GrassField#" + idCounter;
        idCounter++;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (isOccupied(position)) return animals.get(position);
        return grass.get(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d rightUp = new Vector2d(1, 1);

        for (WorldElement we : getElements()) {
            rightUp = we.getPosition().upperRight(rightUp);
        }

        Vector2d leftDown = new Vector2d(1, 1);

        for (WorldElement we : getElements()) {
            leftDown = we.getPosition().lowerLeft(leftDown);
        }

        return new Boundary(leftDown.add(new Vector2d(-1, -1)), rightUp.add(new Vector2d(1, 1)));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> worldElements = new ArrayList<>(animals.values());
        worldElements.addAll(grass.values());

        return worldElements;
    }
}
