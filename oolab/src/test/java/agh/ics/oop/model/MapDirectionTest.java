package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;

        assertEquals(MapDirection.EAST, north.next());
        assertEquals(MapDirection.SOUTH, east.next());
        assertEquals(MapDirection.WEST, south.next());
        assertEquals(MapDirection.NORTH, west.next());
    }

    @Test
    void previous() {
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;

        assertEquals(MapDirection.WEST, north.previous());
        assertEquals(MapDirection.SOUTH, west.previous());
        assertEquals(MapDirection.EAST, south.previous());
        assertEquals(MapDirection.NORTH, east.previous());
    }
}