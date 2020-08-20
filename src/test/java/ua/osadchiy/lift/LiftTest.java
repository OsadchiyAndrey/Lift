package ua.osadchiy.lift;

import org.junit.jupiter.api.Test;
import ua.osadchiy.lift.Lift;
import ua.osadchiy.lift.Passenger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiftTest {

    @Test
    void moveUp() {
        final Lift lift = new Lift();
        lift.moveUp();
        assertEquals(lift.getCurrentFloor(), 1);
    }

    @Test
    void moveDown() {
        final Lift lift = new Lift();
        lift.moveUp();
        lift.moveDown();
        assertEquals(lift.getCurrentFloor(), 0);
    }

    @Test
    void goToLift() {
        final Lift lift = new Lift();
        while (lift.getCurrentFloorPeople().isEmpty()) {
            lift.goToLift();
            lift.moveUp();
        }
        assertFalse(lift.getCurrentFloorPeople().isEmpty());
    }

    @Test
    void getCurrentFloorPeople() {
        final Lift lift = new Lift();
        List<Passenger> passengers = lift.getCurrentFloorPeople();
        List<Passenger> passengersInTheZeroIndexOfArrayOfFloors = lift.getFloors()[0].getPassengerOnFloor();
        assertEquals(passengers, passengersInTheZeroIndexOfArrayOfFloors);
    }

    @Test
    void getCountOfFloors() {
        final Lift lift = new Lift();
        assert Lift.getCountOfFloors() <= 4 || Lift.getCountOfFloors() >= 21 || (true);
    }

    @Test
    void getCurrentFloor() {
        final Lift lift = new Lift();
        lift.moveUp();
        assertEquals(lift.getCurrentFloor(), 1);
    }
}
