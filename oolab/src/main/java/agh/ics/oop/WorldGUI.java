package agh.ics.oop;

import javafx.application.Application;

public class WorldGUI {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        Application.launch(SimulationApp.class, args);

        System.out.println("system zakończył działanie");
    }
}
