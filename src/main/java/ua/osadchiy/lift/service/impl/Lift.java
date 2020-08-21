package ua.osadchiy.lift.service.impl;

import lombok.Getter;
import ua.osadchiy.lift.model.Building;
import ua.osadchiy.lift.model.Passenger;
import ua.osadchiy.lift.model.State;
import ua.osadchiy.lift.service.Door;
import ua.osadchiy.lift.service.Lifting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lift implements Door, Lifting {

    @Getter
    private final List<Passenger> passengersInLift = new ArrayList<>();

    Building building = new Building();

    State state = State.UP;

    private int currentFloor = 0;

    private boolean isNotFull() {
        return getPassengersInLift().size() < 5;
    }

    @Override
    public void goToLift() {
        List<Passenger> passengers = building.getCurrentFloorPeople(currentFloor);
        for (int index = 0; index < passengers.size() && isNotFull(); index++) {
                if(passengersInLift.isEmpty()) {
                    addPassengerIfLiftEmpty(passengers, index);
                } else {
                    if (addPassengerWhoIsGoingUp(passengers.get(index)) || addPassengerWhoIsGoingDown(passengers.get(index))) {
                        building.getCurrentFloorPeople(currentFloor).remove(passengers.get(index));
                    }
            }
        }
    }


    private void addPassengerIfLiftEmpty(List<Passenger> passengers, int index) {
        passengersInLift.add(passengers.get(index));
        if (passengers.get(index).isPassengerGoingUp()) {
            state = State.UP;
        } else state = State.DOWN;
        building.getCurrentFloorPeople(currentFloor).remove(passengers.get(index));
    }

    @Override
    public void goOutFromLift() {
        if(!passengersInLift.isEmpty()) {
            for (int i = 0; i < passengersInLift.size(); i++) {
                if (passengersInLift.get(i).getWhereToGo() == currentFloor) {
                    Passenger passenger = passengersInLift.get(i);
                    passenger.setCurrentFloor(currentFloor);
                    passenger.setWhereToGo(currentFloor);
                    building.getCurrentFloorPeople(currentFloor).add(passenger);
                    passengersInLift.remove(passengersInLift.get(i));
                    System.out.println();
                    i--;
                }
            }
        }
    }

    private boolean addPassengerWhoIsGoingUp(Passenger passenger) {
        if (!passenger.isPassengerGoingUp() || state != State.UP) return false;
        passengersInLift.add(passenger);
        return true;
    }

    private boolean addPassengerWhoIsGoingDown(Passenger passenger) {
        if (!passenger.isPassengerGoingDown() || state != State.DOWN) return false;
        passengersInLift.add(passenger);
        return true;
    }

    public List<String> wherePassengersWantToGo() {
        return passengersInLift.stream()
                .map(passenger -> String.valueOf(passenger.getWhereToGo() + 1))
                .collect(Collectors.toList());
    }

    @Override
    public void moveUp() {
        currentFloor++;
    }

    @Override
    public void moveDown() {
        currentFloor--;
    }

    private boolean isAllPassengersAreGoingDown() {
        return state == State.DOWN && getPassengersInLift()
                .stream()
                .allMatch(passenger -> passenger.getWhereToGo() < currentFloor);
    }

    private boolean isAllPassengersAreGoingUp() {
        return state == State.UP && getPassengersInLift()
                .stream()
                .allMatch(passenger -> passenger.getWhereToGo() > currentFloor);
    }

    private boolean isGroundFloor() {
        return currentFloor == 0;
    }

    private boolean isTopFloor() {
        return currentFloor == Building.getCountOfFloors() - 1;
    }

    private void makeStep() {
        goOutFromLift();

        if (isTopFloor()) {
            state = State.DOWN;
            goToLift();
            moveDown();
        } else if(isGroundFloor()) {
            state = State.UP;
            goToLift();
            moveUp();
        } else if (passengersInLift.isEmpty() && state == State.UP) {
            goToLift();
            moveUp();
        } else if (passengersInLift.isEmpty() && state == State.DOWN) {
            goToLift();
            moveDown();
        } else if (isAllPassengersAreGoingUp()) {
            state = State.UP;
            goToLift();
            moveUp();
        } else if (isAllPassengersAreGoingDown() ) {
            state = State.DOWN;
            goToLift();
            moveDown();
        }

    }

    public void start(int countOfSteps) {
        for (int step = 1; step <= countOfSteps; step++) {
            System.out.println("************* " + step + " ************");
            System.out.println("LIFT IS GOING " + state);
            System.out.println("CURRENT FLOOR: " + (currentFloor + 1));
            makeStep();
            System.out.println("PASSENGERS GO TO: " + wherePassengersWantToGo());
        }
    }

}
