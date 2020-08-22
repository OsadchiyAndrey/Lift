package ua.osadchiy.lift.model;

import org.junit.jupiter.api.Test;

public class BuildingTest {

    private final Building building = new Building();

    @Test
    void getCurrentFloorPeople() {
        assert building.getCurrentFloorPeople(1).equals(building.getFloors().get(1).getPassengersOnFloor());
    }
}
