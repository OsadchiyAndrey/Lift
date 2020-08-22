package ua.osadchiy.lift.service.impl;

import lombok.Getter;
import ua.osadchiy.lift.model.Building;
import ua.osadchiy.lift.model.Passenger;
import ua.osadchiy.lift.model.State;
import ua.osadchiy.lift.service.Door;
import ua.osadchiy.lift.service.Lifting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Lift implements Door, Lifting {

    @Getter
    private List<Passenger> passengersInLift = new ArrayList<>();

    private final Building building = new Building();

    @Getter
    private State state = State.UP;

    @Getter
    private int currentFloor = 0;

    private boolean isNotFull() {
        return passengersInLift.size() < 5;
    }

    private void removeExitedPassenger(Passenger passenger) {
        if (isSuitablePassenger(passenger)) {
            building.getCurrentFloorPeople(currentFloor).remove(passenger);
        }
    }

    private boolean isSuitablePassenger(Passenger passenger) {
        return addPassengerWhoIsGoingUp(passenger) || addPassengerWhoIsGoingDown(passenger);
    }


    private State addPassengerIfLiftEmpty(Passenger passenger) {
        passengersInLift.add(passenger);
        building.getCurrentFloorPeople(currentFloor).remove(passenger);
        return passenger.isPassengerGoingUp() ? State.UP : State.DOWN;
    }

    @Override
    public State moveUp() {
        currentFloor++;
        return State.UP;
    }

    @Override
    public State moveDown() {
        currentFloor--;
        return State.DOWN;
    }

    @Override
    public List<Passenger> goOut() {
        Iterator<Passenger> iterator = passengersInLift.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getWhereToGo() == currentFloor) {
                building.getCurrentFloorPeople(currentFloor).add(genNewFloorForPassenger(passenger, currentFloor));
                iterator.remove();
            }
        }
        return passengersInLift;
    }

    @Override
    public List<Passenger> goTo() {
        List<Passenger> passengers = building.getCurrentFloorPeople(currentFloor);
        for (int index = 0; index < passengers.size() && isNotFull(); index++) {
            Passenger passenger = passengers.get(index);
            if (passengersInLift.isEmpty()) {
                state = addPassengerIfLiftEmpty(passenger);
            } else {
                removeExitedPassenger(passenger);
            }
        }
        return passengersInLift;
    }

    private Passenger genNewFloorForPassenger(Passenger passenger, int currentFloor) {
        passenger.setCurrentFloor(currentFloor);
        passenger.setWhereToGo(currentFloor);
        return passenger;
    }

    private boolean addPassengerWhoIsGoingUp(Passenger passenger) {
        if (!passenger.isPassengerGoingUp() || State.UP != state) return false;
        return passengersInLift.add(passenger);
    }

    private boolean addPassengerWhoIsGoingDown(Passenger passenger) {
        if (!passenger.isPassengerGoingDown() || State.DOWN != state) return false;
        return passengersInLift.add(passenger);
    }

    public List<String> wherePassengersWantToGo() {
        return passengersInLift.stream()
                .map(passenger -> String.valueOf(passenger.getWhereToGo() + 1))
                .collect(Collectors.toList());
    }


    private boolean isAllPassengersAreGoingDown() {
        return state == State.DOWN && passengersInLift
                .stream()
                .allMatch(passenger -> passenger.getWhereToGo() < currentFloor);
    }

    private boolean isTopFloor() {
        return currentFloor == Building.getCountOfFloors() - 1;
    }

    private boolean firstCondition() {
        return isTopFloor() || (isAllPassengersAreGoingDown() && !passengersInLift.isEmpty());
    }

    private boolean secondCondition() {
        return passengersInLift.isEmpty() && state == State.DOWN && currentFloor != 0;
    }

    private State makeStep() {
        return firstCondition() || secondCondition() ? moveDown() : moveUp();
    }

    public void start(int countOfSteps) {
        for (int step = 1; step <= countOfSteps; step++) {
            System.out.println("************* " + step + " *************");
            System.out.println("LIFT IS GOING " + state);
            System.out.println("CURRENT FLOOR: " + (currentFloor + 1));
            passengersInLift = goTo();
            if (!passengersInLift.isEmpty()) {
                passengersInLift = goOut();
            }
            state = makeStep();
            System.out.println(state);
            System.out.println("PASSENGERS GO TO: " + wherePassengersWantToGo());
        }
    }
}
