package agh.ics.oop.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Optional<WorldElement> objectAt(Vector2d position) {
        WorldElement animal = animals.get(position);
        if (animal != null)
            return Optional.of(animal);
        WorldElement grassEl = grass.get(position);
        return Optional.ofNullable(grassEl);
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
        Optional<WorldElement> elementOptional = objectAt(position);
        return elementOptional.map(element -> !(element instanceof Animal)).orElse(true);
    }

    @Override
    public synchronized List<WorldElement> getElements() {
        return Stream.concat(animals.values().stream(), grass.values().stream()).collect(Collectors.toList());
    }
}
