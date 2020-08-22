package ua.osadchiy.lift.model;

import org.junit.jupiter.api.Test;

class FloorTest {

    private final Floor floor = new Floor();

    @Test
    void get() {
        if (!floor.get().getClass().getName().equals(Passenger.class.getName())) throw new AssertionError();
    }
}