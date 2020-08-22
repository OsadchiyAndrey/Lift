package ua.osadchiy.lift.Utils;

import java.util.Random;

public class RandomNumber {
    private static final Random rand = new Random(40);

    public static int getNumber(int max) {
        return rand.nextInt(max);
    }

    public static int getNumber(int min, int max) {
        return min + rand.nextInt((max - min) + 1);
    }
}
