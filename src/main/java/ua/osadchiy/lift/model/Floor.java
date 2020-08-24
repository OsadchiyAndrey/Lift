package ua.osadchiy.lift.model;

import lombok.Getter;
import ua.osadchiy.lift.utils.RandomNumber;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Floor implements Supplier<Passenger> {

    @Getter
    private final List<Passenger> passengersOnFloor;
    private static int counter = 0;

    public Floor() {
        final int maxCountOfPassengersOnFloor = 10;
        passengersOnFloor = initializePassengersOnTheFloor(RandomNumber.getNumber(maxCountOfPassengersOnFloor));
        counter++;
    }

    private List<Passenger> initializePassengersOnTheFloor(int countPassengersOnFloor) {
        return Stream.generate(this).limit(countPassengersOnFloor).collect(Collectors.toList());
    }

    @Override
    public Passenger get() {
        return new Passenger(counter);
    }

}

