package ua.osadchiy.lift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lift implements LiftI {
    private static final int countOfFloors = (int) (5 + Math.random() * 16);
    private Floor[] floors;
    private List<Passenger> passengerInLift = new ArrayList<>();
    private int currentCapacity = 0;
    private int currentFloor = 0;
    private State state = State.UP;

    public Floor[] getFloors() {
        return floors;
    }

    public Lift() {
        floors = new Floor[countOfFloors];
        for (int floor = 0; floor < floors.length; floor++) {
            floors[floor] = new Floor();
        }
    }

    @Override
    public void moveUp() {
        currentFloor++;
    }

    @Override
    public void moveDown() {
        currentFloor--;
    }

    @Override
    public void goToLift() {
        List<Passenger> passengers = getCurrentFloorPeople();
        for (Passenger passenger : passengers) {
            int maxCapacity = 5;
            if (!(currentCapacity == maxCapacity)) {
                if (passenger.getWhereToGo() == passenger.getCurrentFloor()) {
                } else if (passenger.getWhereToGo() > getCurrentFloor() && state == State.UP ||
                        passenger.getWhereToGo() < getCurrentFloor() && state == State.DOWN) {
                    passengerInLift.add(passenger);
                    currentCapacity++;
                }
            }
        }
    }

    @Override
    public void goOutFromLift() {
        if(!passengerInLift.isEmpty())
            for (int passengerIndex = 0; passengerIndex < passengerInLift.size(); passengerIndex++) {
                if (passengerInLift.get(passengerIndex).getWhereToGo() == getCurrentFloor()) {
                    passengerInLift.remove(passengerInLift.get(passengerIndex));
                    currentCapacity--;
                    passengerIndex--;
                }
            }
    }

    public List<Passenger> getCurrentFloorPeople() {
        return floors[getCurrentFloor()].getPassengerOnFloor();
    }

    public static int getCountOfFloors() {
        return countOfFloors;
    }

    public void displayCurrentFloor() {
        System.out.println("CurrentFloor: ");
        for (int floor = 0; floor < floors.length; floor++) {
            if (currentFloor == floor) {
                System.out.print(" (" + (floor + 1) + ") ");
            } else {
                System.out.print("  " + (floor + 1) + "  ");
            }
            if (floor %  2== 0) System.out.println();
        }
        System.out.print("\n\n");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }


    public List<?> wherePassengersWantToGo() {
        return passengerInLift.stream()
                .map(passenger -> String.valueOf(passenger.getWhereToGo()+1))
                .collect(Collectors.toList());
    }

    public List<Passenger> getPassengerInLift() {
        return passengerInLift;
    }

    private void move() {
        if(getPassengerInLift().isEmpty() && getCurrentFloor() == getCountOfFloors()-1) {
            while (true) {
                moveDown();
                state = State.DOWN;
                goToLift();
                if(!getPassengerInLift().isEmpty()) {
                    return;
                }
            }
        }

        if(getPassengerInLift().isEmpty() && getCurrentFloor() == 0) {
            while (true) {
                moveUp();
                state = State.UP;
                goToLift();
                if(!getPassengerInLift().isEmpty()) {
                    return;
                }
            }
        }

        if(getCurrentFloor() != 0 && getPassengerInLift().stream().allMatch(passenger -> passenger.getWhereToGo() < getCurrentFloor())) {
            state = State.DOWN;
            moveDown();
        }

        if(state == State.UP && getPassengerInLift().
                stream().anyMatch(passenger -> passenger.getWhereToGo() > getCurrentFloor())) {
            moveUp();
            state = State.UP;
        }
    }

    public void start(int countOfSteps) {
        for (int step = 1; step <= countOfSteps; step++) {
            System.out.println("************* " + step + " ************");
            goOutFromLift();
            goToLift();
            System.out.println("Passengers want to go to the:" + wherePassengersWantToGo());
            displayCurrentFloor();
            move();
        }
    }
}
