package ua.osadchiy.lift;

public class Passenger {

    private final int whereToGo = (int) (Math.random() * (Lift.getCountOfFloors()));
    private final int currentFloor = (int) (Math.random() * (Lift.getCountOfFloors()));

    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public String toString() {
        return String.valueOf(whereToGo);
    }

    public int getWhereToGo() {
        return whereToGo;
    }
}
