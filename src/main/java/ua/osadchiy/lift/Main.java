package ua.osadchiy.lift;

import ua.osadchiy.lift.service.impl.Lift;

public class Main {
    public static void main(String[] args) {
        new Lift().start(50);
    }
}
