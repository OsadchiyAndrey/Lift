package ua.osadchiy.lift.model;

import lombok.Getter;
import ua.osadchiy.lift.Utils.RandomNumber;

@Getter
public class Passenger {

    private int currentFloor;
    private int whereToGo;


    public Passenger(int currentFloor) {
        this.currentFloor = currentFloor;
        setWhereToGo(currentFloor);
    }

    public void setWhereToGo(int currentFloor) {
        do {
            whereToGo = RandomNumber.getNumber(Building.getCountOfFloors());
        } while (whereToGo == currentFloor);
    }

    @Override
    public String toString() {
        return String.valueOf(whereToGo);
    }

    public boolean isPassengerGoingUp() {
        return getCurrentFloor() < getWhereToGo();
    }

    public boolean isPassengerGoingDown() {
        return getCurrentFloor() > getWhereToGo();
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

}
