package ua.osadchiy.lift.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FloorTest {

    private final Floor floor = new Floor();

    @Test
    void shouldReturnNewPassengers() {
        final boolean isPassenger = !floor.get().getClass().getName().equals(Passenger.class.getName());
        assertTrue(isPassenger);
    }
}