package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class ConsoleMapDisplay implements MapChangeListener {
    int countOfOperations;

    public ConsoleMapDisplay() {
        countOfOperations = 0;
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            countOfOperations++;
            System.out.println("----------------------------------------------------------------");
            System.out.println("ID: " + worldMap.getId());
            System.out.println(message + "\n");
            System.out.println(worldMap);
            System.out.println("Operations count: " + countOfOperations);
        }
    }
}
