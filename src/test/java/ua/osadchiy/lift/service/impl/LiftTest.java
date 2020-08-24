package ua.osadchiy.lift.service.impl;

import org.junit.jupiter.api.Test;
import ua.osadchiy.lift.model.Building;
import ua.osadchiy.lift.model.Passenger;
import ua.osadchiy.lift.model.State;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiftTest {

    private final Lift lift = new Lift();
    private final Building building = new Building();

    @Test
    void shouldMoveTheLiftOneFloorUpAndReturnStateUp() {
        lift.moveUp();
        assertEquals(lift.getCurrentFloor(), 1);
        assertSame(lift.moveUp(), State.UP);
    }

    @Test
    void shouldMoveTheLiftOneFloorUpAndReturnStateDown() {
        lift.moveUp();
        lift.moveDown();
        assertEquals(lift.getCurrentFloor(),0);
        assertSame(lift.moveDown(), State.DOWN);
    }

    @Test
    void shouldMovePassengerFromFloorToLift() {
        assertTrue(getNotEmptyLift(new ArrayList<>())
                .stream()
                .anyMatch(passenger -> !building.getFloors()
                        .get(lift.getCurrentFloor())
                        .getPassengersOnFloor()
                        .contains(passenger)));
    }

    private List<Passenger> getNotEmptyLift(List<Passenger> passengersInLift) {
        while (passengersInLift.isEmpty()) {
            lift.moveUp();
            passengersInLift = lift.goTo();
        }
        return passengersInLift;
    }

    @Test
    void shouldDropPassengersFormLift() {
        assertTrue(isPassengersGoOut());
    }

    private boolean isPassengersGoOut() {
        List<Passenger> passengers = getNotEmptyLift(new ArrayList<>());
        List<Passenger> oldPassengers = new ArrayList<>(passengers);
        while (lift.getCurrentFloor() < Building.getCountOfFloors()) {
            lift.moveUp();
            passengers = lift.goOut();
            if(!oldPassengers.equals(passengers)) return true;
        }
        return false;
    }

}