package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class TextMap implements WorldMap<String, Integer> {
    private final List<String> words = new ArrayList<>();

    public TextMap() {}

    @Override
    public String toString() {
        String str = "";
        for (String s : words)
            str += s + " ";
        if (str.length() > 0) str = str.substring(0, str.length()-1);
        return str;
    }

    /**
     * Checks if string can move to given position (Ignoring 2nd dimmension in this case)
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        return 0 <= position.getX() && position.getX() < words.size();
    }

    @Override
    public boolean place(String word) {
        words.add(word);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        if (direction == MoveDirection.FORWARD) {
            int pos = words.indexOf(object);
            if (canMoveTo(new Vector2d(pos+1, 0))) swap(words, pos, pos+1);
        }
        if (direction == MoveDirection.BACKWARD) {
            int pos = words.indexOf(object);
            if (canMoveTo(new Vector2d(pos-1, 0))) swap(words, pos, pos-1);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return false;
    }

    @Override
    public String objectAt(Integer position) {
        if (position < 0 || position >= words.size()) return null;
        return words.get(position);
    }
}
