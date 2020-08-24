package ua.osadchiy.lift.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingTest {

    private final Building building = new Building();

    @Test
    void shouldReturnCurrentFloorPeople() {
        assertEquals(building.getFloors().get(1).getPassengersOnFloor(), building.getCurrentFloorPeople(1));
    }
}
