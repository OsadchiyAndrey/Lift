package ua.osadchiy.lift.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerTest {

    private final Passenger passenger = new Passenger(4);


    @Test
    void shouldSetValueDifferentFromWhereToGo() {
        assertTrue(passenger.getWhereToGo() != passenger.getCurrentFloor());
    }

    @Test
    void shouldSetCurrentFloorRightly() {
        passenger.setCurrentFloor(2);
        assertEquals(passenger.getCurrentFloor(), 2);
    }
}
