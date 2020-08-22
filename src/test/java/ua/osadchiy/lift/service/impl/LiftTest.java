package ua.osadchiy.lift.service.impl;

import org.junit.jupiter.api.Test;
import ua.osadchiy.lift.model.Building;
import ua.osadchiy.lift.model.Passenger;
import ua.osadchiy.lift.model.State;

import java.util.ArrayList;
import java.util.List;

class LiftTest {

    private final Lift lift = new Lift();
    private final Building building = new Building();

    @Test
    void moveUp() {
        lift.moveUp();
        if (lift.getCurrentFloor() != 1) throw new AssertionError();
        if (lift.moveUp() != State.UP) throw new AssertionError();
    }

    @Test
    void moveDown() {
        lift.moveUp();
        lift.moveDown();
        if (lift.getCurrentFloor() != 0) throw new AssertionError();
        if (lift.moveDown() != State.DOWN) throw new AssertionError();
    }

    @Test
    void goTo() {
        assert getNotEmptyLift(new ArrayList<>())
                .stream()
                .anyMatch(passenger -> !building.getFloors()
                        .get(lift.getCurrentFloor())
                        .getPassengersOnFloor()
                        .contains(passenger));
    }

    private List<Passenger> getNotEmptyLift(List<Passenger> passengersInLift) {
        while (passengersInLift.isEmpty()) {
            lift.moveUp();
            passengersInLift = lift.goTo();
        }
        return passengersInLift;
    }

    @Test
    void goOut() {
        assert isPassengersGoOut();
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