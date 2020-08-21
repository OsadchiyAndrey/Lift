package ua.osadchiy.lift.model;

import lombok.Getter;
import ua.osadchiy.lift.Utils.RandomNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Building {

    private static final int countOfFloors = RandomNumber.getNumber(5, 20);

    @Getter
    private List<Floor> floors;

    public Building() {
        floors = fillingLiftWithFloors();
    }

    private List<Floor> fillingLiftWithFloors() {
        return Stream.generate(Floor::new).limit(countOfFloors).collect(Collectors.toList());
    }

    public static int getCountOfFloors() {
        return countOfFloors;
    }

    public List<Passenger> getCurrentFloorPeople(int currentFloor) {
        return floors.get(currentFloor).getPassengersOnFloor();
    }
}