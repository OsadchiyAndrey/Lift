package ua.osadchiy.lift;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Passenger> passengerOnFloor = new ArrayList<Passenger>();

    public Floor() {
        int maxCountOfPassengersOnFloor = 10;
        int countPassengersOnFloor = (int) (Math.random() * maxCountOfPassengersOnFloor);
        for (int passengerIndex = 0; passengerIndex < countPassengersOnFloor; passengerIndex++) {
            passengerOnFloor.add(new Passenger());
        }
    }

    public List<Passenger> getPassengerOnFloor() {
        return passengerOnFloor;
    }
}
