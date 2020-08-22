package ua.osadchiy.lift.service;

import ua.osadchiy.lift.model.Passenger;

import java.util.List;

public interface Door {

    List<Passenger> goTo();

    List<Passenger> goOut();

}
