package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private List<Vector2d> positions = new ArrayList<>();
    private final int size;
    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        size = grassCount;

        int[] indices = new int[maxHeight*maxWidth];

        for (int i = 0; i < maxHeight * maxWidth; i++)
            indices[i] = i;

        Random r = new Random();

        for (int i = maxHeight*maxWidth-1; i > maxHeight*maxWidth-size-2; i--) {

            int j = r.nextInt(i);

            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }
        for (int i = maxHeight*maxWidth-1; i > maxHeight*maxWidth-size-2; i--) {
            positions.add(new Vector2d(indices[i]%maxWidth, indices[i]/maxWidth));
        }
    }

    public Iterator<Vector2d> iterator() {
        return new RandomPositionIterator(this);
    }

    public Vector2d getHead() {
        return positions.get(0);
    }

    public Vector2d getTail() {
        return positions.get(positions.size() - 1);
    }

    public int getIndex(Vector2d pos) {
        return positions.indexOf(pos);
    }

    public Vector2d get(int index) {
        return positions.get(index);
    }
}

class RandomPositionIterator implements Iterator<Vector2d> {
    private Vector2d current;
    private final RandomPositionGenerator generator;

    RandomPositionIterator(RandomPositionGenerator obj) {
        current = obj.getHead();
        generator = obj;
    }

    public boolean hasNext() {
        return !current.equals(generator.getTail());
    }

    public Vector2d next() {
        Vector2d data = current;
        int index = generator.getIndex(current) + 1;
        current = generator.get(index);
        return data;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}