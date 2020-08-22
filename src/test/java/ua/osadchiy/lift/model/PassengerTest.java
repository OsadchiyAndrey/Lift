package ua.osadchiy.lift.model;


import org.junit.jupiter.api.Test;

public class PassengerTest {

    private final Passenger passenger = new Passenger(4);


    @Test
    void setWhereToGo() {
        if (passenger.getWhereToGo() == passenger.getCurrentFloor()) throw new AssertionError();
    }

    @Test
    void setCurrentFloor() {
        passenger.setCurrentFloor(2);
        assert 2 == passenger.getCurrentFloor();
    }
}
